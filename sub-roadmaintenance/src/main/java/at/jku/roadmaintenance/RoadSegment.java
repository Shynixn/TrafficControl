package at.jku.roadmaintenance;

public class RoadSegment {

	private String id;
	private int impairment;
	
	public RoadSegment(String id,
			int impairment) {
		this.id = id;
		this.impairment = impairment;
	}
	
	public String toString() {
		return "ID : " + this.id + " | Grade of impairment: " + this.impairment;
	}
	
}
