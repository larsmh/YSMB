package com.tdt4240.yousunkmybattleship.state;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.R;
import android.graphics.Canvas;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.input.TouchListener;

public class SettingsState extends State implements TouchListener {
	Image bg = new Image(R.drawable.menu_bg);

	public SettingsState() {

	}

	public void draw(Canvas canvas) {
		bg.draw(canvas, 0, 0);
	}

	public boolean onTouchDown(MotionEvent event) {
		Constants.game.popState();
		return true;
	}
}
