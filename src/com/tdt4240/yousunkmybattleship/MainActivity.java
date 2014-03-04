package com.tdt4240.yousunkmybattleship;

import sheep.game.Game;
import android.os.Bundle;
import android.app.Activity;
import android.view.Display;
import android.view.Menu;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Display display = getWindowManager().getDefaultDisplay(); 
		Constants.width = display.getWidth();
		Constants.height = display.getHeight();
        Game game = new Game(this, null);
        Constants.game=game;
        game.pushState(new MainMenuState());
        setContentView(game);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
