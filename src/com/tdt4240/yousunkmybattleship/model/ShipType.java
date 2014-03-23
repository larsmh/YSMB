package com.tdt4240.yousunkmybattleship.model;

import com.tdt4240.yousunkmybattleship.R;
import sheep.graphics.Image;

/**
 * Contains the different types of ships.
 * 
 * It restricts the ships that can be created to existing ships. Each type of
 * ship has a number to be identified on the players' boards.
 * 
 */

public enum ShipType {
	AC_Carrier(5, 0), Battleship(4, 1), Submarine(3, 2), Destroyer(3, 3), Boat(
			2, 4);

	/**
	 * The size attribute is the ship length which depends on the ship type. The
	 * sprite attribute is an integer that indicates the ship type.
	 * 
	 */
	private int size, id;

	/**
	 * The vertical and horizontal images for each ship.
	 * 
	 */
	private Image shipImgVert, shipImgHor;

	/**
	 * The constructor of the class. It fills automatically the size, sprite and
	 * image of the new ships.
	 * 
	 */
	private ShipType(int size, int sprite) {
		this.size = size;
		this.id = sprite;
		switch (sprite) {
		case 0:
			shipImgHor = new Image(R.drawable.ac_carrier);
			shipImgVert = new Image(R.drawable.ac_carrier2);
			break;
		case 1:
			shipImgHor = new Image(R.drawable.battleship);
			shipImgVert = new Image(R.drawable.battleship2);
			break;
		case 2:
			shipImgHor = new Image(R.drawable.sub);
			shipImgVert = new Image(R.drawable.sub2);
			break;
		case 3:
			shipImgHor = new Image(R.drawable.destroyer);
			shipImgVert = new Image(R.drawable.destroyer2);
			break;
		case 4:
			shipImgHor = new Image(R.drawable.boat);
			shipImgVert = new Image(R.drawable.boat2);
			break;
		}
	}

	public int getSize() {
		return size;
	}

	public int getID() {
		return id;
	}

	public Image getImgVert() {
		return shipImgVert;
	}

	public Image getImgHor() {
		return shipImgHor;
	}
}
