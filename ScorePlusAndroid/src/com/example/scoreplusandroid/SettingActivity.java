package com.example.scoreplusandroid;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.scoreplusandroid.controller.ActivityController;
import com.example.scoreplusandroid.controller.MenuController;

/*
 * セッティング画面
 */
public class SettingActivity extends Activity implements OnItemClickListener{
  Activity activity;
  ArrayAdapter<String> adapter;

  @Override
  public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_setting);
      adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

      //セッティングページリスト作成
      adapter.add("プロフィール");
      adapter.add("外部アカウント連携");
      adapter.add("ログアウト");
      ListView listView = (ListView) findViewById(R.id.listSetting);
      listView.setAdapter(adapter);
      listView.setOnItemClickListener(this);
  }

  public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
	  String str = adapter.getItem(pos);
  	  if(str.equals("ログアウト")){
          try{
              BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("login.dat", Context.MODE_PRIVATE)));
        	  writer.write("");
        	  writer.close();
    	  }catch(FileNotFoundException e){
              e.printStackTrace();
          }catch(IOException e){
              e.printStackTrace();
          }
  	  }
	  new ActivityController().submit(this, view, str);
  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    getMenuInflater().inflate(R.menu.main_login, menu);
		return true;
	}

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
      return new MenuController().submit(this, item);
  }
}