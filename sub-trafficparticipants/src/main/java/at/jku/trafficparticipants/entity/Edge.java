package at.jku.trafficparticipants.entity;

public abstract class Edge extends Node {

	private Node start;
	private Node end;
	
	public Node getStart() {
		return start;
	}
	
	public Node getEnd() {
		return end;
	}
}
