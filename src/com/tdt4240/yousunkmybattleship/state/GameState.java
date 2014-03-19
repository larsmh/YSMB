package com.tdt4240.yousunkmybattleship.state;

import java.util.ArrayList;

import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;

import android.graphics.Canvas;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Graphics;
import com.tdt4240.yousunkmybattleship.model.Player;
import com.tdt4240.yousunkmybattleship.model.Ship;

public abstract class GameState extends State {
	private Image bg = Graphics.bg;
	private Image board = Graphics.board;
	private Image bs = Graphics.bomb_site;
	private Image ws = Graphics.water_splash;
	protected Sprite[] sprites;
	protected Ship[] ships;
	protected ArrayList<Sprite> drops;
	
	public GameState(){
		ships = Constants.p.getShips();
		drops = new ArrayList<Sprite>();
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
	
	protected void drawBombDrop(int x, int y, Player p) {
		if(p==Constants.p) p=Constants.getOther();
		else p=Constants.p;
		if (p.getBoard()[y][x] != -1) {
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
	protected void drawBombDrops(Player p) {
		for (int i = 0; i < Constants.GRID_HEIGHT; i++) {
			for (int j = 0; j < Constants.GRID_WIDTH; j++) {
				if (p.getDrops()[i][j]) {
					drawBombDrop(j, i, p);
				}
			}
		}
	}
	
	
	public void draw(Canvas canvas){
		bg.draw(canvas, 0, 0);
		board.draw(canvas, 0, Constants.START_OF_GRID);
		canvas.drawText(Constants.p.getName() + "'s turn",
				Constants.WINDOW_WIDTH * 0.02f, Constants.WINDOW_HEIGHT * 0.2f,
				Graphics.paint);
	}
}
