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
			sprites[i] = new Sprite(Constants.p.getShips()[i].getType().getImgHor());
		placeOnTiles();
	}

	/**
	 * Turns the sprite (from vertical to horizontal or vice versa)
	 * 
	 * @param spriteIndex
	 *            the index of the sprite to turn
	 * @param ship
	 *            the ship to turn
	 * 
	 */
	private void changeSprite(int spriteIndex, Ship ship) {
		if (ship.isVertical()) {
			sprites[spriteIndex] = new Sprite(ship.getType().getImgHor());
		} else {
			sprites[spriteIndex] = new Sprite(ship.getType().getImgVert());
		}
	}

	public void rotateShip() {
		if (moveableShip != -1) {
			Ship ship = Constants.p.getShips()[moveableShip];
			changeSprite(moveableShip, ship);
			ship.changeDirection();
		}
	}

	/**
	 * Places the ship being moved on the tiles closest to it and add the ships
	 * on the player's board
	 * 
	 */
	public void placeOnTiles() {
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
	public boolean isMoveable(int s) {
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
		startClickTime = Calendar.getInstance().getTimeInMillis();
		return true;
	}

	public boolean onTouchMove(MotionEvent event) {
		for (int i = sprites.length - 1; i >= 0; i--) {
			float x = sprites[i].getPosition().getX();
			float y = sprites[i].getPosition().getY();

			// if(sprites[i].getBoundingBox().contains(event.getX(),
			// event.getY()) && isMoveable(i)){
			if (event.getX() >= sprites[i].getX()
					- sprites[i].getOffset().getX()
					&& event.getX() <= sprites[i].getX()
							+ sprites[i].getOffset().getX()
					&& event.getY() >= sprites[i].getY()
							- sprites[i].getOffset().getY()
					&& event.getY() <= sprites[i].getY()
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

				// Move to onTouchUp?
				sprites[i].setPosition(x, y);
				Constants.p.getShips()[i].placeShip(
						(int) ((Constants.TILE_SIZE / 2 + x - sprites[i]
								.getOffset().getX()) / Constants.TILE_SIZE),
						(int) ((Constants.TILE_SIZE / 2
								- Constants.START_OF_GRID + y - sprites[i]
								.getOffset().getY()) / 108));
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

	/**
	 * Add the player's ships on his board. The integer correspond to the ship
	 * sprite: 4 is an AC_Carrier, 3 is a Battleship, 2 is a Submarine, 1 is a
	 * Destroyer and 0 is a Boat.
	 * 
	 */
	/* This method is not working properly, and is not needed any longer
	public void markOnPlayerBoard() {
		Ship[] ships = Constants.p.getShips();

		for (int i = 0; i < ships.length; i++) {
			if (ships[i].isVertical()) {
				for (int k = ships[i].getPosY(); k < (ships[i].getPosY() + ships[i]
						.getType().getSprite()); k++) {
					Constants.p.shipPlaced(i, k, ships[i]);
				}
			} else {
				for (int l = ships[i].getPosX(); l < (ships[i].getPosX() + ships[i]
						.getType().getSprite()); l++) {
					Constants.p.shipPlaced(i, l, ships[i]);
				}
			}
		}
	}*/
}
