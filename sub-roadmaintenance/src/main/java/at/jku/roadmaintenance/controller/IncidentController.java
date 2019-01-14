package at.jku.roadmaintenance.controller;

import at.jku.roadmaintenance.Incident;
import at.jku.roadmaintenance.Priority;
import at.jku.roadmaintenance.Status;
import at.jku.roadmaintenance.Type;
import at.jku.roadmaintenance.entity.IncidentObject;
import at.jku.roadmaintenance.repository.IncidentRepository;
import at.jku.roadmaintenance.repository.StaffMemberRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDate;

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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addIncident(IncidentObject request){
        if(request == null){
            return Response.status(Response.Status.NO_CONTENT).build();
        }

        Incident incident = new Incident();
        LocalDate startDate = LocalDate.of(request.getStartYear(), request.getStartMonth(), request.getStartDay());
        LocalDate endDate = LocalDate.of(request.getEndYear(), request.getEndMonth(), request.getEndDay());


        incident.setType(Type.valueOf(request.getType().toUpperCase()));
        incident.setPriority(Priority.valueOf(request.getPriority().toUpperCase()));
        incident.setAssignee(
                StaffMemberRepository.getInstance().getStaffMembers()
                        .stream()
                        .filter((staffMember -> staffMember.getName().equals(request.getAssigned())))
                        .findFirst().orElse(null)
        );
        incident.setBegin(startDate);
        incident.setEnd(endDate);
        incident.setStatus(Status.valueOf(request.getStatus().toUpperCase().replace(" ", "_")));

        IncidentRepository.getInstance().getIncidents().add(incident);

        return Response.ok().build();
    }
}
