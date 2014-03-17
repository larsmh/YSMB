package com.tdt4240.yousunkmybattleship;

import android.graphics.Paint;
import sheep.graphics.Image;

public class Graphics {
	public static Paint[] buttonPaint = new Paint[2];
	public static Paint paint = new Paint();
	public static Paint warningPaint = new Paint();
	
	public static Image board = new Image(R.drawable.board);
	public static Image bg = new Image(R.drawable.menu_bg);

	public static Image instruction1 = new Image(R.drawable.instruction1);
	public static Image instruction2 = new Image(R.drawable.instruction2);

	public static Image water_splash = new Image(R.drawable.water_splash);
	public static Image bomb_site = new Image(R.drawable.bomb_site);
}
