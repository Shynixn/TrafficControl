package at.jku.trafficparticipants.entity;

public class Street extends Edge {

	private String name;
	
	private double length;
	
	private double currentTrafficRate;
	private double maxTrafficRate;
	private double defaultTrafficRate;
	
	private double currentSpeed;
	private double maxSpeed;
	private double defaultSpeed;
	
	private Node start;
	private Node end;
	
	public double getCurrentTrafficRate() {
		return currentTrafficRate;
	}
	
	public double getCurrentSpeed() {
		return currentSpeed;
	}
	
	public String getName() {
		return name;
	}
	public double getLength() {
		return length;
	}
	public double getMaxTrafficRate() {
		return maxTrafficRate;
	}
	public double getDefaultTrafficRate() {
		return defaultTrafficRate;
	}
	public double getMaxSpeed() {
		return maxSpeed;
	}
	public double getDefaultSpeed() {
		return defaultSpeed;
	}
	
	@Override
	public Node getStart() {
		return start;
	}
	
	@Override
	public Node getEnd() {
		return end;
	}
}
