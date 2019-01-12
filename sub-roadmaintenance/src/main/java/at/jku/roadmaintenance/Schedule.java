package at.jku.roadmaintenance;
import java.util.ArrayList;

public class Schedule {

	private String period;
	private ArrayList<Incident> incidents;
	
	public Schedule(String period) {
		this.period = period;
		this.incidents = new ArrayList();
	}
	
	public void addIncident(Incident i) {
		this.incidents.add(i);
	}
	
	public ArrayList<Incident> getIncidentsByPriority(Priority prio) {
		
		ArrayList<Incident> list = new ArrayList<>();
		
		for (Incident i :this.incidents) {
			if (i.getPriority() == prio) {
				list.add(i);
			}
		}
		
		return list;
	}
}
