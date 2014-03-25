package com.tdt4240.yousunkmybattleship.state;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Graphics;
import com.tdt4240.yousunkmybattleship.model.Player;

import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

/**
 * This is the settings menu where users are allowed to choose between different
 * settings.
 * 
 */

public class SettingsState extends State implements TouchListener {
	Image bg = Graphics.bg;
	TextButton backButton;
	TextButton gameOverTestScreen;

	public SettingsState() {
		backButton = new TextButton(Constants.WINDOW_WIDTH * 0.30f,
				Constants.WINDOW_HEIGHT * 0.2f, "Back to the menu",
				Graphics.buttonPaint);
		gameOverTestScreen = new TextButton(Constants.WINDOW_WIDTH * 0.30f,
				Constants.WINDOW_HEIGHT * 0.36f, "Game Over Test screen",
				Graphics.buttonPaint);

	}

	public void draw(Canvas canvas) {
		bg.draw(canvas, 0, 0);
		backButton.draw(canvas);
		gameOverTestScreen.draw(canvas);

	}

	public boolean onTouchDown(MotionEvent event) {
		if (backButton.onTouchDown(event)) {
			Constants.game.popState();
		}
		if (gameOverTestScreen.onTouchDown(event)) {
			// for testing:
			Constants.p1 = new Player("1");
			Constants.p2 = new Player("2");
			Constants.p = Constants.p1;
		}

		return true;
	}
}
