package at.jku.trafficparticipants.types;

import java.util.ArrayList;

import at.jku.trafficparticipants.util.Position;

public class PublicTransport extends Car {

	public PublicTransport(Position location, Position goal) {
		super(location, goal);
		// TODO Auto-generated constructor stub
	}

	private ArrayList<Pedestrian> passengers;
	
	public void notifyPassengers() {
		
		for (Pedestrian p : passengers) {
			
		}
	}
}
