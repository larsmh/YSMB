package com.tdt4240.yousunkmybattleship.state;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Graphics;

import sheep.game.Sprite;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

/**
 * Shows the player's grid with his ships and the locations where his opponent
 * dropped bombs.
 * 
 * It is pushed if a player clicks on the “My board” button when it is his turn.
 */

public class MyBoardState extends GameState implements TouchListener {
	private TextButton backButton = new TextButton(
			Constants.WINDOW_WIDTH * 0.05f, Constants.START_OF_GRID
					- Constants.WINDOW_HEIGHT * 0.05f, "back",
			Graphics.buttonPaint);

	/**
	 * The constructor of the class.
	 * 
	 * Creates the player's ships sprites and draws the dropped bombs of his
	 * opponent.
	 */
	public MyBoardState() {
		createSprites();
		drawBombDrops(Constants.getOther());
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);
		backButton.draw(canvas);
		canvas.drawText(Constants.p.getName() + "'s turn",
				Constants.WINDOW_WIDTH * 0.02f, Constants.WINDOW_HEIGHT * 0.2f,
				Graphics.paint);
		for (Sprite s : sprites)
			s.draw(canvas);
		for (Sprite s : drops)
			s.draw(canvas);
	}

	public void update(float dt) {
		for (Sprite s : sprites)
			s.update(dt);
		for (Sprite s : drops)
			s.update(dt);
	}

	public boolean onTouchDown(MotionEvent event) {
		if (backButton.onTouchDown(event)) {
			Constants.game.popState();
			return true;
		}
		return false;
	}
}
