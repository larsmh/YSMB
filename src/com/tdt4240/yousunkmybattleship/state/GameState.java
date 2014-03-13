package com.tdt4240.yousunkmybattleship.state;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.R;
import com.tdt4240.yousunkmybattleship.Ship;

import android.graphics.Canvas;
import android.util.Log;
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
	Image bg = new Image(R.drawable.menu_bg);
	Image board = new Image(R.drawable.board);
	Image bs = new Image(R.drawable.bomb_site);
	Image ws = new Image(R.drawable.water_splash);
	int bombsLeft;
	ArrayList<Sprite> drops;

	public GameState() {
		bombsLeft = Constants.p.getBombsPerTurn();
		drops = new ArrayList<Sprite>();
		drawBombDrops();
	}

	// try to register bomb drop in model
	public void dropBomb(float x1, float y1) {
		int x = (int)(x1 / Constants.TILE_SIZE);
		int y = (int) ((-Constants.START_OF_GRID + y1) / Constants.TILE_SIZE);
		if (Constants.p.registerDrop(x, y)) {
			if(!Constants.getOther().shipIsHit(x, y))
				bombsLeft--;
			drawBombDrop(x, y);
		}
	}
	private void drawBombDrop(int x, int y){
		if (Constants.getOther().getBoard()[y][x] != -1) {
			drops.add(new Sprite(bs));
		} else {
			drops.add(new Sprite(ws));
		}
		drops.get(drops.size()-1).setPosition(
				x * Constants.TILE_SIZE + drops.get(drops.size() - 1).getOffset().getX()+1,
				Constants.START_OF_GRID + y * Constants.TILE_SIZE + 
				drops.get(drops.size() - 1).getOffset().getY()+1);
	}
	
	// draw all bomb drops registered in model
	private void drawBombDrops() {
		for (int i = 0; i < Constants.GRID_HEIGHT; i++) {
			for (int j = 0; j < Constants.GRID_WIDTH; j++) {
				if (Constants.p.getDrops()[i][j]) {
					drawBombDrop(j,i);
				}
			}
		}
	}

	public void draw(Canvas canvas) {
		bg.draw(canvas, 0, 0);
		board.draw(canvas, 0, Constants.START_OF_GRID);
		try{
			for(Sprite s: drops){
				s.draw(canvas);
			}
		}catch (ConcurrentModificationException e){
			e.printStackTrace();
		}
	}

	public void update(float dt) {
		try{
			for(Sprite s: drops){
				s.update(dt);
			}
		}catch (ConcurrentModificationException e){
			e.printStackTrace();
		}
	}

	public boolean onTouchDown(MotionEvent event) {
		// check if all bombs are dropped
		if (bombsLeft == 0) {
			Constants.game.popState();
			return true;
		}
		// try to drop a bomb on selected grid
		if (event.getY() > Constants.START_OF_GRID) {
			dropBomb(event.getX(), event.getY());
			if(isWinner())
				Constants.game.pushState(new GameOverState(Constants.getOther()));
		}
		return false;
	}
	private boolean isWinner(){
		for(Ship s: Constants.getOther().getShips()){
			if(!s.isSunk())
				return false;
		}
		return true;
	}

}
