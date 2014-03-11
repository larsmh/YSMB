package com.tdt4240.yousunkmybattleship;

import android.graphics.Paint;
import sheep.game.Game;

public class Constants {
	public static Game game;
	public static Player p1, p2, p;
	
	/* Window sizes */
	public static float WINDOW_WIDTH = 0;
	public static float WINDOW_HEIGHT = 0;

	/* Grid properties */
	public static int GRID_WIDTH = 10;
	public static int GRID_HEIGHT = 10;
	public static float TILE_SIZE = 108;
	public static float START_OF_GRID = 840;

	public static int NUMBER_SHIPS = 5;

	public static final int MAX_CLICK_DURATION = 200;
	
	public static Paint[] paint;

	public enum DirectionType {
		HORIZONTAL, VERTICAL;
	}


	public static Player getOther() {
		if (p == p1)
			return p2;
		else
			return p1;
	}
	public static void changeTurn(){
		p=getOther();
	}
}
