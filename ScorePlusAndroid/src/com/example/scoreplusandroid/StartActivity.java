package com.example.scoreplusandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.scoreplusandroid.controller.MenuController;

/*
 * スコア管理画面
 */
public class StartActivity extends Activity {
    ArrayAdapter<String> adapter;
    ListView list;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_start);
  }

  public void onClickScore(View v){
      if(v.getId()==R.id.btnScoreRegistration){
          Intent i = new Intent(this, com.example.scoreplusandroid.SearchgolfcourseActivity.class);
          startActivity(i);
       }
      if(v.getId()==R.id.btnScoreList){
          Intent i = new Intent(this, com.example.scoreplusandroid.ScorelistActivity.class);
          startActivity(i);
       }
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
}