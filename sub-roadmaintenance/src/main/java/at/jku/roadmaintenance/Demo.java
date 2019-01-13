package at.jku.roadmaintenance;
import java.time.LocalDate;

public class Demo {

	public static void main(String[] args) {
		
		//configure demo resources
		Resources res = new Resources();
		res.createResource("Bagger", 5);
		res.createResource("Arbeiter", 10);
		
		//configure demo schedule
		Schedule schedule = new Schedule("Jänner");
		
		//create incident
		Incident i1 = new Incident(Type.ACCIDENT, Priority.HIGH, new StaffMember("Peter Hager"));
		i1.addRoadSegment(new RoadSegment("Körnerstraße", 30));
		i1.addRoadSegment(new RoadSegment("Bethlehemstraße", 100));
		i1.setBeginDate(LocalDate.of(2019, 1, 15));
		i1.setEndDate(LocalDate.of(2019, 1, 20));
		
		//allocate and add units
		i1.addUnit(new WorkUnit("Bagger", 2));
		i1.addUnit(new WorkUnit("Arbeiter", 5));
		
		
		schedule.addIncident(i1);

		
		System.out.println(schedule.getIncidentsByPriority(Priority.HIGH));
		i1.dispatchIncident();
		System.out.println(schedule.getIncidentsByPriority(Priority.HIGH));

	}

}
