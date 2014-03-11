package com.tdt4240.yousunkmybattleship.state;

import java.util.Calendar;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Player;
import com.tdt4240.yousunkmybattleship.R;
import com.tdt4240.yousunkmybattleship.Ship;

import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

public class ShipPlacementState extends State implements TouchListener {
	Image bg = new Image(R.drawable.gameboard);
	Image button = new Image(R.drawable.button);
	Sprite[] sprites;
	int moveableShip;
	TextButton submit;
	private long startClickTime;

	public ShipPlacementState() {
		submit = new TextButton(Constants.WINDOW_WIDTH * 0.05f, Constants.START_OF_GRID-192/2, 
				"Submit", Constants.paint);
		Constants.p1 = new Player("Player1");
		Constants.p2 = new Player("Player2");
		Constants.p = Constants.p1;
		moveableShip = -1;

		createSprites();
		//Log.d("!!!!!", Constants.p.getShips()[1].isVertical()+"");
	}

	private void createSprites() {
		sprites = new Sprite[5];
		for (int i = 0; i < sprites.length; i++)
			sprites[i] = new Sprite(Constants.p.getShips()[i].getType().getImgHor());
		placeOnTiles();
	}
	
	//Turning of ships works now
	private void changeSprite(int spriteIndex, Ship ship) {
		if (ship.isVertical()) {
			//sprites[spriteIndex].setView(ship.getType().getImgHor());
			sprites[spriteIndex] = new Sprite(Constants.p.getShips()[spriteIndex].getType().getImgHor());
		}
		else {
			//sprites[spriteIndex].setView(ship.getType().getImgVert());
			sprites[spriteIndex] = new Sprite(Constants.p.getShips()[spriteIndex].getType().getImgVert());
		}
	}
	
	public void rotateShip() {
		if(moveableShip!=-1){
			Ship ship = Constants.p.getShips()[moveableShip];
			ship.changeDirection();
			changeSprite(moveableShip, ship);
		}
	}

	// Places the ship being moved on the tiles closest to it
	public void placeOnTiles() {
		for (int i = 0; i < sprites.length; i++) {
			sprites[i].setPosition(Constants.p.getShips()[i].getPosX() * Constants.TILE_SIZE + sprites[i].getOffset().getX() + 1,
					Constants.START_OF_GRID + Constants.p.getShips()[i].getPosY() * Constants.TILE_SIZE + sprites[i].getOffset().getY() + 1);
		}
	}

	// Makes sure you don't accidentally start moving a different ship when dragging another ship past it
	public boolean isMoveable(int s) {
		return (moveableShip == s || moveableShip == -1);
	}

	public void draw(Canvas canvas) {
		bg.draw(canvas, 0, 0);
		button.draw(canvas, 0, Constants.START_OF_GRID - 192);
		submit.draw(canvas);
		for (int i = 0; i < sprites.length; i++)
			sprites[i].draw(canvas);
	}

	public void update(float dt) {
		for (int i = 0; i < sprites.length; i++)
			sprites[i].update(dt);
	}
	
	/* Do we need this method now?
	public boolean onTouchEvent(MotionEvent event) {
		int eventAction = event.getAction();
		
		switch (eventAction) {
		case MotionEvent.ACTION_DOWN:
			// Change direction of ship
			break;
		case MotionEvent.ACTION_MOVE:
			// Do stuff
			break;
		case MotionEvent.ACTION_UP:
			// Place ship
			break;
		
		}
		return true;
	}*/
	
	public boolean onTouchDown(MotionEvent event) {
		if(submit.onTouchDown(event)){
			for(int i=0; i<sprites.length-1; i++){
				for(int j=i+1; j<sprites.length; j++){
					if(sprites[i].collides(sprites[j])){
						Log.d("!!!!!!", "s"+i+" and s"+j+" collides");
						return false;
					}
				}
			}
			Constants.p.setReady();
			Constants.game.pushState(new ChangeTurnState());
			return true;
		}
		startClickTime = Calendar.getInstance().getTimeInMillis();
		return true;
	}

	public boolean onTouchMove(MotionEvent event) {
		for (int i = sprites.length - 1; i >= 0; i--) {
			float x = sprites[i].getPosition().getX();
			float y = sprites[i].getPosition().getY();
			
			//if(sprites[i].getBoundingBox().contains(event.getX(), event.getY()) && isMoveable(i)){
			if (event.getX() >= sprites[i].getX() - sprites[i].getOffset().getX()
					&& event.getX() <= sprites[i].getX() + sprites[i].getOffset().getX()
					&& event.getY() >= sprites[i].getY() - sprites[i].getOffset().getY()
					&& event.getY() <= sprites[i].getY() + sprites[i].getOffset().getY() 
					&& isMoveable(i)) {
				moveableShip = i;
				
				// Checks edges so that ships are not dragged off the board
				if (event.getX() >= sprites[i].getOffset().getX() 
						&& event.getX() <= Constants.WINDOW_WIDTH - sprites[i].getOffset().getX()) {
					x = event.getX();
				}
				if (event.getY() >= Constants.START_OF_GRID	+ sprites[i].getOffset().getY()
						&& event.getY() <= Constants.WINDOW_HEIGHT - sprites[i].getOffset().getY()) {
					y = event.getY();
				}
				
				// Move to onTouchUp?
				sprites[i].setPosition(x, y);
				Constants.p.getShips()[i].placeShip(
						(int) ((Constants.TILE_SIZE / 2 + x - sprites[i].getOffset().getX()) / Constants.TILE_SIZE),
						(int) ((Constants.TILE_SIZE / 2	- Constants.START_OF_GRID + y - sprites[i].getOffset().getY()) / 108));
				return true;
			}
		}
		return false;
	}

	public boolean onTouchUp(MotionEvent event) {
		long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
		
		if (clickDuration < Constants.MAX_CLICK_DURATION) {
			rotateShip();
		}
		placeOnTiles();
		moveableShip = -1;
		return true;
	}
}
