package com.example.scoreplusandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.scoreplusandroid.controller.ActivityController;
import com.example.scoreplusandroid.controller.MenuController;
import com.example.scoreplusandroid.utils.GateChecker;

/*
 * Facebook登録時のユーザ名登録画面
 */
public class UsernameActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username);
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
    	new GateChecker();
		if(GateChecker.validate(this)){
			new ActivityController().submit(this, v);
		}else{
		    Toast toast = Toast.makeText(this, "入力エラー", Toast.LENGTH_LONG);
		    toast.show();
		}
    }
}