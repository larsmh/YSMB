package com.tdt4240.yousunkmybattleship.state;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Graphics;
import sheep.game.State;
import sheep.graphics.Image;
import sheep.gui.TextButton;
import sheep.input.TouchListener;

/**
 * This screen appears when the players change turns.
 * 
 * It indicates that it is to the next one to play, and the current player has
 * to click on continue before the game screen appears.It prevents from
 * cheating.
 * 
 */

public class ChangeTurnState extends State implements TouchListener {
	Image bg = Graphics.bg;
	TextButton progress;

	public ChangeTurnState() {
		progress = new TextButton(Constants.WINDOW_WIDTH * 0.4f,
				Constants.WINDOW_HEIGHT * 0.35f, "Continue", Graphics.buttonPaint);
	}

	public void draw(Canvas canvas) {
		bg.draw(canvas, 0, 0);
		Graphics.paint.setColor(Color.WHITE);
		Graphics.paint.setTextSize(Constants.WINDOW_WIDTH/6.5f);
		canvas.drawText(Constants.p.getName()+"'s turn", Constants.WINDOW_WIDTH*0.02f, 
				Constants.WINDOW_HEIGHT*0.2f, Graphics.paint);
		progress.draw(canvas);
	}

	public boolean onTouchDown(MotionEvent event) {
		if (progress.onTouchDown(event)) {
			if (Constants.p2.isReady())
				Constants.game.pushState(new DropState());
			else
				Constants.game.pushState(new ShipPlacementState());
			return true;
		}
		return false;
	}
}
