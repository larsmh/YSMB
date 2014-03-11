package com.tdt4240.yousunkmybattleship.state;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.R;
import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

public class SettingsState extends State implements TouchListener {
	Image bg = new Image(R.drawable.menu_bg);
	TextButton backButton;

	public SettingsState() {
		backButton = new TextButton(Constants.WINDOW_WIDTH * 0.39f,
				Constants.WINDOW_HEIGHT * 0.2f, "back to main", Constants.paint);

	}

	public void draw(Canvas canvas) {
		bg.draw(canvas, 0, 0);
		backButton.draw(canvas);
	}

	public boolean onTouchDown(MotionEvent event) {
		if(backButton.onTouchDown(event)){
			//Constants.game.popState();
			Constants.game.pushState(new GameOverState("Player1"));
		}
		
		return true;
	}
}
