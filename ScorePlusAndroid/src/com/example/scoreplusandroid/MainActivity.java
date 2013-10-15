package com.example.scoreplusandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.scoreplusandroid.controller.ActivityController;
import com.example.scoreplusandroid.controller.MenuController;

/*
 * トップ画面
 */
public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return new MenuController().submit(this, item);
    }

    public void onClick(View v){
		new ActivityController().submit(this, v);
    }
}