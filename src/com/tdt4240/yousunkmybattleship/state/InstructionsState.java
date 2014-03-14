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
	private Image instruction1 = new Image(R.drawable.instruction1);
	private Image instruction2 = new Image(R.drawable.instruction2);

	private TextButton nextButton;

	/**
	 * Indicates how many times the user clicked on the "Next" button. The first
	 * time, the second instructions' page appears. The second time, the main
	 * menu appears.
	 * 
	 */
	private int clickNext;

	public InstructionsState() {
		clickNext = 0;

		nextButton = new TextButton(Constants.WINDOW_WIDTH * 0.85f,
				Constants.WINDOW_HEIGHT * 0.98f, "Next", Constants.paint);
		addTouchListener(nextButton);
	}

	public void draw(Canvas canvas) {
		if (clickNext == 0) {
			instruction1.draw(canvas, 0, 0);
		} else {
			instruction2.draw(canvas, 0, 0);
		}

		nextButton.draw(canvas);
	}

	public boolean onTouchDown(MotionEvent event) {
		if (nextButton.onTouchDown(event)) {
			if (clickNext == 0) {
				clickNext++;
			} else {
				Constants.game.popState();
			}
		}
		return true;
	}

}
