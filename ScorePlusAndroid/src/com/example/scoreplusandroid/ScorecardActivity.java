package com.example.scoreplusandroid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.scoreplusandroid.controller.MenuController;
import com.example.scoreplusandroid.utils.DatabaseHelper;

/*
 * スコアカード入力画面
 */
public class ScorecardActivity extends Activity {
    private DatabaseHelper helper = null;
    static int SID = 1;
    static int HOLE = 1;
	@Override
  public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_scorecard);
      setParams();//画面表示用パラメータセット

      //スコア入力項目に基づいて登録項目条件をセット
      StringBuffer str = new StringBuffer();
      try{
          BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("set.dat")));
          while(reader.ready()){
              str.append(reader.readLine());
          }
          reader.close();
 		  JSONArray jArray = new JSONArray(str.toString());
 		  int intPn = (Integer) jArray.getJSONObject(0).get("pn");
 		  if(intPn == 0){//ペナルティ
 		      EditText pn = (EditText)findViewById(R.id.txtPenalty);
 		      pn.setEnabled(false);
 		  }
 		  int intPut = (Integer) jArray.getJSONObject(0).get("put");
 		  if(intPut ==0){//パット
 		      EditText put = (EditText)findViewById(R.id.txtPut);
 		      put.setEnabled(false);
 		  }
 		  int intOb = (Integer)jArray.getJSONObject(0).get("ob");
 		  if(intOb ==0){//OB
 		      EditText ob = (EditText)findViewById(R.id.txtOb);
 		      ob.setEnabled(false);
 		  }
 		  int intFw = (Integer)jArray.getJSONObject(0).get("fw");
 		  if(intFw==0){//フェアウェイ
 		      //ToggleButton left  = (ToggleButton)findViewById(R.id.tglLeft);
 		      //ToggleButton right = (ToggleButton)findViewById(R.id.tglRignt);
 		      ToggleButton fw    = (ToggleButton)findViewById(R.id.tglFw);
 		      //left.setEnabled(false);
 		      //right.setEnabled(false);
 		      fw.setEnabled(false);
 		  }
 		  int intBk = (Integer)jArray.getJSONObject(0).get("bk");
 		  if(intBk==0){//バンカー
 		      ToggleButton bk = (ToggleButton)findViewById(R.id.tglBk);
 		      bk.setEnabled(false);
 		  }
      }catch(FileNotFoundException e){
    	  e.printStackTrace();
      }catch(IOException e){
    	  e.printStackTrace();
      }catch (JSONException e){
		e.printStackTrace();
	  }
  }

  public void onClick(View v){
	  EditText score = (EditText)findViewById(R.id.txtScore);
      EditText put   = (EditText)findViewById(R.id.txtPut);
      EditText ob    = (EditText)findViewById(R.id.txtOb);
      EditText pn    = (EditText)findViewById(R.id.txtPenalty);
      //ToggleButton left  = (ToggleButton)findViewById(R.id.tglLeft);
      //ToggleButton right = (ToggleButton)findViewById(R.id.tglRignt);
      ToggleButton fw    = (ToggleButton)findViewById(R.id.tglFw);
      ToggleButton bk    = (ToggleButton)findViewById(R.id.tglBk);
      //左ボタン
  	  if(v.getId()==R.id.btnLeft){
  		  updateSqlite(score.getText().toString(), put.getText().toString(), ob.getText().toString(), pn.getText().toString(), bk.isChecked(), fw.isChecked());
          HOLE--;
          setParams();
      }
      //右ボタン
  	  if(v.getId()==R.id.btnRight){
  		  updateSqlite(score.getText().toString(), put.getText().toString(), ob.getText().toString(), pn.getText().toString(), bk.isChecked(), fw.isChecked());
          HOLE++;
          setParams();
      }
      //戻るボタン
  	  if(v.getId()==R.id.btnBack){
  		  updateSqlite(score.getText().toString(), put.getText().toString(), ob.getText().toString(), pn.getText().toString(), bk.isChecked(), fw.isChecked());
          ScoreActivity.HOLE = HOLE;
          Intent intent = new Intent(this, ScoreActivity.class);
          startActivity(intent);
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

  //画面表示用パラメータセット
  private void setParams(){
      TextView textHole = (TextView) findViewById(R.id.txtHole);
      textHole.setText("ホール" + HOLE);

      //取得したスコア情報をlistに入れる
      String[] list = new String[11];
      list = getScore();

      TextView textScore = (TextView) findViewById(R.id.txtScore);
      textScore.setText(list[3]);//hole
      TextView textPut = (TextView) findViewById(R.id.txtPut);
      textPut.setText(list[4]);//score
      TextView textOb = (TextView) findViewById(R.id.txtOb);
      textOb.setText(list[8]);//ob
      TextView textPn = (TextView) findViewById(R.id.txtPenalty);
      textPn.setText(list[9]);//pn
      //ToggleButton tglLeft  = (ToggleButton)findViewById(R.id.tglLeft);
      //ToggleButton tglRight = (ToggleButton)findViewById(R.id.tglRignt);
      ToggleButton tglFw    = (ToggleButton)findViewById(R.id.tglFw);
      ToggleButton tglBk    = (ToggleButton)findViewById(R.id.tglBk);
      if(list[5].equals("1")){//left
        //  tglLeft.setChecked(true);
      }else{
    	  //tglLeft.setChecked(false);
      }
      if(list[6].equals("1")){//right
         // tglRight.setChecked(true);
      }else{
    	 // tglRight.setChecked(false);
      }
      if(list[7].equals("1")){//fw
          tglFw.setChecked(true);
      }else{
          tglFw.setChecked(false);
      }
      if(list[10].equals("1")){//bk
          tglBk.setChecked(true);
      }else{
          tglBk.setChecked(false);
      }

      if(HOLE <= 1){
    	  findViewById(R.id.btnLeft).setEnabled(false);
    	  findViewById(R.id.btnRight).setEnabled(true);
      }
      if(HOLE >= 17){
    	  findViewById(R.id.btnRight).setEnabled(false);
    	  findViewById(R.id.btnLeft).setEnabled(true);
      }
      if(HOLE <= 17 && HOLE >= 2){
    	  findViewById(R.id.btnLeft).setEnabled(true);
    	  findViewById(R.id.btnRight).setEnabled(true);
      }
      if(HOLE == 18){
    	  findViewById(R.id.btnLeft).setEnabled(true);
    	  findViewById(R.id.btnRight).setEnabled(false);
      }
  }

  //スコア情報を取得
  private String[] getScore(){
	  helper = new DatabaseHelper(this);
	  String[] array = new String[11];
	  SQLiteDatabase db = helper.getReadableDatabase();
	  String[] cols = {"id", "sid", "hole", "score", "put", "left", "right", "fw", "ob", "pn", "bk"};
      String[] params = {Integer.toString(SID), Integer.toString(HOLE)};
      Cursor cs = db.query("scores", cols, "sid=? AND hole=?", params, null, null, null, null);
      if(cs.moveToFirst()){
          array[0]  = cs.getString(0);  //id
          array[1]  = cs.getString(1);  //sid
          array[2]  = cs.getString(2);  //hole
          array[3]  = cs.getString(3);  //score
          array[4]  = cs.getString(4);  //put
          array[5]  = cs.getString(5);  //left
          array[6]  = cs.getString(6);  //right
          array[7]  = cs.getString(7);  //fw
          array[8]  = cs.getString(8);  //ob
          array[9]  = cs.getString(9);  //pn
          array[10] = cs.getString(10); //bk
      }
	  return array;
  }

  //スコアカード切り替え時に入力情報で端末DBをアップデート
  private void updateSqlite(String score, String put, String ob, String pn, Boolean bk, Boolean fw){
	  helper = new DatabaseHelper(this);
	  SQLiteDatabase db = helper.getWritableDatabase();
	  ContentValues cv = new ContentValues();
	  int intBk    = bk?1:0;
	  int intFw    = fw?1:0;
	  //int intLeft  = left?1:0;
	  //int intRight = right?1:0;
	  cv.put("score", score);
	  cv.put("put", put);
	  cv.put("ob", ob);
	  cv.put("pn", pn);
	  cv.put("bk", intBk);
	  //cv.put("left", intLeft);
	  //cv.put("right", intRight);
	  cv.put("fw", intFw);
	  String[] params = {String.valueOf(SID), String.valueOf(HOLE)};
	  db.update("scores", cv, "sid=? AND hole=?", params);
  }

  @Override
  public boolean dispatchKeyEvent(KeyEvent event) {
	  //戻るボタン無効化
      if (event.getAction()==KeyEvent.ACTION_DOWN) {
          if(event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
              return false;
          }
      }
      return super.dispatchKeyEvent(event);
  }
}