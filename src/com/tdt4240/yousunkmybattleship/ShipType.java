package com.tdt4240.yousunkmybattleship;

public enum ShipType {
	AC_Carrier(5, 4), Battleship(4, 3), Submarine(3, 2), Destroyer(3, 1), Boat(2, 0);
	
	private int size, sprite;
	
	private ShipType(int size, int sprite){
		this.size=size;
		this.sprite=sprite;
	}
	
	public int getSize(){
		return size;
	}
	
	public int getSprite(){
		return sprite;
	}
}
