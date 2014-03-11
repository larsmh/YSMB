package com.tdt4240.yousunkmybattleship.state;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.R;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

public class MainMenuState extends State implements TouchListener {

	//Background with integrated buttonbackgrounds:
	//Image bg = new Image(R.drawable.menu_bgtest);
	//Regular background:
	Image bg = new Image(R.drawable.menu_bg);
	TextButton newGame;
	TextButton settings;

	public MainMenuState() {
		Constants.paint = new Paint[2];
		for (int i = 0; i < Constants.paint.length; i++) {
			Constants.paint[i] = new Paint();
			Constants.paint[i].setColor(Color.WHITE);
			Constants.paint[i].setTextSize(Constants.WINDOW_WIDTH / 21);
		}
		newGame = new TextButton(Constants.WINDOW_WIDTH * 0.39f,
				Constants.WINDOW_HEIGHT * 0.2f, "New Game", Constants.paint);
		settings = new TextButton(Constants.WINDOW_WIDTH * 0.42f,
				Constants.WINDOW_HEIGHT * 0.36f, "Settings", Constants.paint);
	}

	public void draw(Canvas canvas) {
		bg.draw(canvas, 0, 0);
		newGame.draw(canvas);
		settings.draw(canvas);
	}

	/*
	 * public void update(float dt) {
	 * 
	 * }
	 */
	
	public boolean onTouchDown(MotionEvent event) {
		if (newGame.onTouchDown(event))
			Constants.game.pushState(new ShipPlacementState());
		else if (settings.onTouchDown(event))
			Constants.game.pushState(new SettingsState());
		return true;
	}

}
