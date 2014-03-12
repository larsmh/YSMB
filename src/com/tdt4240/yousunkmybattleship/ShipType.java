package com.tdt4240.yousunkmybattleship;

import sheep.graphics.Image;

/**
 * Contains the different types of ships.
 * 
 * It restricts the ships that can be created to existing ships. Each type of
 * ship has a number to be identify on the players' boards.
 * 
 */

public enum ShipType {
	AC_Carrier(5, 4), Battleship(4, 3), Submarine(3, 2), Destroyer(3, 1), Boat(
			2, 0);

	/**
	 * The size attribute is the ship length which depends on the ship type.
	 * The sprite attribute is an integer that indicates the ship type.
	 * 
	 */
	private int size, sprite;

	/**
	 * The vertical and horizontal images for each ship.
	 * 
	 */
	private Image shipImgVert, shipImgHor;

	private ShipType(int size, int sprite) {
		this.size = size;
		this.sprite = sprite;
		switch (sprite) {
		case 4:
			shipImgHor = new Image(R.drawable.ac_carrier);
			shipImgVert = new Image(R.drawable.ac_carrier2);
			break;
		case 3:
			shipImgHor = new Image(R.drawable.battleship2);
			shipImgVert = new Image(R.drawable.battleship);
			break;
		case 2:
			shipImgHor = new Image(R.drawable.sub2);
			shipImgVert = new Image(R.drawable.sub);
			break;
		case 1:
			shipImgHor = new Image(R.drawable.destroyer);
			shipImgVert = new Image(R.drawable.destroyer2);
			break;
		case 0:
			shipImgHor = new Image(R.drawable.boat);
			shipImgVert = new Image(R.drawable.boat2);
			break;
		}
	}

	public int getSize() {
		return size;
	}

	public int getSprite() {
		return sprite;
	}

	public Image getImgVert() {
		return shipImgVert;
	}

	public Image getImgHor() {
		return shipImgHor;
	}
}
