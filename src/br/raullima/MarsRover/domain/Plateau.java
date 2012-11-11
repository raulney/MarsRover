package br.raullima.MarsRover.domain;

import java.util.ArrayList;
import java.util.List;

import br.raullima.MarsRover.exception.InvalidRoverLocationException;

public class Plateau {

	private int maxY;
	private int maxX;
	private List<Rover> rovers;

	public Plateau(int maxX, int maxY) {
		this.maxX = maxX;
		this.maxY = maxY;
	}

	public void deployRover(Rover rover) throws InvalidRoverLocationException {
		if (rover.getX() <= this.maxX && rover.getX() >= 0
				&& rover.getY() <= this.maxY && rover.getY() >= 0) {
			this.getRovers().add(rover);
			rover.setPlateau(this);
		} else {
			throw new InvalidRoverLocationException(
					"Invalid rover position on this Plateau");
		}
	}

	public List<Rover> getRovers() {
		if (this.rovers == null) {
			this.rovers = new ArrayList<Rover>();
		}
		return this.rovers;
	}

	public int getMaxX() {
		return this.maxX;
	}

	public int getMaxY() {
		return this.maxY;
	}

	public String getRoversPositions() {
		String ret = "";
		for (Rover rover : rovers) {
			ret = ret + rover.getCurrentPosition();
			ret = ret + "\n";
		}
		return ret + "==========";
	}

	public boolean isValidPosition(int x, int y) {
		return (x <= this.getMaxX() && y <= this.getMaxY() && x >= 0 && y >= 0 && this
				.IsEmptyPosition(x, y));
	}

	private boolean IsEmptyPosition(int x, int y) {
		boolean valid = true;
		for (Rover rover : rovers) {
			if (rover.getX() == x && rover.getY() == y) {
				valid = false;
			}
		}
		return valid;
	}
}
