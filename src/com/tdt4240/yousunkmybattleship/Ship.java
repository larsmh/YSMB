package com.tdt4240.yousunkmybattleship;

public class Ship {
	private int size;
	private int[] ship;
	
	private String direction;
	
	private boolean shipSunk;
	
	public Ship(int size, int[] ship) {
		this.size = size;
		this.ship = ship;
		this.shipSunk = false;
	}
	
	public boolean isSunk() {
		return this.shipSunk;
	}
	
	public boolean isVertical() {
		if (direction == "VERTICAL") {
			return true;
		}
		return false;
	}
}
