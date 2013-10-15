package com.example.scoreplusandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.scoreplusandroid.controller.ActivityController;
import com.example.scoreplusandroid.controller.MenuController;

/*
 * 登録済スコアの管理画面
 */
public class ScoremenuActivity extends Activity {
    ArrayAdapter<String> adapter;
    ListView list;
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_scoremenu);
  }

  public void onClickScore(View v){
      if(v.getId()==R.id.btnScoreDelete){//削除API完成後、実装してください。
  	      Toast toast = Toast.makeText(this, "スコア削除", Toast.LENGTH_LONG);
  	      toast.show();
      }else{
	      new ActivityController().submit(this, v);
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