package com.tdt4240.yousunkmybattleship.state;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Graphics;
import com.tdt4240.yousunkmybattleship.model.Ship;

import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.Sprite;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

/**
 * It is the screen that shows the game play.
 * 
 */

public class DropState extends GameState implements TouchListener {
	private Image bs = Graphics.bomb_site;
	private Image ws = Graphics.water_splash;
	private TextButton myBoardButton;
	private int bombsLeft;
	private ArrayList<Sprite> drops;

	public DropState() {
		myBoardButton = new TextButton(Constants.WINDOW_WIDTH * 0.05f,
				Constants.START_OF_GRID - Constants.WINDOW_HEIGHT*0.05f, "My board", Graphics.buttonPaint);
		bombsLeft = Constants.p.getBombsPerTurn();
		drops = new ArrayList<Sprite>();
		drawBombDrops();
	}

	// try to register bomb drop in model
	public void dropBomb(float x1, float y1) {
		int x = (int) (x1 / Constants.TILE_SIZE);
		int y = (int) ((-Constants.START_OF_GRID + y1) / Constants.TILE_SIZE);
		if (Constants.p.registerDrop(x, y)) {
			if (!Constants.getOther().shipIsHit(x, y))
				bombsLeft--;
			drawBombDrop(x, y);
		}
	}

	private void drawBombDrop(int x, int y) {
		if (Constants.getOther().getBoard()[y][x] != -1) {
			drops.add(new Sprite(bs));
		} else {
			drops.add(new Sprite(ws));
		}
		drops.get(drops.size() - 1).setPosition(
				x * Constants.TILE_SIZE
						+ drops.get(drops.size() - 1).getOffset().getX() + 1,
				Constants.START_OF_GRID + y * Constants.TILE_SIZE
						+ drops.get(drops.size() - 1).getOffset().getY() + 1);
	}

	// draw all bomb drops registered in model
	private void drawBombDrops() {
		for (int i = 0; i < Constants.GRID_HEIGHT; i++) {
			for (int j = 0; j < Constants.GRID_WIDTH; j++) {
				if (Constants.p.getDrops()[i][j]) {
					drawBombDrop(j, i);
				}
			}
		}
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);
		myBoardButton.draw(canvas);
		canvas.drawText(Constants.p.getName() + "'s turn",
				Constants.WINDOW_WIDTH * 0.02f, Constants.WINDOW_HEIGHT * 0.2f,
				Graphics.paint);
		try {
			for (Sprite s : drops) {
				s.draw(canvas);
			}
		} catch (ConcurrentModificationException e) {
			e.printStackTrace();
		}

		canvas.drawText("You have: " + bombsLeft + " bombs left!",
				Constants.WINDOW_WIDTH * 0.18f, Constants.WINDOW_HEIGHT * 0.3f,
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
		if(myBoardButton.onTouchDown(event)){
			Constants.game.pushState(new MyBoardState());
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
		for (Ship s : Constants.getOther().getShips()) {
			if (!s.isSunk())
				return false;
		}
		return true;
	}

}
