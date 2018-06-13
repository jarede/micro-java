package micro.etc;

import java.io.IOException;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

public class RestEasyDefaultCharsetInterceptor implements ReaderInterceptor {
    private static final String RESTEASY_DEFAULT_CHARSET_PROPERTY = "resteasy.provider.multipart.inputpart.defaultCharset";

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext ctx) throws IOException, WebApplicationException {
        ctx.setProperty(RESTEASY_DEFAULT_CHARSET_PROPERTY, "UTF-8");
        return ctx.proceed();
    }
}