package io.appform.dropwizard.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

/**
 * Test resource class.
 */
@Produces(MediaType.APPLICATION_JSON)
@Path("/hello")
public class HelloResource {
    private final String name;

    public HelloResource(String name) {
        this.name = name;
    }

    @GET
    public Response hello() {
        return Response.ok()
                    .entity(Collections.singletonMap("message", "Hello name"))
                    .build();
    }

}
