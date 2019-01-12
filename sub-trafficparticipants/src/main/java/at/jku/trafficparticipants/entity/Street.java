package at.jku.trafficparticipants.entity;

public class Street extends Edge {

	private String name;
	
	private double length;
	
	private double currentTrafficRate;
	private double maxTrafficRate;
	
	private double currentSpeed;
	private double maxSpeed;
	
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
	public double getMaxSpeed() {
		return maxSpeed;
	}
}
