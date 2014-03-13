package com.tdt4240.yousunkmybattleship.state;

import java.util.Calendar;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Player;
import com.tdt4240.yousunkmybattleship.R;
import com.tdt4240.yousunkmybattleship.Ship;

import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.Sprite;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

/**
 * On this screen, the players can to place ships on their grids before the game
 * starts.
 * 
 * When the ship placement is done the ChangePlayerState is pushed and the other
 * player places his ships on the same type of screen. When both players have
 * placed their ships they start the game in the GameState.
 * 
 */

public class ShipPlacementState extends State implements TouchListener {
	private Image bg = new Image(R.drawable.gameboard);
	private Image button = new Image(R.drawable.button);
	private Sprite[] sprites;
	private int moveableShip;
	private TextButton submit;
	private long startClickTime;

	public ShipPlacementState() {
		submit = new TextButton(Constants.WINDOW_WIDTH * 0.05f,
				Constants.START_OF_GRID - 192 / 2, "Submit", Constants.paint);
		Constants.p1 = new Player("Player1");
		Constants.p2 = new Player("Player2");
		Constants.p = Constants.p1;
		moveableShip = -1;

		createSprites();
	}

	private void createSprites() {
		sprites = new Sprite[5];
		for (int i = 0; i < sprites.length; i++)
			sprites[i] = new Sprite(Constants.p.getShips()[i].getType()
					.getImgHor());
		placeOnTiles();
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
			sprites[spriteIndex] = new Sprite(
					ship.getType().getImgHor());
		} else {
			sprites[spriteIndex] = new Sprite(
					ship.getType().getImgVert());
		}
	}
	
	/**
	 * Rotates the ship (from vertical to horizontal or vice versa)
	 * 
	 * @param index
	 * 			index of the ship to rotate
	 */
	private void rotateShip(int index, Ship ship) {
		changeSprite(index, ship);
		ship.changeDirection();
	}

	/**
	 * Places the ship being moved on the tiles closest to it and add the ships
	 * on the player's board
	 * 
	 */
	private void placeOnTiles() {
		for (int i = 0; i < sprites.length; i++) {
			sprites[i].setPosition(
					Constants.p.getShips()[i].getPosX() * Constants.TILE_SIZE
							+ sprites[i].getOffset().getX() + 1,
					Constants.START_OF_GRID
							+ Constants.p.getShips()[i].getPosY()
							* Constants.TILE_SIZE
							+ sprites[i].getOffset().getY() + 1);
		}
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
		bg.draw(canvas, 0, 0);
		button.draw(canvas, 0, Constants.START_OF_GRID - 192);
		submit.draw(canvas);
		for (Sprite s: sprites)
			s.draw(canvas);
	}

	public void update(float dt) {
		for (Sprite s: sprites)
			s.update(dt);
	}

	public boolean onTouchDown(MotionEvent event) {
		// Logic check for submit button
		if (submit.onTouchDown(event)) {
			for (int i = 0; i < sprites.length - 1; i++) {
				for (int j = i + 1; j < sprites.length; j++) {
					if (sprites[i].collides(sprites[j])) {
						return false;
					}
				}
			}
			Constants.p.setReady();
			for(int i = 0; i < Constants.p.getShips().length; i++){
				if(Constants.p.getShips()[i].isVertical())
					changeSprite(i, Constants.p.getShips()[i]);
			}
			Constants.game.pushState(new ChangeTurnState());
			return true;
		}
		// Used to register a click
		startClickTime = Calendar.getInstance().getTimeInMillis();
		return true;
	}

	public boolean onTouchMove(MotionEvent event) {
		float x, y, 
			event_x, event_y, 
			sprite_x, sprite_y,
			offset_x, offset_y;
		for (int i = sprites.length - 1; i >= 0; i--) {
			x = sprites[i].getPosition().getX();
			y = sprites[i].getPosition().getY();
			event_x = event.getX();
			event_y = event.getY();
			sprite_x = sprites[i].getX();
			sprite_y = sprites[i].getY();
			offset_x = sprites[i].getOffset().getX();
			offset_y = sprites[i].getOffset().getY();

			if (event_x >= sprite_x	- offset_x
					&& event_x <= sprite_x	+ offset_x
					&& event_y >= sprite_y	- offset_y
					&& event_y <= sprite_y	+ offset_y 
					&& isMoveable(i)) {
				moveableShip = i;

				// Checks edges so that ships are not dragged off the board
				if (event_x >= offset_x	&& event_x <= Constants.WINDOW_WIDTH - offset_x) {
					x = event_x;
				}
				if (event_y >= Constants.START_OF_GRID + offset_y && event_y <= Constants.WINDOW_HEIGHT	- offset_y) {
					y = event_y;
				}
				
				// Sets position for the selected ship. Also allows smooth dragging
				sprites[i].setPosition(x, y);
				Constants.p.getShips()[i].placeShip(
						(int) ((Constants.TILE_SIZE / 2 + x - offset_x / Constants.TILE_SIZE)),
						(int) ((Constants.TILE_SIZE / 2	- Constants.START_OF_GRID + y - offset_y) / 108));
				return true;
			}
		}
		return false;
	}

	public boolean onTouchUp(MotionEvent event) {
		long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;

		// If a click is registered on a ship, initiate rotating process
		if (clickDuration < Constants.MAX_CLICK_DURATION && moveableShip != -1) {
			Ship ship = Constants.p.getShips()[moveableShip];
			rotateShip(moveableShip, ship);
		}
		
		placeOnTiles();
		moveableShip = -1;
		return true;
	}
}