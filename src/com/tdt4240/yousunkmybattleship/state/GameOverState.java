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
 * This screen appears when one of the two players win.
 * 
 * It indicates who are the winner and the loser, and allows the players to go
 * back to the main menu or quit the app.
 * 
 */

public class GameOverState extends State implements TouchListener {
	// Temporary background
	Image background = new Image(R.drawable.gameboard);

	TextButton menu;
	TextButton exit;
	String looserName;
	String winnerName;

	public GameOverState(Player looser) {
		looserName = looser.getName();
		winnerName = Constants.getOther().getName();
		menu = new TextButton(Constants.WINDOW_WIDTH * 0.1f,
				Constants.WINDOW_HEIGHT * 0.9f, "Menu", Constants.paint);
		exit = new TextButton(Constants.WINDOW_WIDTH * 0.9f,
				Constants.WINDOW_HEIGHT * 0.9f, "Exit", Constants.paint);
	}

	public void draw(Canvas canvas) {
		background.draw(canvas, 0, 0);
		canvas.drawText("The Winner is: " + winnerName, Constants.WINDOW_WIDTH * 0.39f,
				Constants.WINDOW_HEIGHT * 0.2f,	Constants.paint[0]);
		canvas.drawText("and the big, fat looser is: " + looserName, Constants.WINDOW_WIDTH * 0.30f, Constants.WINDOW_HEIGHT *0.36f,
				Constants.paint[0]);
		menu.draw(canvas);
		exit.draw(canvas);
	}

	public boolean onTouchDown(MotionEvent event) {
		if (menu.onTouchDown(event)) {
			// should pop back down to main menu, not add another mainmenustate, when moved to the right order, should pop more than 2 states.
			Constants.game.popState(2);
		} else if (exit.onTouchDown(event)) {
			//quit game
		}
		return true;
	}
}
