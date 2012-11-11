package br.raullima.MarsRover.domain;

import br.raullima.MarsRover.exception.InvalidCommandStringException;
import br.raullima.MarsRover.exception.InvalidRoverLocationException;
import br.raullima.MarsRover.exception.NotDeployedRoverException;

public class CommandParser {

	private String[] commands;

	public CommandParser(String command) {
		this.commands = command.split("\n");
	}

	public String[] getCommands() {
		return this.commands;
	}

	public String run() throws InvalidRoverLocationException,
			NotDeployedRoverException, InvalidCommandStringException {
		if (commands.length >= 3) {
			try {
				String[] plateuMaxCoordinates = commands[0].split(" ");
				Plateau plateau = new Plateau(
						Integer.parseInt(plateuMaxCoordinates[0]),
						Integer.parseInt(plateuMaxCoordinates[1]));

				for (int i = 1; i < commands.length; i = i + 2) {
					Rover rover = new Rover(commands[i]);
					rover.setExploreCommands(commands[i + 1]);
					plateau.deployRover(rover);
					rover.move();
				}

				return plateau.getRoversPositions();
			} catch (Exception ex) {
				throw new InvalidCommandStringException("Invalid command string: "+ex.getMessage());
			}
		} else {
			throw new InvalidCommandStringException("Incomplete or empty command string");
		}
	}

}
