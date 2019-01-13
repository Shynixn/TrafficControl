package at.jku.roadmaintenance.controller;

import at.jku.roadmaintenance.Incident;
import at.jku.roadmaintenance.repository.IncidentRepository;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/incident")
public class IncidentController {
    @GET
    public Response getIncidents(){
        return Response.ok(
                IncidentRepository.getInstance().getIncidents(),
                MediaType.APPLICATION_JSON
        ).build();
    }

    @POST
    public Response addIncident(Incident incident){
        if(incident == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        IncidentRepository.getInstance().getIncidents().add(incident);

        return Response.ok().build();
    }
}
