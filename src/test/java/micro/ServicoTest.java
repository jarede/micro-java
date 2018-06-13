package micro;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServicoTest {

    private static Dispatcher dispatcher;
    private static Logger log = LoggerFactory.getLogger(ServicoTest.class);

    @Before
    public void setUp() throws Exception {
        dispatcher = MockDispatcherFactory.createDispatcher();
        POJOResourceFactory noDefaults = new POJOResourceFactory(Servico.class);
        dispatcher.getRegistry().addResourceFactory(noDefaults);
    }

    private static Map<String, Object> formAg() {
        Map<String, Object> parts = new HashMap<String, Object>();
        parts.put("nome", "eu");
        return parts;
    }

    @Test
    public void iniciarOk() throws URISyntaxException, IOException {
        MockHttpRequest request = null;
        MockHttpResponse response = null;
        Map<String, Object> parts = formAg();

        request = Mock.multipartRequest("/s/i", parts);
        response = new MockHttpResponse();
        dispatcher.invoke(request, response);
        log.info(response.getContentAsString());
        Assert.assertEquals(200, response.getStatus());
    }

}
