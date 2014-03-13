package com.tdt4240.yousunkmybattleship;

import com.tdt4240.yousunkmybattleship.state.MainMenuState;

import sheep.game.Game;
import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);

		Constants.WINDOW_HEIGHT = dm.heightPixels;
		Constants.WINDOW_WIDTH = dm.widthPixels;
		Constants.setDimensions();

		Game game = new Game(this, null);

		Constants.game = game;
		game.pushState(new MainMenuState());
		setContentView(game);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
	
	@Override
	public void onBackPressed() {
	}
	@Override
    protected void onPause() {
		onStop();
    }
	
	@Override
    protected void onStop() {
		android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }

}
