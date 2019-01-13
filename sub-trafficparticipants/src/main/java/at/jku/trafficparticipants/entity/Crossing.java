package at.jku.trafficparticipants.entity;

import java.util.ArrayList;

public class Crossing extends Node {
	
	private int x;
	private int y;
	
	private ArrayList<Edge> edges;
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public ArrayList<Edge> getEdges() {
		return edges;
	}
}
