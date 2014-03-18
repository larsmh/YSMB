package com.tdt4240.yousunkmybattleship;

import com.tdt4240.yousunkmybattleship.model.Player;

import android.content.Context;
import android.graphics.Paint;
import sheep.game.Game;

public class Constants {
	public static Game game;
	public static Player p1, p2, p;

	/* Window sizes */
	public static float WINDOW_WIDTH;
	public static float WINDOW_HEIGHT;

	/* Grid properties */
	public static int GRID_WIDTH = 10;
	public static int GRID_HEIGHT = 10;
	public static float TILE_SIZE;
	public static float START_OF_GRID;

	public static int NUMBER_SHIPS = 5;
	public static int NUMBER_BOMBS = 4;

	public static final int MAX_CLICK_DURATION = 200;


	public enum DirectionType {
		HORIZONTAL, VERTICAL;
	}

	public static Player getOther() {
		if (p == p1)
			return p2;
		else
			return p1;
	}

	public static void changeTurn() {
		p = getOther();
	}
	public static void setDimensions(){
		TILE_SIZE = WINDOW_WIDTH/GRID_WIDTH;
		START_OF_GRID = WINDOW_HEIGHT-(TILE_SIZE*GRID_HEIGHT);
	}
}
