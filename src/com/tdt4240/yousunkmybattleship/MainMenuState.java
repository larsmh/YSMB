package com.tdt4240.yousunkmybattleship;

import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

public class MainMenuState extends State implements TouchListener {
	Image bg = new Image(R.drawable.menu_bgtest);
	TextButton newGame;
	TextButton settings;
	
	public MainMenuState(){
		newGame = new TextButton(Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT*0.2f, "New Game");
		settings = new TextButton(Constants.WINDOW_WIDTH/2, Constants.WINDOW_HEIGHT*0.4f, "Settings");
	}
	
	public void draw(Canvas canvas) {
		bg.draw(canvas, 0, 0);
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
