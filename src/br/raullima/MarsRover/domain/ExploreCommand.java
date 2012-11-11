package br.raullima.MarsRover.domain;

public enum ExploreCommand {
	M(0),
	L(1),
	R(2);
	
	private int value;
	
	ExploreCommand(int value)
	{
		this.value = value;
	}
}
