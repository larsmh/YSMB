package com.tdt4240.yousunkmybattleship;

public class Player {
	private String name;
	private int bombsPrTurn, totalHits;
	
	// The number of non sunk ships of the player
	private int shipsRemaining;
	private char board[][];
	private boolean drops[][];
	
	public Player(String name) {
		this.name = name;
		shipsRemaining = Constants.NUMBER_SHIPS;
		board = new char[Constants.GRID_HEIGHT][Constants.GRID_WIDTH];
		drops = new boolean[Constants.GRID_HEIGHT][Constants.GRID_WIDTH];
	}
	
	public String getName() {
		return this.name;
	}
	
	public void changePlayerState() {
		
	}
	
	public void shipSunk() {
		shipsRemaining -= 1;
		
		if(shipsRemaining == 0) {
			// game over
		}
	}

}
