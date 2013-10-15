package com.example.scoreplusandroid;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scoreplusandroid.controller.MenuController;
import com.example.scoreplusandroid.utils.DatabaseHelper;

/*
 * スコア一覧画面
 */
public class ScoreActivity extends Activity implements OnItemClickListener{
    ArrayAdapter<String> adapter;
	static int HOLE = 1;
    static int SID = 1;
    private DatabaseHelper helper = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_score);
        setParams(); //画面表示用パラメータセット

        //スコア・パット・ペナルティ合計を取得
        int[] clist = new int[3];
        clist = countScore();
        int sumScore = clist[0];
        int sumPut   = clist[1];
        int sumPn    = clist[2];

        //取得したスコア情報をslistに入れる
        String[] slist = new String[11];
        slist = getScore();
        String score = slist[3].toString();
        String put   = slist[4].toString();
        String titles[] = {"山田華子"};
	    String tags[] = {score + "/" + put};
	    String descs[] = {"Total:" + sumScore + "(" + sumPut + ") -P" + sumPn + "        "};

        /*
         * 同伴者機能ができたら、表示データを配列に追加してください。
         */
        //String titles[] = {"山田華子", "TK4", "荻野太郎", "嶋崎次郎"};
	    //String tags[] = {score + "/" + put, "5/1", "4/2", "5/2"};
	    //String descs[] = {"Total:9(0) -P3        ", "Total:9(0) -P3        ","Total:9(0) -P3        ", "Total:9(0) -P3        "};
        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
	    for (int i = 0; i < titles.length; i++) {
	        HashMap<String, String> item = new HashMap<String, String>();
	        item.put("title", titles[i]);
	        item.put("tag", tags[i]);
	        item.put("desc", descs[i]);
	        data.add(item);
	    }
	    SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item, new String[] { "title", "tag", "desc" }, new int[] { R.id.title, R.id.tag, R.id.desc });
	    ListView list = (ListView) findViewById(R.id.list);
	    list.setAdapter(adapter);
        list.setOnItemClickListener(this);
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item){
        return new MenuController().submit(this, item);
    }

    public void onClick(View v){
        //左ボタン
    	if(v.getId()==R.id.btnLeft){
    		HOLE--;
    		setParams();
		}
    	//右ボタン
		if(v.getId()==R.id.btnRight){
    		HOLE++;
    		if(HOLE==19){
        	    TextView textHole=(TextView) findViewById(R.id.txtHole);
                textHole.setText("ホールアウト");
                setParams();
    		}else{
        	    TextView textHole=(TextView) findViewById(R.id.txtHole);
                textHole.setText("ホール" + HOLE);
                setParams();
    		}
		}
    	//保存ボタン
		if(v.getId()==R.id.btnSave){
		    Toast toast = Toast.makeText(this, "保存しました。", Toast.LENGTH_LONG);
		    toast.show();
		}
    	//戻るボタン
		if(v.getId()==R.id.btnBack){
			HOLE = 1;
	    	Intent intent = new Intent(this, PlayActivity.class);
	    	startActivity(intent);
		}
    }

    // リスト項目をクリックした時に実行するメソッド
    public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
        if(HOLE != 19){
    	    Intent intent = new Intent(this, ScorecardActivity.class);
    	    ScorecardActivity.HOLE = HOLE;
    	    startActivity(intent);
        }
    }

    //画面表示用パラメータセット
    private void setParams(){
    	TextView textHole=(TextView) findViewById(R.id.txtHole);
        textHole.setText("ホール" + HOLE);
        if(HOLE == 1){
        	setScore();
        	findViewById(R.id.btnSave).setEnabled(false);
        	findViewById(R.id.btnLeft).setEnabled(false);
        	findViewById(R.id.btnRight).setEnabled(true);
        }
        if(HOLE >= 2 && HOLE <= 18){
        	setScore();
        	findViewById(R.id.btnSave).setEnabled(false);
        	findViewById(R.id.btnLeft).setEnabled(true);
        	findViewById(R.id.btnRight).setEnabled(true);
        }
        if(HOLE == 19){
        	textHole.setText("ホールアウト");
        	setScore();
        	findViewById(R.id.btnSave).setEnabled(true);
        	findViewById(R.id.btnLeft).setEnabled(true);
        	findViewById(R.id.btnRight).setEnabled(false);
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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

	private void setScore(){
        //スコア・パット・ペナルティ合計を取得
        int[] clist = new int[3];
        clist = countScore();
        int sumScore = clist[0];
        int sumPut   = clist[1];
        int sumPn    = clist[2];

	    ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();

        if(HOLE==19){
            String titles[] = { "山田華子"};
    	    String tags[] = {"0/0"};
    	    String descs[] = {"Total:" + sumScore + "(" + sumPut + ") -P" + sumPn + "        "};
	        for (int i = 0; i < titles.length; i++) {
	            HashMap<String, String> item = new HashMap<String, String>();
	            item.put("title", titles[i]);
	            item.put("tag", tags[i]);
	            item.put("desc", descs[i]);
	            data.add(item);
	        }
        }else{
            //取得したスコア情報をslistに入れる
            String[] slist = new String[11];
            slist = getScore();
            String score = slist[3].toString();
            String put   = slist[4].toString();
            String titles[] = { "山田華子"};
	        String tags[] = { score + "/" + put};
	        String descs[] = {"Total:" + sumScore + "(" + sumPut + ") -P" + sumPn + "        "};
	        for (int i = 0; i < titles.length; i++) {
	            HashMap<String, String> item = new HashMap<String, String>();
	            item.put("title", titles[i]);
	            item.put("tag", tags[i]);
	            item.put("desc", descs[i]);
	            data.add(item);
	        }
        }
	    SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.list_item, new String[] { "title", "tag", "desc" }, new int[] { R.id.title, R.id.tag, R.id.desc });
	    ListView list = (ListView) findViewById(R.id.list);
	    list.setAdapter(adapter);
	}

	private int[] countScore(){
	    helper = new DatabaseHelper(this);
		//String[][] array = new String[18][3];
        int[] clist = new int[3];
	    int score = 0;
		int put = 0;
		int pn = 0;
	    SQLiteDatabase db = helper.getReadableDatabase();
        String[] cols = {"score", "put", "pn"};
        for(int i=1; i<19; i++){
        String[] params = {Integer.toString(SID), Integer.toString(i)};
	    Cursor cs = db.query("scores", cols, "sid=? AND hole=?", params, null, null, null, null);
	        if(cs.moveToFirst()){
	            score += Integer.parseInt(cs.getString(0));  //score
	            put   += Integer.parseInt(cs.getString(1));  //put
	            pn    += Integer.parseInt(cs.getString(2));  //pn
	        }
        }
        clist[0] = score;
        clist[1] = put;
        clist[2] = pn;
        return clist;
	}
}