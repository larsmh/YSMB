package com.tdt4240.yousunkmybattleship;

import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

public class MainMenuState extends State implements TouchListener {
	TextButton newGame;
	TextButton settings;
	
	public MainMenuState(){
		newGame = new TextButton(Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT*0.4f, "New Game");
		settings = new TextButton(Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT*0.6f, "Settings");
	}
	
	public void draw(Canvas canvas) {
		newGame.draw(canvas);
		settings.draw(canvas);
	}
	/*
	public void update(float dt) {

	}*/
	
	public boolean onTouchDown(MotionEvent event){
		if(newGame.onTouchDown(event))
			Constants.game.pushState(new ShipPlacementState());
		else if(settings.onTouchDown(event))
			Constants.game.pushState(new SettingsState());
		return true;
	}

}
