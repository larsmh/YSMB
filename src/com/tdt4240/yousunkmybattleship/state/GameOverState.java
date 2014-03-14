package com.tdt4240.yousunkmybattleship.state;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Graphics;
import com.tdt4240.yousunkmybattleship.Player;
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
<<<<<<< HEAD
	Image background = new Image(R.drawable.menu_bg);
=======
	Image background = Graphics.bg;
>>>>>>> 73baf2de5884d4505f20125b7ad271865a838847

	TextButton menu;
	TextButton exit;

	public GameOverState() {
		menu = new TextButton(Constants.WINDOW_WIDTH * 0.1f,
				Constants.WINDOW_HEIGHT * 0.9f, "Menu", Constants.paint);
		exit = new TextButton(Constants.WINDOW_WIDTH * 0.9f,
				Constants.WINDOW_HEIGHT * 0.9f, "Exit", Constants.paint);
	}

	public void draw(Canvas canvas) {
		background.draw(canvas, 0, 0);
<<<<<<< HEAD
		canvas.drawText(Constants.p.getName(), Constants.WINDOW_WIDTH * 0.25f,
				Constants.WINDOW_HEIGHT * 0.25f,	Graphics.paint);
		canvas.drawText("is the winner", Constants.WINDOW_WIDTH * 0.05f,
				Constants.WINDOW_HEIGHT * 0.35f,	Graphics.paint);
=======
		canvas.drawText("The Winner is: " + winnerName,
				Constants.WINDOW_WIDTH * 0.39f, Constants.WINDOW_HEIGHT * 0.2f,
				Constants.paint[0]);
		canvas.drawText("and the big, fat looser is: " + looserName,
				Constants.WINDOW_WIDTH * 0.30f,
				Constants.WINDOW_HEIGHT * 0.36f, Constants.paint[0]);
>>>>>>> 73baf2de5884d4505f20125b7ad271865a838847
		menu.draw(canvas);
		exit.draw(canvas);
	}

	public boolean onTouchDown(MotionEvent event) {
		if (menu.onTouchDown(event)) {
<<<<<<< HEAD
			Constants.game.popState(3);
		} else if (exit.onTouchDown(event)) {
			android.os.Process.killProcess(android.os.Process.myPid());
	        System.exit(1);
=======
			// should pop back down to main menu, not add another mainmenustate,
			// when moved to the right order, should pop more than 2 states.
			Constants.game.popState(4);
		} else if (exit.onTouchDown(event)) {
			// quit game, we really can't find how to quit the game
			// Constants.game.
			// super.onStop();
			// GameOverState.kill();
			// finish();
>>>>>>> 73baf2de5884d4505f20125b7ad271865a838847
		}
		return true;
	}
}
