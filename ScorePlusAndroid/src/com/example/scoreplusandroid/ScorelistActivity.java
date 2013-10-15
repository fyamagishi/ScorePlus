package com.example.scoreplusandroid;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.scoreplusandroid.controller.MenuController;

/*
 * スコア一覧画面
 */
public class ScorelistActivity extends Activity implements OnItemClickListener{
    ArrayAdapter<String> adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorelist);
        //検索結果リスト表示（API完成後DBより取得に変更）
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.add("嶋崎ゴルフ倶楽部");
        adapter.add("荻野目カントリー");
        adapter.add("ムーンレイク大内");
        ListView listView = (ListView) findViewById(R.id.golfcourselist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
    }

    //検索結果listクリック時
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        /*
         * とりあえずゴルフ場名だけ保存していますが、APIが完成したら取得した
         * 他のゴルフ場データも入れておくと便利かも
         */
    	String golfcourseName = adapter.getItem(pos); //ゴルフ場名
        JSONArray jArray = new JSONArray();
        JSONObject jo = new JSONObject();
        try {
            jo.put("golfcourseName", golfcourseName);
        }catch (JSONException e){
            e.printStackTrace();
        }
        jArray.put(jo);
        try{
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("golfcourseinfo.dat", Context.MODE_PRIVATE)));
        	writer.write(jArray.toString());
        	writer.close();
        }catch(FileNotFoundException e){
        	e.printStackTrace();
        }catch(IOException e){
        	e.printStackTrace();
        }

        Intent i = new Intent(this, com.example.scoreplusandroid.ScoremenuActivity.class);
        startActivity(i);
    }

    //検索履歴画面へ切り替え
    public void onClick(View v){
        Intent i = new Intent(this, com.example.scoreplusandroid.PlayActivity.class);
        startActivity(i);
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