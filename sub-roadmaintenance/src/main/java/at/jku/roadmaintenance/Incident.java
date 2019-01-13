package at.jku.roadmaintenance;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Incident {

	private Status status;
	private Type type;
	private Priority priority;
	private LocalDate created;
	private LocalDate begin;
	private LocalDate end;
	
	private ArrayList<RoadSegment> affectedRoadSegments;
	private ArrayList<WorkUnit> assignedUnits;
	
	private StaffMember assignee;
	
	public Incident(
			Type type,
			Priority priority,
			StaffMember assignee) {
		//may be caused by the TCD module
		this.status = Status.CREATED;
		this.type = type;
		this.priority = priority;
		this.created = LocalDate.now();
		this.assignee = assignee;
		this.affectedRoadSegments = new ArrayList();
		this.assignedUnits = new ArrayList();
		
	}
	
	public void addRoadSegment(RoadSegment segment) {
		this.affectedRoadSegments.add(segment);
	}
	
	public void addUnit(WorkUnit unit) {
		this.assignedUnits.add(unit);
	}
	
	public void setBeginDate(LocalDate begin) {
		this.begin = begin;
	}
	
	public void setEndDate(LocalDate end) {
		this.end = end;
	}
	
	public Priority getPriority() {
		return this.priority;
	}
	
	public boolean dispatchIncident() {
		
		//all information and resources available?
		if ((this.begin != null) && (this.end != null) &&
			(this.affectedRoadSegments != null) && (this.assignedUnits != null)) {
				
			for (WorkUnit u : this.assignedUnits)
			{
				if (Resources.getQuantity(u.getName()) < u.getQuantity())
					return false;
			}
			for (WorkUnit u : this.assignedUnits)
			{
				Resources.withdrawResources(u.getName(), u.getQuantity());
			}
			
			//inform CS module
			//inform TP module
			this.status = Status.IN_PROGESS;		
			return true;
		}
		
		return false;
	}
	
	public boolean pauseIncident() {
		
		for (WorkUnit u : this.assignedUnits)
		{
			Resources.returnResources(u.getName(), u.getQuantity());
		}
		
		//inform CS module
		//inform TP module
		this.status = Status.PAUSED;
		
		return true;
	}
	
	public boolean finishIncident() {
		
		for (WorkUnit u : this.assignedUnits)
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
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyy");
		
		result += "Status " + this.status + " - Type " + this.type + " - Priority " + this.priority + "\n";
		result += "Responsible staff member: " + this.assignee + "\n";
		result += "Created: " + dtf.format(this.created) +  "\n";
		result += "Begin: " + dtf.format(this.begin) +  "\n";
		result += "End: " + dtf.format(this.end) +  "\n";
		result += "Affected road segments: \n\n";
		
		for (RoadSegment seg : this.affectedRoadSegments) {
			result += seg + "\n";
		}
		
		result += "Assigned maintenance units: \n";
		for (WorkUnit u : this.assignedUnits) {
			result += u + "\n";
		}
		
		return result;
	}
}
