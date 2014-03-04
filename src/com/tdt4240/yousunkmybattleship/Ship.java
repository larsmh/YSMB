package com.tdt4240.yousunkmybattleship;

import com.tdt4240.yousunkmybattleship.Constants.DirectionType;

public class Ship {
	private final String VERTICAL = "VERTICAL";
	private final String HORIZONTAL = "HORIZONTAL";
	
	private int size;
	//list of ships
	private int[] ship;

	private int posX, posY;
	

	private DirectionType direction;
	private boolean placedOnBoard;
    
	//gives opponent points.
	private boolean shipSunk;

	public Ship(int size, int[] ship) {
		this.size = size;
		
		/*this.ship = new int[size];
		for (int i = 0; i < size; i++) {
			this.ship[i] = ship[i];
		}*/
		
		this.ship = ship;
		
		this.shipSunk = false;
		
		this.direction = DirectionType.HORIZONTAL;
		this.placedOnBoard = false;
	}

	public boolean isSunk() {
		return this.shipSunk;
	}

	public boolean isVertical() {
		if (direction == DirectionType.VERTICAL) {
			return true;
		} else {
			return false;
		}
	}
	
	public void placeShip(int posX, int posY, String direction) {
		
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
}
