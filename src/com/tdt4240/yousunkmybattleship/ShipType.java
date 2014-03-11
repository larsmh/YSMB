package com.tdt4240.yousunkmybattleship;

import sheep.graphics.Image;

public enum ShipType {
	AC_Carrier(5, 4), Battleship(4, 3), Submarine(3, 2), Destroyer(3, 1), Boat(
			2, 0);

	private int size, sprite;
	private Image shipImg;

	private ShipType(int size, int sprite) {
		this.size = size;
		this.sprite = sprite;
		switch (sprite) {
		case 4:
			shipImg = new Image(R.drawable.ac_carrier);
			break;
		case 3:
			shipImg = new Image(R.drawable.battleship);
			break;
		case 2:
			shipImg = new Image(R.drawable.sub);
			break;
		case 1:
			shipImg = new Image(R.drawable.destroyer);
			break;
		case 0:
			shipImg = new Image(R.drawable.boat);
			break;
		}
	}

	public int getSize() {
		return size;
	}

	public int getSprite() {
		return sprite;
	}

	public Image getImg() {
		return shipImg;
	}
}
