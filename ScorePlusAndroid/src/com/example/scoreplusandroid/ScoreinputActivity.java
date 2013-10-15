package com.example.scoreplusandroid;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scoreplusandroid.controller.MenuController;

public class ScoreinputActivity extends Activity {
  Calendar cal;
  Activity activity;

  @Override
  public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_scoreinput);
      cal = Calendar.getInstance(); //カレンダー用
  }

  public void onClick(View v){
	  EditText userName = (EditText)findViewById(R.id.txtUserName);
	  EditText firstName = (EditText)findViewById(R.id.txtFirstName);
	  EditText lastName = (EditText)findViewById(R.id.txtLastName);

	  if((userName==null) || ( userName.length() < 1 ) ){
          System.out.println("ユーザーネームを正しく入力してください");
      }

	  Toast toast = Toast.makeText(this, userName.getText() + "/" + firstName.getText() + "/" + lastName.getText(), Toast.LENGTH_LONG);
	  toast.show();
  }

  //カレンダー用
  public void onDateClick(View view) {
	    DatePickerDialog d_date = new DatePickerDialog(
	        this,
	        new DatePickerDialog.OnDateSetListener() {
	          public void onDateSet(DatePicker view, int year,
	            int monthOfYear, int dayOfMonth) {
	            EditText txtDate = (EditText)ScoreinputActivity.this.findViewById(R.id.txtDate);
	            txtDate.setText(
	              String.format("%02d/%02d/%02d", year, monthOfYear + 1, dayOfMonth));
	          }
	        },
	        cal.get(Calendar.YEAR),
	        cal.get(Calendar.MONTH),
	        cal.get(Calendar.DAY_OF_MONTH)
	    );
	    d_date.show();
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