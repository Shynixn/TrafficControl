package at.jku.roadmaintenance;

public class Unit {

	private String name;
	private int quantity;
	
	public Unit(String name) {
		this.name = name;
		this.quantity = 1;
	}
	
	public Unit(String name,
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
