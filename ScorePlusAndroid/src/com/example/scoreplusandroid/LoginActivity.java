package com.example.scoreplusandroid;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.http.client.CookieStore;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scoreplusandroid.controller.ActivityController;
import com.example.scoreplusandroid.controller.MenuController;
import com.example.scoreplusandroid.request.GetProfileTask;
import com.example.scoreplusandroid.request.PostLoginTask;
import com.example.scoreplusandroid.utils.GateChecker;

/*
 * ログイン画面
 */
public class LoginActivity extends Activity{
	public static int RESULT = 0;
	public static CookieStore CS;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
	  setContentView(R.layout.activity_login);
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
		if(v.getId()==R.id.btnForget){
		    Toast toast = Toast.makeText(this, "PC版パスワード再送信画面へ", Toast.LENGTH_LONG);
		    toast.show();
		}else if(v.getId()==R.id.btnFacebook){
		    Toast toast = Toast.makeText(this, "Facebook認証", Toast.LENGTH_LONG);
		    toast.show();
		}else{
			new GateChecker();
			if(GateChecker.validate(this)){
		    	EditText email = (EditText)findViewById(R.id.txtEmail);
				EditText password = (EditText)findViewById(R.id.txtPassword);
				String[] array = new String[2];
                array[0] = email.getText().toString();
                array[1] = password.getText().toString();
                PostLoginTask plt = new PostLoginTask();
                plt.execute(array);
                if(RESULT==200){
                    //取得したクッキー情報をキャッシュ
                    try{
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("login.dat", Context.MODE_PRIVATE)));
                        writer.write(CS.toString());
                        writer.close();
                    }catch(FileNotFoundException e){
                    	e.printStackTrace();
                    }catch(IOException e){
                    	e.printStackTrace();
                    }

                    GetProfileTask gpt = new GetProfileTask();
                    gpt.execute();
                    new ActivityController().submit(this, v);
                }else{
    			    Toast toast = Toast.makeText(this, "該当ユーザなし", Toast.LENGTH_LONG);
    			    toast.show();
                }
			}else{
			    Toast toast = Toast.makeText(this, "正しい値を入力してください。", Toast.LENGTH_LONG);
			    toast.show();
			}
		}
	}
}