package com.tdt4240.yousunkmybattleship;

public class Ship {
	private int size;
	private int[] ship;

	private DirectionType direction;
	private boolean placedOnBoard;

	private boolean shipSunk;

	public Ship(int size, int[] ship) {
		this.size = size;
		this.ship = ship;
		this.shipSunk = false;
		
		this.direction = DirectionType.HORIZONTAL;
		this.placedOnBoard = false;
	}

	public boolean isSunk() {
		return this.shipSunk;
	}
	
	public boolean isPlaced() {
		return this.placedOnBoard;
	}

	public boolean isVertical() {
		if (direction == DirectionType.VERTICAL) {
			return true;
		} else {
			return false;
		}
	}
}
