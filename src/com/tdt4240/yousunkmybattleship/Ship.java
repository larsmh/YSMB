package com.tdt4240.yousunkmybattleship;

public class Ship {
	private int size;
	private int[] ship;
	private boolean shipSunk;
	
	public Ship(int size, int[] ship) {
		this.size = size;
		this.ship = ship;
		this.shipSunk = false;
	}
	
	private boolean isSunk() {
		return this.shipSunk;
	}
	
}
