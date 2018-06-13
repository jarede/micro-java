package micro;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/s")
public class Servico {

    private static Logger log = LoggerFactory.getLogger(Servico.class);

    @Context
    private UriInfo uriInfo;

    @POST
    @Path("/i")
    @Consumes("multipart/form-data")
    @Produces("application/json")
    public Response iniciar(MultipartFormDataInput campos) {
        log.info(campos.toString());
        return Response.ok().build();
    }

}
