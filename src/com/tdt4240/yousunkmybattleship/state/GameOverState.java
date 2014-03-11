package com.tdt4240.yousunkmybattleship.state;

import com.tdt4240.yousunkmybattleship.Constants;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Font;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

public class GameOverState extends State implements TouchListener {
	// Image backGround =
	TextButton menu;
	TextButton exit;

	private static final Font WHITE_SANS_BOLD_70 = new Font(255, 255, 255,
			70.0f, Typeface.SANS_SERIF, Typeface.BOLD);

	public GameOverState() {
		Constants.paint = new Paint[2];
		for (int i = 0; i < Constants.paint.length; i++) {
			Constants.paint[i] = new Paint();
			Constants.paint[i].setColor(Color.WHITE);
			Constants.paint[i].setTextSize(Constants.WINDOW_WIDTH / 21);
		}
		menu = new TextButton(Constants.WINDOW_WIDTH * 0.35f,
				Constants.WINDOW_HEIGHT * 0.50f, "New Game", Constants.paint);
		exit = new TextButton(Constants.WINDOW_WIDTH * 0.42f,
				Constants.WINDOW_HEIGHT * 0.65f, "Settings", Constants.paint);
	}

	public void draw(Canvas canvas) {
		// backGround.draw(canvas, 0, 0);
		menu.draw(canvas);
		exit.draw(canvas);
		canvas.drawText(" wins!", Constants.WINDOW_WIDTH * 0.25f,
				Constants.WINDOW_HEIGHT * 0.1f, WHITE_SANS_BOLD_70);
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