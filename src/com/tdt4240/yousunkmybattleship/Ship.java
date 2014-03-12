package com.tdt4240.yousunkmybattleship;

import com.tdt4240.yousunkmybattleship.Constants.DirectionType;

/**
 * Contains the relevant methods and attributes linked to the ships.
 * 
 * Each ship has a type (from a closed list), a position on the player's grid, a
 * direction, etc.
 * 
 */

public class Ship {
	private ShipType type;

	/**
	 * The position of the top left square of the ship in the grid.
	 * 
	 */
	private int posX, posY;

	/**
	 * Number of the ship's block hit.
	 * 
	 */
	private int hits;

	/**
	 * Indicates if the ship is horizontal or vertical.
	 * 
	 */
	private DirectionType direction;

	/**
	 * Indicates if the ship is placed on the board.
	 * 
	 */
	private boolean placedOnBoard;

	/**
	 * Indicates if the ship is sunk.
	 * 
	 */
	private boolean shipSunk;

	public Ship(ShipType type) {
		this.type = type;
		hits = 0;
		shipSunk = false;
		direction = DirectionType.HORIZONTAL;
		placedOnBoard = false;
		posX = 0;
		posY = 0;
	}

	public boolean isSunk() {
		return this.shipSunk;
	}

	public void shipHit() {
		hits++;
		if (hits >= type.getSize())
			shipSunk = true;
	}

	public void changeDirection() {
		if (isVertical())
			direction = DirectionType.HORIZONTAL;
		else
			direction = DirectionType.VERTICAL;
	}

	public boolean isVertical() {
		return (direction == DirectionType.VERTICAL);
	}

	public void placeShip(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		placedOnBoard = true;
	}

	public int getPosX() {
		return this.posX;
	}

	public int getPosY() {
		return this.posY;
	}

	public ShipType getType() {
		return type;
	}

}
