package com.tdt4240.yousunkmybattleship.state;

import android.graphics.Canvas;
import android.util.Log;

import com.tdt4240.yousunkmybattleship.Constants;
import com.tdt4240.yousunkmybattleship.Graphics;
import com.tdt4240.yousunkmybattleship.model.Ship;

import sheep.game.Sprite;

public class MyBoardState extends GameState {
	
	public MyBoardState(){
		Log.d("!!!!", ships[0].isVertical()+" " + ships[4].isVertical());
		createSprites();
	}
	
	public void draw(Canvas canvas) {
		super.draw(canvas);
		canvas.drawText(Constants.p.getName()+"'s turn", Constants.WINDOW_WIDTH*0.02f, 
				Constants.WINDOW_HEIGHT*0.2f, Graphics.paint);
		for (Sprite s : sprites)
			s.draw(canvas);
	}
	
	public void update(float dt){
		for (Sprite s : sprites)
			s.update(dt);
	}
}
