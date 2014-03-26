package com.tdt4240.yousunkmybattleship.state;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Calendar;
import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Graphics;
import com.tdt4240.yousunkmybattleship.model.Ship;
import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.Sprite;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

/**
 * On this screen, the players can to place ships on their grids before the game
 * starts.
 * 
 * When the ship placement is done this state is popped (back to ChangeTurnState), and the other
 * player gets ready to place his ships on the same type of screen. When both players have
 * placed their ships they start the game in the DropState.
 * 
 */

public class ShipPlacementState extends GameState implements TouchListener, PropertyChangeListener {

	private int moveableShip;
	private TextButton submit;

	private long startClickTime;
	private boolean legal;

	public ShipPlacementState() {
		submit = new TextButton(Constants.WINDOW_WIDTH * 0.05f,
				Constants.START_OF_GRID - Constants.WINDOW_HEIGHT*0.05f, "Submit", Graphics.buttonPaint);
		moveableShip = -1;
		ships = Constants.p.getShips();
		for (int i = 0; i < ships.length; i++) {
			ships[i].addPropertyChangeListener(this);
		}
		createSprites();
	}
	

	/**
	 * Changes the sprite (from vertical to horizontal or vice versa)
	 * 
	 * @param spriteIndex
	 *            the index of the sprite to change
	 * @param ship
	 *            the ship whose sprite is being changed
	 * 
	 */
	private void changeSprite(int spriteIndex, Ship ship) {
		if (ship.isVertical()) {
			sprites[spriteIndex] = new Sprite(ship.getType().getImgVert());
		} else {
			sprites[spriteIndex] = new Sprite(ship.getType().getImgHor());
		}
	}

	/**
	 * Rotates the ship (from vertical to horizontal or vice versa)
	 * 
	 * @param index
	 *            index of the ship to rotate
	 */
	private void rotateShip(Ship ship) {
		ship.changeDirection();
	}

	/**
	 * Places the ship being moved on the tiles closest to it and add the ships
	 * on the player's board
	 * 
	 */
	private boolean checkLegal(){
		// wait to make sure all ships have slid back to right position before check
		synchronized(this){
			try {
				wait(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (int i = 0; i < sprites.length - 1; i++) {
			for (int j = i + 1; j < sprites.length; j++) {
				if (sprites[i].collides(sprites[j])) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Allows to make sure the player do not accidentally start moving a
	 * different ship when dragging another ship past it
	 * 
	 * @param s
	 *            the index of the ship to move
	 * 
	 */
	private boolean isMoveable(int s) {
		return (moveableShip == s || moveableShip == -1);
	}

	public void draw(Canvas canvas) {
		super.draw(canvas);
		if(legal)
			submit.draw(canvas);
		else
			canvas.drawText("Illegal placement", Constants.WINDOW_WIDTH*0.05f, 
					Constants.START_OF_GRID-Constants.WINDOW_HEIGHT*0.05f, Graphics.warningPaint);
		for (Sprite s : sprites)
			s.draw(canvas);
	}

	public void update(float dt) {
		for (Sprite s : sprites)
			s.update(dt);
	}

	public boolean onTouchDown(MotionEvent event) {
		// Logic check for submit button
		if (submit.onTouchDown(event) && legal) {
			Constants.p.setReady();
			if(!Constants.p.isReady())
				return false;
			for (int i = 0; i < ships.length; i++)
				ships[i].removePropertyChangeListener(this);
			
			Constants.game.popState();
			Constants.changeTurn();
			return true;
		}
		// Used to register a click
		startClickTime = Calendar.getInstance().getTimeInMillis();
		return true;
	}

	public boolean onTouchMove(MotionEvent event) {
		for (int i = sprites.length - 1; i >= 0; i--) {
			float x = sprites[i].getX();
			float y = sprites[i].getY();

			if (event.getX() >= x - sprites[i].getOffset().getX() && event.getX() <= x
							+ sprites[i].getOffset().getX() && event.getY() >= y
							- sprites[i].getOffset().getY()	&& event.getY() <= y
							+ sprites[i].getOffset().getY() && isMoveable(i)) {
				moveableShip = i;

				// Checks edges so that ships are not dragged off the board
				if (event.getX() >= sprites[i].getOffset().getX()
						&& event.getX() <= Constants.WINDOW_WIDTH
								- sprites[i].getOffset().getX()) {
					x = event.getX();
				}
				if (event.getY() >= Constants.START_OF_GRID
						+ sprites[i].getOffset().getY()
						&& event.getY() <= Constants.WINDOW_HEIGHT
								- sprites[i].getOffset().getY()) {
					y = event.getY();
				}

				sprites[i].setPosition(x, y);
				
				return true;
			}
		}
		return false;
	}

	public boolean onTouchUp(MotionEvent event) {
		long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;

		if(moveableShip!=-1){
			ships[moveableShip].placeShip(
					(int) ((Constants.TILE_SIZE / 2 + sprites[moveableShip].getX() - sprites[moveableShip]
							.getOffset().getX()) / Constants.TILE_SIZE),
							(int) ((Constants.TILE_SIZE / 2 - Constants.START_OF_GRID + sprites[moveableShip].getY() 
									- sprites[moveableShip].getOffset().getY()) / Constants.TILE_SIZE));
		}
		// If a click is registered on a ship, initiate rotating process
		if (clickDuration < Constants.MAX_CLICK_DURATION && moveableShip != -1) {
			rotateShip(ships[moveableShip]);
		}
		placeOnTiles();
		legal = checkLegal();
		moveableShip = -1;
		return true;
	}

	@Override
	public void propertyChange(PropertyChangeEvent event) {
		if (event.getPropertyName() == Constants.CHANGE_DIRECTION && moveableShip != -1) {
			changeSprite(moveableShip, ships[moveableShip]);
		}
		
	}
}