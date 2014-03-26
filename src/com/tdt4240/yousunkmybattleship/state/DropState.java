package com.tdt4240.yousunkmybattleship.state;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ConcurrentModificationException;
import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Graphics;
import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.Sprite;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

/**
 * Screen that shows the game play. It extends GameState and contains the
 * methods for dropping bombs.
 * 
 */

public class DropState extends GameState implements TouchListener, PropertyChangeListener {
	private TextButton viewBoardButton;
	private TextButton backToMainButton;
	private int bombsLeft;
	private int counter = 0;
	private int[] coords = new int[2];
	

	public DropState() {
		viewBoardButton = new TextButton(Constants.WINDOW_WIDTH * 0.05f,
				Constants.START_OF_GRID - Constants.WINDOW_HEIGHT * 0.05f,
				"My board", Graphics.buttonPaint);
		backToMainButton = new TextButton(Constants.WINDOW_WIDTH * 0.6f,
				Constants.START_OF_GRID - Constants.WINDOW_HEIGHT * 0.05f,
				"Main menu", Graphics.buttonPaint);
		bombsLeft = Constants.p.getBombsPerTurn();

		if (counter < 2) {
			Constants.p.addPropertyChangeListener(this);
			counter++;
		}
		drawBombDrops(Constants.p);
	}

	// try to register bomb drop in model
	public void dropBomb(float x1, float y1) {
		int x = (int) (x1 / Constants.TILE_SIZE);
		int y = (int) ((-Constants.START_OF_GRID + y1) / Constants.TILE_SIZE);
		coords[0] = x;
		coords[1] = y;

		// Update player model, dropstate listens to player model, draws sprites
		if (Constants.p.registerDrop(x, y)) {
			if (!Constants.getOther().shipIsHit(x, y))
				bombsLeft--;
		}
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);
		viewBoardButton.draw(canvas);
		backToMainButton.draw(canvas);
		try {
			for (Sprite s : drops) {
				s.draw(canvas);
			}
		} catch (ConcurrentModificationException e) {
			e.printStackTrace();
		}

		canvas.drawText("You have: " + bombsLeft + " bombs left!",
				Constants.WINDOW_WIDTH * 0.18f, Constants.WINDOW_HEIGHT * 0.28f,
				Graphics.buttonPaint[1]);
	}

	public void update(float dt) {
		try {
			for (Sprite s : drops) {
				s.update(dt);
			}
		} catch (ConcurrentModificationException e) {
			e.printStackTrace();
		}
	}

	public boolean onTouchDown(MotionEvent event) {
		// check if all bombs are dropped
		if (viewBoardButton.onTouchDown(event)) {
			Constants.game.pushState(new ViewBoardState());
			return true;
		}
		else if (backToMainButton.onTouchDown(event)) {
			Constants.game.popState(2);
			return true;
		}

		if (bombsLeft == 0) {
			Constants.game.popState();
			Constants.changeTurn();
			return true;
		}
		// try to drop a bomb on selected grid
		if (event.getY() > Constants.START_OF_GRID) {
			dropBomb(event.getX(), event.getY());
			if (isWinner())
				Constants.game.pushState(new GameOverState());
		}
		return false;
	}

	private boolean isWinner() {
		return (Constants.getOther().getShipsRemaining() == 0);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName() == Constants.BOMB_DROPPED) {
			drawBombDrop(coords[0], coords[1], Constants.p);
		}
		
	}
}
