package com.example.scoreplusandroid;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scoreplusandroid.controller.ActivityController;
import com.example.scoreplusandroid.controller.MenuController;
import com.example.scoreplusandroid.request.PostRegistTask;
import com.example.scoreplusandroid.utils.GateChecker;

/*
 * 会員登録画面
 */
public class RegistrationActivity extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
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
    	if(v.getId()==R.id.btnRegistration){
    		new GateChecker();
			if(GateChecker.validate(this)){
		        EditText username = (EditText)findViewById(R.id.txtUserName);
		    	EditText email = (EditText)findViewById(R.id.txtEmail);
				EditText password = (EditText)findViewById(R.id.txtPassword);

				String[] array = new String[3];
                array[0] = email.getText().toString();
                array[1] = password.getText().toString();
				array[2] = username.getText().toString();

				PostRegistTask post = new PostRegistTask();
	            post.execute(array);
				new ActivityController().submit(this, v);
    		}else{
    		    Toast toast = Toast.makeText(this, "入力エラー", Toast.LENGTH_LONG);
    		    toast.show();
    		}
        }else{//facebook
			new ActivityController().submit(this, v);
		}
    }
}