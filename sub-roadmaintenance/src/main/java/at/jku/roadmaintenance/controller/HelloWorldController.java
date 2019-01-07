package at.jku.roadmaintenance.controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * There should only be resource controllers like below in this package. Please
 * use a different package for business logic.
 */
@Path("/hello")
public class HelloWorldController {

    /**
     * Sends a plain text response 'Hello from RoadMaintenance!' once
     * a user opens 'http://127.0.0.1:8082/roadmaintenance/hello'
     *
     * @return response.
     */
    @GET
    @Produces("text/plain")
    public Response doGet() {
        return Response.ok("Hello from RoadMaintenance test!").build();
    }
}
