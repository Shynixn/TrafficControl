package at.jku.roadmaintenance.repository;

import at.jku.roadmaintenance.Incident;

import java.util.LinkedList;
import java.util.List;

public class IncidentRepository {
    private static IncidentRepository instance = null;

    public static IncidentRepository getInstance() {
        if(instance == null){
            instance = new IncidentRepository();
        }

        return instance;
    }

    private List<Incident> incidents = new LinkedList<>();

    public List<Incident> getIncidents() {
        return incidents;
    }
}
