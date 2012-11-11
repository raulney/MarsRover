package br.raullima.MarsRover.app;

import java.util.Scanner;

import br.raullima.MarsRover.domain.CommandParser;
import br.raullima.MarsRover.exception.InvalidCommandStringException;
import br.raullima.MarsRover.exception.InvalidRoverLocationException;
import br.raullima.MarsRover.exception.NotDeployedRoverException;

public class App {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out
				.println("Enter Rover instructions (end instructions with 'go')");
		String instructions = "";
		Scanner scanner = new Scanner(System.in);
		while (!instructions.contains("go")) {
			String nextLine = scanner.nextLine();
			if (!nextLine.isEmpty()) {
				instructions = instructions + nextLine.trim() + "\n";
			}
		}
		instructions = instructions.substring(0, 
				instructions.indexOf("go"));
		CommandParser parser = new CommandParser(instructions);
		try {
			System.out.println("Output: \n"+parser.run());
		} catch (InvalidRoverLocationException e) {
			e.printStackTrace();
		} catch (NotDeployedRoverException e) {
			e.printStackTrace();
		} catch (InvalidCommandStringException e) {
			e.printStackTrace();
		}

	}

}
