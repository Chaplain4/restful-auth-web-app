package main.rest.service;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Date;

@Path("/testservice")
public class RestServiceTest {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTestService() {
        return "Hello World! This is coming from webservice!! Server date:" +new Date();
    }

}
