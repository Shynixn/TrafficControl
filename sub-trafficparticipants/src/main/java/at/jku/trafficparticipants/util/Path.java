package at.jku.trafficparticipants.util;

import java.util.ArrayList;

public class Path {

	private ArrayList<Position> positions;
	
	public Path(ArrayList<Position> positions) {
		this.positions = positions;
	}
	
	public ArrayList<Position> getPositions() {
		return positions;
	}
}
