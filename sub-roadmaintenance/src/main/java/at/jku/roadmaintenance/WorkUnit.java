package at.jku.roadmaintenance;

public class WorkUnit {

	private String name;
	private int quantity;
	
	public WorkUnit(String name) {
		this.name = name;
		this.quantity = 1;
	}
	
	public WorkUnit(String name,
					int quantity) {
		this.name = name;
		this.quantity = quantity;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public String toString() {
		return "Type: " + name + ", Quantity: " + quantity;
	}
}
