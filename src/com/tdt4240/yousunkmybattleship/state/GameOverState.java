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

public class GameOverState extends State implements TouchListener {
	// Temporary background
	Image background = new Image(R.drawable.water_background);

	TextButton menu;
	TextButton exit;
	String looserName;
	String winnerName;

	public GameOverState(Player looser) {
		looserName = looser.getName();
		winnerName = Constants.getOther().getName();

		menu = new TextButton(Constants.WINDOW_WIDTH * 0.35f,
				Constants.WINDOW_HEIGHT * 0.60f, "Menu", Constants.paint);
		exit = new TextButton(Constants.WINDOW_WIDTH * 0.42f,
				Constants.WINDOW_HEIGHT * 0.75f, "Exit", Constants.paint);
	}

	public void draw(Canvas canvas) {
		background.draw(canvas, 0, 0);
		canvas.drawText("The Winner is: " + winnerName, 30f, 15f,
				Constants.paint[0]);
		canvas.drawText("and the big, fat looser is: " + looserName, 30f, 30f,
				Constants.paint[0]);

		menu.draw(canvas);
		exit.draw(canvas);
	}

	public boolean onTouchDown(MotionEvent event) {
		if (menu.onTouchDown(event)) {
			// should pop back down to main menu, not add another mainmenustate
		} else if (exit.onTouchDown(event)) {
			// Quit the app
		}
		return true;
	}
}
