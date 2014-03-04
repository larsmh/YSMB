package com.tdt4240.yousunkmybattleship;

public class Ship {
	private final String VERTICAL = "VERTICAL";
	private final String HORIZONTAL = "HORIZONTAL";
	private String direction;
	
	private int size;
	private int[] ship;
	private int posX, posY;
	
	private boolean shipSunk;
	
	public Ship(int size, int[] ship) {
		this.size = size;
		
		/*this.ship = new int[size];
		for (int i = 0; i < size; i++) {
			this.ship[i] = ship[i];
		}*/
		
		this.ship = ship;
		
		this.shipSunk = false;
	}
	
	public boolean isSunk() {
		return this.shipSunk;
	}
	
	public void setVertical() {
		this.direction = VERTICAL;
	}
	
	public void setHorizontal() {
		this.direction = HORIZONTAL;
	}
	
	public boolean isVertical() {
		if (this.direction == VERTICAL) {
			return true;
		}
		return false;
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
