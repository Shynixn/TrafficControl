package at.jku.trafficparticipants.util;

import at.jku.trafficparticipants.entity.Node;

public class Position {

	private Node local;
	
	private int distance;
	
	public Position(Node local, int distance) {
		this.local = local;
		this.distance = distance;
	}
	
	public Node getLocal() {
		return local;
	}
	
	public int getDistance() {
		return distance;
	}
}
