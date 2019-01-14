package at.jku.roadmaintenance;

public class StaffMember {

	public String name;
	
	public StaffMember(String name) {
		this.name = name;
	}
	
	public String toString() {
		return this.name;
	}

	public String getName() {
		return name;
	}
}
