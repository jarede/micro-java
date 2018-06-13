package micro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import org.jboss.resteasy.mock.MockHttpRequest;

public class Mock {

    /**
     * Return a multipart/form-data MockHttpRequest
     * 
     * @param parts Key is the name of the part, value is either a String or a File.
     * @return
     */
    public static MockHttpRequest multipartRequest(String uri, Map<String, Object> parts)
            throws URISyntaxException, IOException {
        MockHttpRequest req = MockHttpRequest.post(uri);
        String boundary = UUID.randomUUID().toString();
        req.contentType("multipart/form-data; boundary=" + boundary);
        // Make sure this is deleted in afterTest()
        File tmpMultipartFile = Files.createTempFile(null, null).toFile();
        System.out.println("Tmp file:" + tmpMultipartFile.getAbsolutePath());
        FileWriter fileWriter = new FileWriter(tmpMultipartFile);
        fileWriter.append("--").append(boundary);
        for (Entry<String, Object> entry : parts.entrySet()) {
            if (entry.getValue() instanceof String) {
                fileWriter.append("\n");
                fileWriter.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"").append("\n\n");
                fileWriter.append(entry.getValue().toString()).append("\n");
                fileWriter.append("--").append(boundary);
            } else if (entry.getValue() instanceof File) {
                fileWriter.append("\n");
                File val = (File) entry.getValue();
                fileWriter.append(String.format("Content-Disposition: form-data; name=\"%s\"; filename=\"%s\"",
                        entry.getKey(), val.getName())).append("\n");
                fileWriter.append("Content-Type: application/octet-stream").append("\n\n");
                FileInputStream fis = new FileInputStream(val);
                int b = fis.read();
                while (b >= 0) {
                    fileWriter.write(b);
                    b = fis.read();
                }

                fis.close();
                fileWriter.append("\n").append("--").append(boundary);
            }
        }

        fileWriter.append("--");
        fileWriter.flush();
        fileWriter.close();
        req.setInputStream(new FileInputStream(tmpMultipartFile));
        return req;
    }
}