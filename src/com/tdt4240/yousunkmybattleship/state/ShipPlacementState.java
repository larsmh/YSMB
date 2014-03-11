package com.tdt4240.yousunkmybattleship.state;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Player;
import com.tdt4240.yousunkmybattleship.R;
import android.graphics.Canvas;
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
	Player p1, p2, p;
	int moveableShip;
	TextButton submit;
	private boolean waitForClick;

	public ShipPlacementState() {
		submit = new TextButton(Constants.WINDOW_WIDTH * 0.05f,
				Constants.START_OF_GRID - 192 / 2, "Submit", Constants.paint);
		p1 = new Player("Player1");
		p2 = new Player("Player2");
		p = p1;
		moveableShip = -1;
		waitForClick = true;
		createSprites();

	}

	private void createSprites() {
		sprites = new Sprite[5];
		for (int i = 0; i < sprites.length; i++)
			sprites[i] = new Sprite(p.getShips()[i].getType().getImg());
		placeOnTiles();
	}

	// Places the ship being moved on the tiles closest to it
	public void placeOnTiles() {
		for (int i = 0; i < sprites.length; i++)
			sprites[i].setPosition(p.getShips()[i].getPosX()
					* Constants.TILE_SIZE + sprites[i].getOffset().getX() + 1,
					Constants.START_OF_GRID + p.getShips()[i].getPosY()
							* Constants.TILE_SIZE
							+ sprites[i].getOffset().getY() + 1);
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

	public boolean onTouchMove(MotionEvent event) {
		for (int i = sprites.length - 1; i >= 0; i--) {
			float x = sprites[i].getPosition().getX(), y = sprites[i]
					.getPosition().getY();
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
						&& event.getX() <= Constants.WINDOW_WIDTH - sprites[i].getOffset().getX()) {
					x = event.getX();
				}
				if (event.getY() >= Constants.START_OF_GRID	+ sprites[i].getOffset().getY()
						&& event.getY() <= Constants.WINDOW_HEIGHT - sprites[i].getOffset().getY()) {
					y = event.getY();
				}
				// Move to onTouchUp
				sprites[i].setPosition(x, y);
				p.getShips()[i].placeShip(
						(int) ((Constants.TILE_SIZE / 2 + x - sprites[i]
								.getOffset().getX()) / Constants.TILE_SIZE),
						(int) ((Constants.TILE_SIZE / 2
								- Constants.START_OF_GRID + y - sprites[i]
								.getOffset().getY()) / 108),
						Constants.DirectionType.HORIZONTAL);
				return true;
			}
		}
		return false;
	}

	public boolean onTouchUp(MotionEvent event) {
		placeOnTiles();
		moveableShip = -1;
		return true;
	}
}
