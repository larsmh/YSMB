package com.tdt4240.yousunkmybattleship.state;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Graphics;
import com.tdt4240.yousunkmybattleship.model.Player;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

/**
 * This is the first screen that appears when one starts the application.
 * 
 * The user can either start the game or go to the settings menu.
 * 
 */

public class MainMenuState extends State implements TouchListener {
	Image bg = Graphics.bg;
	TextButton newGame;
	//TextButton settings;
	TextButton instructions;

	public MainMenuState() {
		Log.d("!!!!", Constants.WINDOW_HEIGHT + " " + Constants.WINDOW_WIDTH
				+ " " + Constants.TILE_SIZE + " " + Constants.START_OF_GRID);
		for (int i = 0; i < Graphics.buttonPaint.length; i++) {
			Graphics.buttonPaint[i] = new Paint();
			Graphics.buttonPaint[i].setColor(Color.WHITE);
			Graphics.buttonPaint[i].setTextSize(Constants.WINDOW_WIDTH / 18);
		}
		Graphics.warningPaint.setTextSize(Constants.WINDOW_WIDTH/18);
		Graphics.warningPaint.setColor(Color.RED);
		newGame = new TextButton(Constants.WINDOW_WIDTH * 0.37f,
				Constants.WINDOW_HEIGHT * 0.2f, "New Game", Graphics.buttonPaint);
		/*settings = new TextButton(Constants.WINDOW_WIDTH * 0.4f,
				Constants.WINDOW_HEIGHT * 0.36f, "Settings", Graphics.buttonPaint);*/
		instructions = new TextButton(Constants.WINDOW_WIDTH * 0.36f,
				Constants.WINDOW_HEIGHT * 0.52f, "Instructions",
				Graphics.buttonPaint);
	}

	public void draw(Canvas canvas) {
		bg.draw(canvas, 0, 0);
		newGame.draw(canvas);
		//settings.draw(canvas);
		instructions.draw(canvas);
	}

	public boolean onTouchDown(MotionEvent event) {
		if (newGame.onTouchDown(event)){
			Constants.p1 = new Player("Player1");
			Constants.p2 = new Player("Player2");
			Constants.p = Constants.p1;
			Constants.game.pushState(new ChangeTurnState());
		}
		/*else if (settings.onTouchDown(event))
			Constants.game.pushState(new SettingsState());*/
		else if (instructions.onTouchDown(event))
			Constants.game.pushState(new InstructionsState());
		return true;
	}

}
