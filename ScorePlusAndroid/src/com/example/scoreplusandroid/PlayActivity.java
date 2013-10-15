package com.example.scoreplusandroid;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.scoreplusandroid.controller.ActivityController;
import com.example.scoreplusandroid.controller.MenuController;

/*
 * スコア入力項目選択（新規用）
 */
public class PlayActivity extends Activity {
	private Calendar cal;
    ArrayAdapter<String> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play);
		cal = Calendar.getInstance(); //カレンダー用

		//ゴルフ場情報を取得
	    StringBuffer strb = new StringBuffer();
	    try{
	        BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("golfcourseinfo.dat")));
	        while(reader.ready()){
	            strb.append(reader.readLine());
	        }
	        reader.close();
	        JSONArray jArray = new JSONArray(strb.toString());
	        String strGolfcoursename = (String) jArray.getJSONObject(0).get("golfcourseName");
		    TextView golfcoursename = (TextView)findViewById(R.id.txtGolfcoursename);
		    golfcoursename.setText(strGolfcoursename);
	    }catch(FileNotFoundException e){
		  	  e.printStackTrace();
		}catch(IOException e){
		    	e.printStackTrace();
		}catch (JSONException e){
	            e.printStackTrace();
		}

		//キャッシュしたスコア入力項目に基づいて登録項目条件をセット
	    StringBuffer str = new StringBuffer();
	    try{
	        BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("set.dat")));
	        while(reader.ready()){
	            str.append(reader.readLine());
	        }
	        reader.close();
	        JSONArray jArray = new JSONArray(str.toString());

	        int intPn = (Integer) jArray.getJSONObject(0).get("pn");
 		    Switch pn = (Switch)findViewById(R.id.switchPn);
	 	    if(intPn == 0){//ペナルティ
	 		    pn.setChecked(false);
	 		}else{
	 			pn.setChecked(true);
	 		}
	 		int intPut = (Integer) jArray.getJSONObject(0).get("put");
 		    Switch put = (Switch)findViewById(R.id.switchPut);
	 		if(intPut ==0){//パット
	 		    put.setChecked(false);
	 		}else{
	 			put.setChecked(true);
	 		}
	 		int intOb = (Integer)jArray.getJSONObject(0).get("ob");
 		    Switch ob = (Switch)findViewById(R.id.switchOb);
	 		if(intOb ==0){//OB
	 		    ob.setChecked(false);
	 		}else{
	 			ob.setChecked(true);
	 		}
	 		int intFw = (Integer)jArray.getJSONObject(0).get("fw");
 		    Switch fw = (Switch)findViewById(R.id.switchFw);
	 		if(intFw==0){//フェアウェイ
	 		    fw.setChecked(false);
	 		}else{
	 			fw.setChecked(true);
	 		}
	 		int intBk = (Integer)jArray.getJSONObject(0).get("bk");
 		    Switch bk = (Switch)findViewById(R.id.switchBk);
	 		if(intBk==0){//バンカー
	 		    bk.setChecked(false);
	 		}else{
	 			bk.setChecked(true);
	 		}

	 		//ラウンド日
	        String strDate = (String)jArray.getJSONObject(0).get("date");
            EditText date = (EditText)findViewById(R.id.txtDate);
            date.setText(strDate);


            //前半ホール
	        int intFirsthole = (Integer)jArray.getJSONObject(0).get("first_hole");
	        Spinner first_hole = (Spinner)findViewById(R.id.spnFirsthole);
	        first_hole.setSelection(intFirsthole);

            //後半ホール
	        int intLasthole = (Integer)jArray.getJSONObject(0).get("last_hole");
	        Spinner last_hole = (Spinner)findViewById(R.id.spnLasthole);
            last_hole.setSelection(intLasthole);

            //天気
	        int intWeather = (Integer)jArray.getJSONObject(0).get("weather");
	        Spinner weather = (Spinner)findViewById(R.id.spnWeather);
	        weather.setSelection(intWeather);

            //ティー
	        int intTee = (Integer)jArray.getJSONObject(0).get("tee");
	        Spinner tee = (Spinner)findViewById(R.id.spnTee);
	        tee.setSelection(intTee);
	    }catch(FileNotFoundException e){
	  	  e.printStackTrace();
	    }catch(IOException e){
	    	e.printStackTrace();
	    }catch (JSONException e){
            e.printStackTrace();
		}
	}

	//カレンダー用
    public void onDateClick(View view) {
	    DatePickerDialog d_date = new DatePickerDialog(this,
		        new DatePickerDialog.OnDateSetListener() {
		          public void onDateSet(DatePicker view, int year,
		            int monthOfYear, int dayOfMonth) {
		            EditText txtDate = (EditText)PlayActivity.this.findViewById(R.id.txtDate);
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

    public void onClick(View v){
    	//設定項目をキャッシュ
        Switch put = (Switch)findViewById(R.id.switchPut);
        Switch fw  = (Switch)findViewById(R.id.switchFw);
        Switch ob  = (Switch)findViewById(R.id.switchOb);
        Switch bk  = (Switch)findViewById(R.id.switchBk);
        Switch pn  = (Switch)findViewById(R.id.switchPn);
        int flgPut = 0;
        if(put.isChecked()){//パット
            flgPut = 1;
        }
        int flgFw = 0;
        if(fw.isChecked()){//フェアウェイ
            flgFw = 1;
        }
        int flgOb = 0;
        if(ob.isChecked()){//OB
            flgOb = 1;
        }
        int flgBk = 0;
        if(bk.isChecked()){//バンカー
            flgBk = 1;
        }
        int flgPn = 0;
        if(pn.isChecked()){//ペナルティ
            flgPn = 1;
        }

    	EditText date = (EditText)findViewById(R.id.txtDate);
        Spinner  first_hole = (Spinner)findViewById(R.id.spnFirsthole);
        Spinner  last_hole  = (Spinner)findViewById(R.id.spnLasthole);
        Spinner  weather    = (Spinner)findViewById(R.id.spnWeather);
        Spinner  tee        = (Spinner)findViewById(R.id.spnTee);

        JSONArray jArray = new JSONArray();
        JSONObject jo = new JSONObject();
        try {
            jo.put("put", flgPut);
            jo.put("fw", flgFw);
            jo.put("ob", flgOb);
            jo.put("bk", flgBk);
            jo.put("pn", flgPn);
            jo.put("date", date.getText().toString());
            jo.put("first_hole", first_hole.getSelectedItemPosition());
            jo.put("last_hole", (Integer)last_hole.getSelectedItemPosition());
            jo.put("weather", (Integer)weather.getSelectedItemPosition());
            jo.put("tee", (Integer)tee.getSelectedItemPosition());
        }catch (JSONException e){
            e.printStackTrace();
        }
        jArray.put(jo);
        try{
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("set.dat", Context.MODE_PRIVATE)));
        	writer.write(jArray.toString());
        	writer.close();
        }catch(FileNotFoundException e){
        	e.printStackTrace();
        }catch(IOException e){
        	e.printStackTrace();
        }
        new ActivityController().submit(this, v);
    }
}