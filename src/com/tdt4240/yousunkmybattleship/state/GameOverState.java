package com.tdt4240.yousunkmybattleship.state;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Graphics;
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

	Image background = Graphics.bg;

	TextButton menu;
	TextButton exit;

	public GameOverState() {
		menu = new TextButton(Constants.WINDOW_WIDTH * 0.1f,
				Constants.WINDOW_HEIGHT * 0.9f, "Menu", Graphics.buttonPaint);
		exit = new TextButton(Constants.WINDOW_WIDTH * 0.8f,
				Constants.WINDOW_HEIGHT * 0.9f, "Exit", Graphics.buttonPaint);
	}

	public void draw(Canvas canvas) {
		background.draw(canvas, 0, 0);
		canvas.drawText(Constants.p.getName(), Constants.WINDOW_WIDTH * 0.25f,
				Constants.WINDOW_HEIGHT * 0.25f,	Graphics.paint);
		canvas.drawText("is the winner", Constants.WINDOW_WIDTH * 0.05f,
				Constants.WINDOW_HEIGHT * 0.35f,	Graphics.paint);

		menu.draw(canvas);
		exit.draw(canvas);
	}

	public boolean onTouchDown(MotionEvent event) {
		if (menu.onTouchDown(event)) {
			Constants.game.popState(3);
		} else if (exit.onTouchDown(event)) {
			android.os.Process.killProcess(android.os.Process.myPid());
	        System.exit(1);
		}
		return true;
	}
}
