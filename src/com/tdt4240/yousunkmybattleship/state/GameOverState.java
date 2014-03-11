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

	String loser;
	String winner;

	private static final Font WINNER = new Font(255, 255, 255,
			Constants.WINDOW_WIDTH / 15, Typeface.SANS_SERIF, Typeface.BOLD);

	public GameOverState(String loser) {
		this.loser = loser;
		if (loser == Constants.p1.getName()) {
			this.winner = Constants.p2.getName();
		} else {
			this.winner = Constants.p1.getName();
		}

		Constants.paint = new Paint[2];
		for (int i = 0; i < Constants.paint.length; i++) {
			Constants.paint[i] = new Paint();
			Constants.paint[i].setColor(Color.WHITE);
			Constants.paint[i].setTextSize(Constants.WINDOW_WIDTH / 21);
		}
		menu = new TextButton(Constants.WINDOW_WIDTH * 0.35f,
				Constants.WINDOW_HEIGHT * 0.60f, "Menu", Constants.paint);
		exit = new TextButton(Constants.WINDOW_WIDTH * 0.42f,
				Constants.WINDOW_HEIGHT * 0.75f, "Exit", Constants.paint);
	}

	public void draw(Canvas canvas) {
		// backGround.draw(canvas, 0, 0);
		menu.draw(canvas);
		exit.draw(canvas);

		// Display the name of the loser
		canvas.drawText(winner + " wins!\n\n" + loser + " loses!",
				Constants.WINDOW_WIDTH * 0.25f, Constants.WINDOW_HEIGHT * 0.1f,
				WINNER);
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
