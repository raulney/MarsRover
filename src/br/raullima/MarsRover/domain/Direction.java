package br.raullima.MarsRover.domain;

public enum Direction {
	N(0),
	S(1),
	E(2),
	W(3);
	
	private int value;
	
	Direction(int value)
	{
		this.value = value;
	}
}
