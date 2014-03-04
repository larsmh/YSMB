package com.tdt4240.yousunkmybattleship;

public class Player {
	private String name;
	
	// The number of non sunk ships of the player
	private int shipsRemaining;
	
	private PlayerState playerState;
	
	public Player(String name) {
		this.name = name;
		shipsRemaining = Constants.NUMBER_SHIPS;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void changePlayerState() {
		
	}
	
	public PlayerState getPlayerState() {
		return playerState;
	}
	
	public void shipSunk() {
		shipsRemaining -= 1;
		
		if(shipsRemaining == 0) {
			// game over
		}
	}
}
