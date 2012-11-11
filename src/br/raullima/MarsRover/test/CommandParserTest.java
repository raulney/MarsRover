package br.raullima.MarsRover.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.raullima.MarsRover.domain.CommandParser;
import br.raullima.MarsRover.exception.InvalidCommandStringException;
import br.raullima.MarsRover.exception.InvalidRoverLocationException;
import br.raullima.MarsRover.exception.NotDeployedRoverException;

public class CommandParserTest {

	@Test
	public void shouldSetCommands()
	{
		String command = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM";
		String[] commands = {"5 5","1 2 N","LMLMLMLMM","3 3 E","MMRMMRMRRM"};
		CommandParser parser = new CommandParser(command);
		assertArrayEquals(commands, parser.getCommands());
	}
	
	@Test
	public void shouldShowRoversFinalCoordinatesAndHeading() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		String command = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMMRMMRMRRM";
		CommandParser parser = new CommandParser(command);
		assertEquals("1 3 N\n5 1 E\n==========", parser.run());
	}
	
	@Test(expected=InvalidCommandStringException.class)
	public void shouldntExecuteWithLessThenMinimalCommands() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		String command = "5 5\n1 2 N";
		CommandParser parser = new CommandParser(command);
		parser.run();
	}
	
	@Test(expected=InvalidCommandStringException.class)
	public void shouldntExecuteWithInvalidCommands() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		String command = "5 5\n1 2 N\nLMLMLMLMM\n3 3 E\nMWRMMRMRRM";
		CommandParser parser = new CommandParser(command);
		parser.run();
	}

}
