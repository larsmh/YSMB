package com.tdt4240.yousunkmybattleship;

import sheep.game.Game;

public class Constants {
	public static Game game;
	public static Player p1, p2;
	
	/* Window sizes */
	public static int WINDOW_WIDTH  = 0;
	public static int WINDOW_HEIGHT = 0;
	
	/* Grid properties */
	public static int GRID_WIDTH = 10;
	public static int GRID_HEIGHT = 10;
	
	public static int NUMBER_SHIPS = 5;
	
	public static Player getOther(Player p){
		if(p==p1)
			return p2;
		else
			return p1;
	}
}
