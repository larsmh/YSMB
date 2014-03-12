package com.tdt4240.yousunkmybattleship.state;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.R;

import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

public class ChangeTurnState extends State implements TouchListener {
	Image bg = new Image(R.drawable.menu_bg);
	TextButton progress;

	public ChangeTurnState() {
		progress = new TextButton(Constants.WINDOW_WIDTH * 0.4f,
				Constants.WINDOW_HEIGHT * 0.5f, "Continue", Constants.paint);
	}

	public void draw(Canvas canvas) {
		bg.draw(canvas, 0, 0);
		progress.draw(canvas);
	}

	public boolean onTouchDown(MotionEvent event) {
		if (progress.onTouchDown(event)) {
			Constants.changeTurn();
			if (Constants.p2.isReady()) {
				Constants.game.pushState(new GameState());
				return true;
			} else {
				Constants.game.popState();
				return true;
			}
		}
		return false;
	}
}
