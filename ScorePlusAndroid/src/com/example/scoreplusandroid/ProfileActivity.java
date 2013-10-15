package com.example.scoreplusandroid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.scoreplusandroid.controller.MenuController;
import com.example.scoreplusandroid.request.GetProfileTask;
import com.example.scoreplusandroid.request.PutProfileTask;

/*
 * プロフィール画面
 */
public class ProfileActivity extends Activity {
  Calendar cal;
  Activity activity;
  public static String EMAIL = "";
  public static String USERNAME = "";
  private static final int REQUEST_GALLERY = 0;
  private ImageView imgView;

  @Override
  public void onCreate(Bundle savedInstanceState) {
	  super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_profile);
      cal = Calendar.getInstance();
      //CheckLogin cl = new CheckLogin();
      StringBuffer str = new StringBuffer();
      try{
          BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("memo.dat")));
      	  while(reader.ready()){
      		  str.append(reader.readLine());
      	  }
      	  reader.close();
      }catch(FileNotFoundException e){
      	  e.printStackTrace();
      }catch(IOException e){
      	  e.printStackTrace();
      }
      GetProfileTask gpt = new GetProfileTask();
      gpt.execute(str.toString());
      EditText editEmail = (EditText)findViewById(R.id.txtEmail);
      editEmail.setText(EMAIL);
      EditText editUsername = (EditText)findViewById(R.id.txtUserName);
      editUsername.setText(USERNAME);
  }

  public void onClick(View v){
      if(v.getId()==R.id.btnImage){
	      imgView = (ImageView)findViewById(R.id.profileimg);
		  // ギャラリー呼び出し
		  Intent intent = new Intent();
		  intent.setType("image/*");
		  intent.setAction(Intent.ACTION_GET_CONTENT);
		  startActivityForResult(intent, REQUEST_GALLERY);
	  }else{
	      EditText userName  = (EditText)findViewById(R.id.txtUserName);
	      EditText firstName = (EditText)findViewById(R.id.txtFirstName);
	      EditText lastName  = (EditText)findViewById(R.id.txtLastName);
	      EditText email     = (EditText)findViewById(R.id.txtEmail);
	      Spinner gender    = (Spinner)findViewById(R.id.spnGender);
	      Spinner pref      = (Spinner)findViewById(R.id.spnPrefecture);
	      EditText date      = (EditText)findViewById(R.id.txtDate);
	      if((userName==null) || ( userName.length() < 1 ) ){
              System.out.println("ユーザーネームを正しく入力してください");
	          Toast toast = Toast.makeText(this, userName.getText() + "/" + firstName.getText() + "/" + lastName.getText(), Toast.LENGTH_LONG);
	          toast.show();
          }else{
    	      String[] array = new String[7];
              array[0] = userName.getText().toString();
              array[1] = firstName.getText().toString();
              array[2] = lastName.getText().toString();
              array[3] = email.getText().toString();
              array[4] = (String)gender.getSelectedItem();
              array[5] = (String)pref.getSelectedItem();
              array[6] = date.getText().toString();
              PutProfileTask ppt = new PutProfileTask();
              ppt.execute(array);
          }
	  }
  }

  //カレンダー用
  public void onDateClick(View view) {
	    DatePickerDialog d_date = new DatePickerDialog(
	        this,
	        new DatePickerDialog.OnDateSetListener() {
	          public void onDateSet(DatePicker view, int year,
	            int monthOfYear, int dayOfMonth) {
	            EditText txtDate = (EditText)ProfileActivity.this.findViewById(R.id.txtDate);
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
	  getMenuInflater().inflate(R.menu.main_login, menu);
	  return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item){
      return new MenuController().submit(this, item);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
  	if(requestCode == REQUEST_GALLERY && resultCode == RESULT_OK) {
  		try{
  			InputStream in = getContentResolver().openInputStream(data.getData());
  			Bitmap img = BitmapFactory.decodeStream(in);
  			in.close();

  			// 選択した画像を表示
  			imgView.setImageBitmap(img);
  		}catch(Exception e){}
  	}
  }
}