package at.jku.roadmaintenance.repository;

import at.jku.roadmaintenance.Incident;
import at.jku.roadmaintenance.Priority;
import at.jku.roadmaintenance.Type;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IncidentRepository {
    private static IncidentRepository instance = null;

    public static IncidentRepository getInstance() {
        if(instance == null){
            instance = new IncidentRepository();

            instance.getIncidents().addAll(
                    Arrays.asList(
                            new Incident(Type.ACCIDENT, Priority.STANDARD,
                                    StaffMemberRepository.getInstance().getStaffMembers().get(0)),
                            new Incident(Type.ACCIDENT, Priority.STANDARD,
                                    StaffMemberRepository.getInstance().getStaffMembers().get(1)),
                            new Incident(Type.ACCIDENT, Priority.STANDARD,
                                    StaffMemberRepository.getInstance().getStaffMembers().get(2)),
                            new Incident(Type.ACCIDENT, Priority.STANDARD,
                                    StaffMemberRepository.getInstance().getStaffMembers().get(3))
                    )
            );
        }

        return instance;
    }

    private List<Incident> incidents = new LinkedList<>();

    public List<Incident> getIncidents() {
        return incidents;
    }
}
