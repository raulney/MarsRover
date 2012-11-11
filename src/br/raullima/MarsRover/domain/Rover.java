package br.raullima.MarsRover.domain;

import java.util.ArrayList;
import java.util.List;

import br.raullima.MarsRover.exception.InvalidCommandStringException;
import br.raullima.MarsRover.exception.NotDeployedRoverException;

public class Rover {

	private int x;
	private int y;
	private Direction direction;
	private List<ExploreCommand> exploreCommands;
	private Plateau plateau;

	public Rover(int i, int j, Direction n) {
		this.x = i;
		this.y = j;
		this.direction = n;
	}

	public Rover(String string) {
		String[] arr = string.split(" ");
		this.x = Integer.parseInt(arr[0]);
		this.y = Integer.parseInt(arr[1]);
		this.direction = Direction.valueOf(arr[2]);
	}

	public void setX(int i) {
		this.x = i;
	}

	public void setY(int i) {
		this.y = i;
	}

	public void setDirection(Direction n) {
		this.direction = n;
	}

	public String getCurrentPosition() {
		return this.getX() + " " + this.getY() + " "
				+ this.getDirection().toString();
	}

	public int getY() {
		return this.y;
	}

	public int getX() {
		return this.x;
	}

	public Direction getDirection() {
		return this.direction;
	}

	public void setExploreCommands(String string) throws InvalidCommandStringException {
		String[] arr = string.split("");
		for (String command : arr) {
			if (command.equalsIgnoreCase("M") || command.equalsIgnoreCase("L")
					|| command.equalsIgnoreCase("R")) {
				this.getExploreCommands().add(ExploreCommand.valueOf(command));
			}
			else if(!command.isEmpty())
			{
				throw new InvalidCommandStringException("Invalid movement command: "+command);
			}
		}
	}

	public List<ExploreCommand> getExploreCommands() {
		if (this.exploreCommands == null) {
			this.exploreCommands = new ArrayList<ExploreCommand>();
		}
		return this.exploreCommands;
	}

	public void move() throws NotDeployedRoverException, InvalidCommandStringException {
		if (this.plateau != null) {
			for (ExploreCommand command : this.exploreCommands) {
				this.excuteCommand(command);
			}
		}else
		{
			throw new NotDeployedRoverException("Can´t move a rover that is not in a plateau");
		}
	}

	private void excuteCommand(ExploreCommand command) throws InvalidCommandStringException {
		int nextRoverXPosition = this.x;
		int nextRoverYPosition = this.y;
		switch (command) {
		case M:
			switch (this.direction) {
			case N:
				nextRoverYPosition++;
				break;
			case S:
				nextRoverYPosition--;
				break;
			case E:
				nextRoverXPosition++;
				break;
			case W:
				nextRoverXPosition--;
				break;
			}
			break;
		case L:
			switch (this.direction) {
			case N:
				this.direction = Direction.W;
				break;
			case S:
				this.direction = Direction.E;
				break;
			case E:
				this.direction = Direction.N;
				break;
			case W:
				this.direction = Direction.S;
				break;
			}
			break;
		case R:
			switch (this.direction) {
			case N:
				this.direction = Direction.E;
				break;
			case S:
				this.direction = Direction.W;
				break;
			case E:
				this.direction = Direction.S;
				break;
			case W:
				this.direction = Direction.N;
				break;
			}
			break;
		default:
			throw new InvalidCommandStringException("Invalid movement command: "+command.toString());
		}
		if (this.getPlateau().isValidPosition(nextRoverXPosition, nextRoverYPosition)) {
			this.x = nextRoverXPosition;
			this.y = nextRoverYPosition;
		}
	}

	private Plateau getPlateau() {
		return this.plateau;
	}

	public void setPlateau(Plateau plateau) {
		this.plateau = plateau;
	}
}
