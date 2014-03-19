package com.tdt4240.yousunkmybattleship.state;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;

import android.graphics.Canvas;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Graphics;
import com.tdt4240.yousunkmybattleship.model.Ship;

public abstract class GameState extends State {
	private Image bg = Graphics.bg;
	private Image board = Graphics.board;
	protected Sprite[] sprites;
	protected Ship[] ships;
	
	public GameState(){
		ships = Constants.p.getShips();
	}
	
	protected void createSprites() {
		sprites = new Sprite[5];
		for (int i = 0; i < sprites.length; i++){
			if(!ships[i].isVertical())
				sprites[i] = new Sprite(ships[i].getType().getImgHor());
			else
				sprites[i] = new Sprite(ships[i].getType().getImgVert());
		}
		placeOnTiles();
	}
	
	protected void placeOnTiles() {
		for (int i = 0; i < sprites.length; i++) {
			sprites[i].setPosition(
					ships[i].getPosX() * Constants.TILE_SIZE
							+ sprites[i].getOffset().getX() + 1,
					Constants.START_OF_GRID
							+ ships[i].getPosY()
							* Constants.TILE_SIZE
							+ sprites[i].getOffset().getY() + 1);
		}
	}
	
	public void draw(Canvas canvas){
		bg.draw(canvas, 0, 0);
		board.draw(canvas, 0, Constants.START_OF_GRID);
	}
}
