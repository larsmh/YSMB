package com.tdt4240.yousunkmybattleship.state;

import java.util.ArrayList;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.R;

import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.input.TouchListener;

/**
 * It is the screen that shows the game play.
 * 
 */

public class GameState extends State implements TouchListener {
	Image bg = new Image(R.drawable.gameboard);
	Image bs = new Image(R.drawable.bomb_site);
	Image ws = new Image(R.drawable.water_splash);
	int bombsLeft;

	public GameState() {
		bombsLeft = Constants.p.getBombsPerTurn();
	}

	// try to register bomb drop in model
	public boolean dropBomb(float x, float y) {
		if (Constants.p.registerDrop((int) (x / Constants.TILE_SIZE),
				(int) ((-Constants.START_OF_GRID + y) / Constants.TILE_SIZE))) {
			bombsLeft--;
			return true;
		}
		return false;
	}

	// draw all bomb drops registered in model
	private void drawBombDrops(float dt) {
		ArrayList<Sprite> drops;
		drops = new ArrayList<Sprite>();
		for (int i = 0; i < Constants.GRID_HEIGHT; i++) {
			for (int j = 0; j < Constants.GRID_WIDTH; j++) {
				if (Constants.p.getDrops()[i][j]) {
					if (Constants.getOther().getBoard()[i][j] != -1) {
						drops.add(new Sprite(bs));
					} else {
						drops.add(new Sprite(ws));
					}
					drops.get(drops.size() - 1).setPosition(
							j
									* Constants.TILE_SIZE
									+ drops.get(drops.size() - 1).getOffset()
											.getX(),
							Constants.START_OF_GRID
									+ i
									* Constants.TILE_SIZE
									+ drops.get(drops.size() - 1).getOffset()
											.getY());
					drops.get(drops.size() - 1).update(dt);
				}
			}
		}
	}

	public void draw(Canvas canvas) {
		bg.draw(canvas, 0, 0);
	}

	public void update(float dt) {
		drawBombDrops(dt);
	}

	public boolean onTouchDown(MotionEvent event) {
		// check if all bombs are dropped
		if (bombsLeft == 0) {
			// bombsLeft=Constants.getOther().getBombsPerTurn();
			Constants.game.pushState(new ChangeTurnState());
		}
		// try to drop a bomb on selected grid
		if (event.getY() > Constants.START_OF_GRID) {
			return dropBomb(event.getX(), event.getY());
		}
		return false;
	}

}
