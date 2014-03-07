package com.tdt4240.yousunkmybattleship;

import com.tdt4240.yousunkmybattleship.Constants.DirectionType;

public class Ship {	
	private ShipType type;

	// The position of the top left square of the ship in the grid
	private int posX, posY;
	private int hits;
	private DirectionType direction;
	private boolean placedOnBoard;
    
	private boolean shipSunk;

	public Ship(ShipType type) {
		this.type=type;
		hits=0;
		shipSunk = false;
		direction = DirectionType.HORIZONTAL;
		placedOnBoard = false;
	}

	public boolean isSunk() {
		return this.shipSunk;
	}
	
	public void shipHit(){
		hits++;
		if(hits>=type.getSize())
			shipSunk=true;
	}

	public boolean isVertical() {
		if (direction == DirectionType.VERTICAL)
			return true;
		return false;
	}
	
	public void placeShip(int posX, int posY, DirectionType direction) {
		this.posX=posX;
		this.posY=posY;
		this.direction=direction;
		placedOnBoard=true;
	}
	
	public int getPosX() {
		return this.posX;
	}
	
	public int getPosY() {
		return this.posY;
	}
}
