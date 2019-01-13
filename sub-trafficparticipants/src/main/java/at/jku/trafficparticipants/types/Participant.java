package at.jku.trafficparticipants.types;

import java.util.ArrayList;
import java.util.Random;

import at.jku.trafficparticipants.entity.Crossing;
import at.jku.trafficparticipants.entity.Edge;
import at.jku.trafficparticipants.entity.Node;
import at.jku.trafficparticipants.entity.Street;
import at.jku.trafficparticipants.util.Path;
import at.jku.trafficparticipants.util.Position;

public abstract class Participant implements Runnable {
	
	private class TrafficLightRecognition implements Runnable {

		@Override
		public void run() {
			
			
		}
	}
	
	private class ObstacleRecognition implements Runnable {

		@Override
		public void run() {
			
			
		}
	}

	private Position location;
	private Position goal;
	
	private Position currentPos;
	
	private Path path;
	
	private int speed; // metres/second
	
	public Participant(Position location, Position goal) {
		this.location = location;
		this.goal = goal;
		path = calculatePath();
		speed = 0;
	}
	
	public Participant() {
		(new Random()).ints();
	}
	
	public Position getCurrentPosition() {
		return currentPos;
	}
	
	public Path calculatePath() {
		
		ArrayList<Position> positions = new ArrayList<>();
		
		int dirX = ((Crossing)goal.getLocal()).getX() - ((Crossing)location.getLocal()).getX();
		int dirY = ((Crossing)goal.getLocal()).getY() - ((Crossing)location.getLocal()).getY();
		
		for (Node n = location.getLocal(); n == goal.getLocal();) {
		
		if (n instanceof Crossing) {
			
			Crossing next = (Crossing) n;
			
			for (Edge e : next.getEdges()) {
				
			}
			
			positions.add(new Position(next.getEdges().stream().min(
					(a, b) -> (int) (((Street)a).getLength() - ((Street)b).getLength())
			).get(), 0));
			
		} else {
			
			Street next = (Street) n;
			
			positions.add(new Position(next, 0));
			
			n = next.getEnd();
		}
		}
		
		path = new Path(positions);
		
		return path;
	}
	
	public void proceed() {
		
		ArrayList<Position> way = path.getPositions();
		
		int pathIndex = 0;
		
		currentPos = way.get(pathIndex);
		
		int distanceToTraverse = 0;
		int traversal = 0;
		
		while (currentPos != goal) {
			
			if (currentPos.getLocal() instanceof Street) {
				distanceToTraverse = (int) ((Street)currentPos.getLocal()).getLength(); 
			} else {
				currentPos = way.get(++pathIndex);
				continue;
			}
			
			if (traversal == distanceToTraverse) {
				currentPos = way.get(++pathIndex);
			}
			
			try {
				Thread.sleep(1000/speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			traversal++;
		}
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while (currentPos != location) {
			
			if (currentPos.getLocal() instanceof Street) {
				distanceToTraverse = (int) ((Street)currentPos.getLocal()).getLength(); 
			} else {
				currentPos = way.get(--pathIndex);
				continue;
			}
			
			if (traversal == distanceToTraverse) {
				currentPos = way.get(--pathIndex);
			}
			
			try {
				Thread.sleep(1000/speed);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			traversal++;
		}
	}
	
	public int getSpeed() {
		return speed;
	}
	
	@Override
	public void run() {
		proceed();
	}
	
	public void waitTrafficLight() {
		
	}
}
