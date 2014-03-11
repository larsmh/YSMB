package com.tdt4240.yousunkmybattleship.state;

import java.util.ArrayList;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Player;
import com.tdt4240.yousunkmybattleship.R;

import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.input.TouchListener;

public class GameState extends State implements TouchListener {
	Image bg = new Image(R.drawable.gameboard);
	Image bs = new Image(R.drawable.bomb_site);
	Image ws = new Image(R.drawable.water_splash);
	Sprite drop;
	Player p;
	int bombsLeft;
	//ArrayList<Image> drops;
	

	public GameState() {
		//drops = new ArrayList<Image>();
	}
	
	//try to register bomb drop in model
	public boolean dropBomb(float x, float y){
		if(p.registerDrop((int)(x/Constants.TILE_SIZE), (int)(y/Constants.TILE_SIZE))){
			bombsLeft--;
			return true;
		}
		return false;
	}
	
	//draw all bomb drops registered in model
	private void drawBombDrops(float dt){
		for(int i=0; i<Constants.GRID_HEIGHT; i++){
			for(int j=0; j<Constants.GRID_WIDTH; j++){
				if(p.getDrops()[i][j]){
					if(Constants.getOther(p).getBoard()[i][j]!=-1){
						drop = new Sprite(bs);
					}
					else{
						drop = new Sprite(ws);
					}
					drop.setPosition(j*Constants.TILE_SIZE, Constants.START_OF_GRID+i*Constants.TILE_SIZE);
					drop.update(dt);
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
		//check if all bombs are dropped
		if(bombsLeft==0)
			Constants.game.pushState(new ChangeTurnState());
		//try to drop a bomb on selected grid
		if(event.getY()>Constants.START_OF_GRID){
			return dropBomb(event.getX(), event.getY());
		}
		return false;
	}

}
