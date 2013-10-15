package com.example.scoreplusandroid;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.scoreplusandroid.controller.MenuController;
/*
 * 外部アプリ連携画面
 */
public class AccountActivity extends Activity implements CompoundButton.OnCheckedChangeListener {
	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_account);

	  //トグルスイッチのインスタンス生成
	  Switch s = (Switch) findViewById(R.id.switchFacebook);
	  s.setOnCheckedChangeListener(this);
	  s.setSwitchTypeface(Typeface.DEFAULT_BOLD, Typeface.ITALIC);

	  //Facebookと連携済みか否かでデフォルトのOn/Offを切り替えてください。
      //s.setChecked(false);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	    if(isChecked == true){//Facebook連携処理
            Intent i = new Intent(this, com.example.scoreplusandroid.FacebookaccountActivity.class);
            startActivity(i);
	    }else{
	    	//Facebook連携解除の処理を記載
	    }

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
