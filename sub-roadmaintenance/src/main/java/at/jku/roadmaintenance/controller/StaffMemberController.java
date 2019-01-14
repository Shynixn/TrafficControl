package at.jku.roadmaintenance.controller;

import at.jku.roadmaintenance.repository.StaffMemberRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/staff")
public class StaffMemberController {
    @GET
    public Response requestStaffMember(){
        return Response.ok(
                StaffMemberRepository.getInstance().getStaffMembers(),
                MediaType.APPLICATION_JSON
        ).build();
    }
}
