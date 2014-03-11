package com.tdt4240.yousunkmybattleship.state;

import com.tdt4240.yousunkmybattleship.Constants;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

public class GameOverState extends State implements TouchListener {
	// Image backGround =
	TextButton menu;
	TextButton exit;
	
	// Change to a simple text
	TextButton winnerAnnounce;

	public GameOverState() {
		Constants.paint = new Paint[2];
		for (int i = 0; i < Constants.paint.length; i++) {
			Constants.paint[i] = new Paint();
			Constants.paint[i].setColor(Color.WHITE);
			Constants.paint[i].setTextSize(Constants.WINDOW_WIDTH / 21);
		}
		menu = new TextButton(Constants.WINDOW_WIDTH * 0.39f,
				Constants.WINDOW_HEIGHT * 0.2f, "New Game", Constants.paint);
		exit = new TextButton(Constants.WINDOW_WIDTH * 0.42f,
				Constants.WINDOW_HEIGHT * 0.36f, "Settings", Constants.paint);
		
		// winnerAnnounce = " wins!";
	}

	public void draw(Canvas canvas) {
		// backGround.draw(canvas, 0, 0);
		menu.draw(canvas);
		exit.draw(canvas);
		// winnerAnnounce.draw(canvas);
	}

	public boolean onTouchDown(MotionEvent event) {
		if (menu.onTouchDown(event)) {
			Constants.game.pushState(new MainMenuState());
		} else if (exit.onTouchDown(event)) {
			// Quit the app
		}
		return true;
	}
}