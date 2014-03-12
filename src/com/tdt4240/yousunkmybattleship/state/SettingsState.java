package com.tdt4240.yousunkmybattleship.state;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Player;
import com.tdt4240.yousunkmybattleship.R;
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
	Image bg = new Image(R.drawable.menu_bg);
	TextButton backButton;
	TextButton gameOverTestScreen;

	public SettingsState() {
		backButton = new TextButton(Constants.WINDOW_WIDTH * 0.39f,
				Constants.WINDOW_HEIGHT * 0.2f, "back to main", Constants.paint);
		gameOverTestScreen = new TextButton(Constants.WINDOW_WIDTH * 0.42f,
				Constants.WINDOW_HEIGHT * 0.36f, "Game Over test screen", Constants.paint);
	}

	public void draw(Canvas canvas) {
		bg.draw(canvas, 0, 0);
		backButton.draw(canvas);
		gameOverTestScreen.draw(canvas);
	}

	public boolean onTouchDown(MotionEvent event) {
		if (backButton.onTouchDown(event)) {
			Constants.game.popState();
		}if (gameOverTestScreen.onTouchDown(event)){
			// for testing:
			Constants.p1 = new Player("1");
			Constants.p2 = new Player("2");
			Constants.p = Constants.p1;
			Constants.game.pushState(new GameOverState(Constants.p1));
		}
		return true;
	}
}
