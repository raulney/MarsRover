package br.raullima.MarsRover.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.raullima.MarsRover.domain.Plateau;
import br.raullima.MarsRover.domain.Rover;
import br.raullima.MarsRover.exception.InvalidCommandStringException;
import br.raullima.MarsRover.exception.InvalidRoverLocationException;
import br.raullima.MarsRover.exception.NotDeployedRoverException;

public class PlateauTest {
	
	@Test
	public void shouldReturnAllRoversPosition() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(5, 5);
		
		Rover rover1 = new Rover("1 2 N");
		rover1.setExploreCommands("LMLMLMLMM");
		plateau.deployRover(rover1);
		rover1.move();
		
		Rover rover2 = new Rover("3 3 E");
		rover2.setExploreCommands("MMRMMRMRRM");
		plateau.deployRover(rover2);
		rover2.move();
		
		assertEquals("1 3 N\n5 1 E\n==========", plateau.getRoversPositions());
	}
	
	@Test(expected = InvalidRoverLocationException.class)
	public void shouldntDeplyRoverOnPositionGreatherThenMax() throws InvalidRoverLocationException
	{
		Plateau plateau = new Plateau(5, 5);
		
		Rover rover = new Rover("6 6 N");
		plateau.deployRover(rover);
	}
	
	@Test(expected = InvalidRoverLocationException.class)
	public void shouldntDeplyRoverOnPositionLowerThanMin() throws InvalidRoverLocationException
	{
		Plateau plateau = new Plateau(5, 5);
		
		Rover rover = new Rover("-1 -1 N");
		plateau.deployRover(rover);
	}
	
	@Test
	public void shouldntLetRoversColide() throws NotDeployedRoverException, InvalidRoverLocationException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(5, 5);
		
		//Move first rover to 5 5 N
		Rover rover1 = new Rover("1 1 N");
		rover1.setExploreCommands("MMMMRMMMML");
		plateau.deployRover(rover1);
		rover1.move();
		
		//Try to move second rover to 5 5 N
		Rover rover2 = new Rover("1 1 N");
		rover2.setExploreCommands("MMMMRMMMML");
		plateau.deployRover(rover2);
		rover2.move();
		
		//Try to move third rover to 5 5 N
		Rover rover3 = new Rover("1 1 N");
		rover3.setExploreCommands("RMMMMLMMMM");
		plateau.deployRover(rover3);
		rover3.move();
		
		//Second rover should stay at 4 5 N, because 5 5 N is occupied by the first rover.
		//Third rover should stay at 5 4 N, because 5 5 N is occupied by the first rover.
		assertEquals("5 5 N\n4 5 N\n5 4 N\n==========", plateau.getRoversPositions());
	}

}
