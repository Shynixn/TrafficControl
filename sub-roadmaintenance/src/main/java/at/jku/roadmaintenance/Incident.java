package at.jku.roadmaintenance;
import java.util.ArrayList;
import java.util.Calendar;

public class Incident {

	private Status status;
	private Type type;
	private Priority priority;
	private Calendar created;
	
	private ArrayList<RoadSegment> affectedRoadSegments;
	private ArrayList<Unit> assignedUnits;
	
	private StaffMember assignee;
	
	public Incident(
			Type type,
			Priority priority,
			StaffMember assignee) {
		//may be caused by the TCD module
		this.status = Status.CREATED;
		this.type = type;
		this.priority = priority;
		this.created = Calendar.getInstance();
		this.assignee = assignee;
		this.affectedRoadSegments = new ArrayList();
		this.assignedUnits = new ArrayList();
		
	}
	
	public void addRoadSegment(RoadSegment segment) {
		this.affectedRoadSegments.add(segment);
	}
	
	public void addUnit(Unit unit) {
		this.assignedUnits.add(unit);
	}
	
	public Priority getPriority() {
		return this.priority;
	}
	
	public boolean dispatchIncident() {
		//all information and resources available?
		
		for (Unit u : this.assignedUnits)
		{
			if (Resources.getQuantity(u.getName()) < u.getQuantity())
				return false;
		}
		for (Unit u : this.assignedUnits)
		{
			Resources.withdrawResources(u.getName(), u.getQuantity());
		}
		
		//inform CS module
		//inform TP module
		this.status = Status.IN_PROGESS;
		
		return true;
	}
	
	public boolean pauseIncident() {
		
		for (Unit u : this.assignedUnits)
		{
			Resources.returnResources(u.getName(), u.getQuantity());
		}
		
		//inform CS module
		//inform TP module
		this.status = Status.PAUSED;
		
		return true;
	}
	
	public boolean finishIncident() {
		
		for (Unit u : this.assignedUnits)
		{
			Resources.returnResources(u.getName(), u.getQuantity());
		}
		
		//inform CS module
		//inform TP module
		this.status = Status.FINISHED;
		
		return true;
	}
	
	public String toString() {
		String result = "";
		
		result += "Status " + this.status + " - Type " + this.type + " - Priority " + this.priority + "\n";
		result += "Responsible staff member: " + this.assignee + "\n";
		result += " \n";
		result += "Affected road segments: \n";
		
		for (RoadSegment seg : this.affectedRoadSegments) {
			result += seg + "\n";
		}
		
		result += "Assigned maintenance units: \n";
		for (Unit u : this.assignedUnits) {
			result += u + "\n";
		}
		
		return result;
	}
}
