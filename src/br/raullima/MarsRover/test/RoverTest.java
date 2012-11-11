package br.raullima.MarsRover.test;

import static org.junit.Assert.*;

import org.junit.Test;

import br.raullima.MarsRover.domain.Direction;
import br.raullima.MarsRover.domain.Plateau;
import br.raullima.MarsRover.domain.Rover;
import br.raullima.MarsRover.exception.InvalidCommandStringException;
import br.raullima.MarsRover.exception.InvalidRoverLocationException;
import br.raullima.MarsRover.exception.NotDeployedRoverException;

public class RoverTest {
	
	@Test
	public void shouldInstantiateNewRoverOnPosition55N()
	{
		Rover rover = new Rover(5,5,Direction.N);
		assertEquals("5 5 N", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldInstantiateNewRoverOnPosition33EByString()
	{
		Rover rover = new Rover("3 3 N");
		assertEquals( "3 3 N", rover.getCurrentPosition());
	}
	
	@Test(expected = NotDeployedRoverException.class)
	public void shouldntLetNotDeployedRoverMove() throws NotDeployedRoverException, InvalidCommandStringException
	{
		Rover rover = new Rover("3 3 N");
		rover.setExploreCommands("M");
		rover.move();
	}
	
	@Test
	public void shouldTurnRightWhenFacingNorth() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10, 10);
		Rover rover = new Rover("5 5 N");
		rover.setExploreCommands("R");
		plateau.deployRover(rover);
		rover.move();
		
		assertEquals("5 5 E", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldTurnLeftWhenFacingNorth() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10, 10);
		Rover rover = new Rover("5 5 N");
		rover.setExploreCommands("L");
		plateau.deployRover(rover);
		rover.move();
		
		assertEquals("5 5 W", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldTurnRightWhenFacingEast() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10, 10);
		Rover rover = new Rover("5 5 E");
		rover.setExploreCommands("R");
		plateau.deployRover(rover);
		rover.move();
		
		assertEquals("5 5 S", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldTurnLeftWhenFacingEast() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10, 10);
		Rover rover = new Rover("5 5 E");
		rover.setExploreCommands("L");
		plateau.deployRover(rover);
		rover.move();
		
		assertEquals("5 5 N", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldTurnRightWhenFacingSouth() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10, 10);
		Rover rover = new Rover("5 5 S");
		rover.setExploreCommands("R");
		plateau.deployRover(rover);
		rover.move();
		
		assertEquals("5 5 W", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldTurnLeftWhenFacingSouth() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10, 10);
		Rover rover = new Rover("5 5 S");
		rover.setExploreCommands("L");
		plateau.deployRover(rover);
		rover.move();
		
		assertEquals("5 5 E", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldTurnRightWhenFacingWest() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10, 10);
		Rover rover = new Rover("5 5 W");
		rover.setExploreCommands("R");
		plateau.deployRover(rover);
		rover.move();
		
		assertEquals("5 5 N", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldTurnLeftWhenFacingWest() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10, 10);
		Rover rover = new Rover("5 5 W");
		rover.setExploreCommands("L");
		plateau.deployRover(rover);
		rover.move();
		
		assertEquals("5 5 S", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldMoveFacingNorth() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10, 10);
		Rover rover = new Rover("5 5 N");
		rover.setExploreCommands("M");
		plateau.deployRover(rover);
		rover.move();
		
		assertEquals("5 6 N", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldMoveFacingEast() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10, 10);
		Rover rover = new Rover("5 5 E");
		rover.setExploreCommands("M");
		plateau.deployRover(rover);
		rover.move();
		
		assertEquals("6 5 E", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldMoveFacingSouth() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10, 10);
		Rover rover = new Rover("5 5 S");
		rover.setExploreCommands("M");
		plateau.deployRover(rover);
		rover.move();
		
		assertEquals("5 4 S", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldMoveFacingWest() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10, 10);
		Rover rover = new Rover("5 5 W");
		rover.setExploreCommands("M");
		plateau.deployRover(rover);
		rover.move();
		
		assertEquals("4 5 W", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldMoveToPosition55N() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10,10);
		Rover rover = new Rover("1 1 N");
		plateau.deployRover(rover);
		rover.setExploreCommands("MMMMRMMMML");
		rover.move();
		assertEquals("5 5 N", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldntMoveToPositionGreaterThanMaxX() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(5, 5);
		Rover rover = new Rover("5 5 E");
		plateau.deployRover(rover);
		rover.setExploreCommands("MMMMM");
		rover.move();
		assertEquals("5 5 E", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldntMoveToPositionGreaterThanMaxY() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(10, 10);
		Rover rover = new Rover("10 10 N");
		plateau.deployRover(rover);
		rover.setExploreCommands("MMMMM");
		rover.move();
		assertEquals("10 10 N", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldntMoveToPositionLowerThanMinX() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(5, 5);
		Rover rover = new Rover("0 0 W");
		plateau.deployRover(rover);
		rover.setExploreCommands("MMMMM");
		rover.move();
		assertEquals("0 0 W", rover.getCurrentPosition());
	}
	
	@Test
	public void shouldntMoveToPositionLowerThanMinY() throws InvalidRoverLocationException, NotDeployedRoverException, InvalidCommandStringException
	{
		Plateau plateau = new Plateau(5, 5);
		Rover rover = new Rover("0 0 S");
		plateau.deployRover(rover);
		rover.setExploreCommands("MMMMM");
		rover.move();
		assertEquals("0 0 S", rover.getCurrentPosition());
	}
}
