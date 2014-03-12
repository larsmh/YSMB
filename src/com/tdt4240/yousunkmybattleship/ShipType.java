package com.tdt4240.yousunkmybattleship;

import sheep.graphics.Image;

public enum ShipType {
	AC_Carrier(5, 4), Battleship(4, 3), Submarine(3, 2), Destroyer(3, 1), Boat(
			2, 0);

	private int size, sprite;
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
