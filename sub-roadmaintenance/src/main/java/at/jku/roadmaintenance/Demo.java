package at.jku.roadmaintenance;
import at.jku.roadmaintenance.*;

public class Demo {

	public static void main(String[] args) {
		
		//configuration
		Schedule schedule = new Schedule("J�nner");
		
		Resources res = new Resources();
		res.createResource("Bagger", 5);
		res.createResource("Arbeiter", 10);
		
		//create incident
		Incident i1 = new Incident(Type.ACCIDENT, Priority.HIGH, new StaffMember("Peter Hager"));
		i1.addRoadSegment(new RoadSegment("K�rnerstra�e", 30));
		i1.addRoadSegment(new RoadSegment("Bethlehemstra�e", 100));
		
		//allocate and add units
		i1.addUnit(new Unit("Bagger", 2));
		i1.addUnit(new Unit("Arbeiter", 5));
		
		
		schedule.addIncident(i1);

		
		System.out.println(schedule.getIncidentsByPriority(Priority.HIGH));
		i1.dispatchIncident();
		System.out.println(schedule.getIncidentsByPriority(Priority.HIGH));
		i1.finishIncident();
		System.out.println(schedule.getIncidentsByPriority(Priority.HIGH));

	}

}
