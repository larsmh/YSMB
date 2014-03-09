package com.tdt4240.yousunkmybattleship;

import com.tdt4240.yousunkmybattleship.R;
import android.graphics.Canvas;
import sheep.game.State;
import sheep.graphics.Image;

public class ShipPlacementState extends State {
	Image bg = new Image(R.drawable.menu_bg);
	Ship[] ships;

	public ShipPlacementState() {
		createShips();
	}

	private void createShips() {
		ships = new Ship[Constants.NUMBER_SHIPS];
		ships[0] = new Ship(ShipType.Boat);
		ships[1] = new Ship(ShipType.Destroyer);
		ships[2] = new Ship(ShipType.Submarine);
		ships[3] = new Ship(ShipType.Battleship);
		ships[4] = new Ship(ShipType.AC_Carrier);
	}

	public void draw(Canvas canvas) {
		bg.draw(canvas, 0, 0);

	}
}
