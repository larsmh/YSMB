package com.tdt4240.yousunkmybattleship.state;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.R;

import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

/**
 * Contains the instructions to play to "You Sunk My Battleship".
 * 
 */

public class InstructionsState extends State implements TouchListener {
	// Temporary background
	private Image background = new Image(R.drawable.menu_bg);
	private TextButton backButton;

	public InstructionsState() {
		backButton = new TextButton(Constants.WINDOW_WIDTH * 0.8f,
				Constants.WINDOW_HEIGHT * 0.9f, "Back");
		addTouchListener(backButton);
	}

	public void draw(Canvas canvas) {
		background.draw(canvas, 0, 0);
		backButton.draw(canvas);
	}

	public boolean onTouchDown(MotionEvent event) {
		if (backButton.onTouchDown(event))
			Constants.game.popState();
		return true;
	}

}
