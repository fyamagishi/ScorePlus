package com.example.scoreplusandroid;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.scoreplusandroid.controller.MenuController;
import com.example.scoreplusandroid.utils.DatabaseHelper;

/*
 * スコア表示画面
 */
public class ScoredisplayActivity extends Activity {
    private DatabaseHelper helper = null;
    static int SID = 1;  //スコアカードIDですが、端末に複数スコアを保持しないなら使わなくていいかも
    static int HOLE = 1; //ホール番号を保持

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scoredisplay);

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

        //スコア・パット合計を取得
        int[] clist = new int[3];
        clist = countScore();
	    TextView txtSumsScore = (TextView)findViewById(R.id.txtScoresum);
	    txtSumsScore.setText(Integer.toString(clist[0]));
	    TextView txtSumsPut = (TextView)findViewById(R.id.txtPutsum);
	    txtSumsPut.setText(Integer.toString(clist[1]));

	    /*
	     * ホールごとの登録データを取得して代入。
	     * 見た目がダサいので上手いやり方があったら直してください。。。
	     */
	    String[] hole1 = new String[11];
	  	String[] hole2 = new String[11];
	  	String[] hole3 = new String[11];
	  	String[] hole4 = new String[11];
	  	String[] hole5 = new String[11];
	  	String[] hole6 = new String[11];
	  	String[] hole7 = new String[11];
	  	String[] hole8 = new String[11];
	  	String[] hole9 = new String[11];
	  	String[] hole10 = new String[11];
	  	String[] hole11 = new String[11];
	  	String[] hole12 = new String[11];
	  	String[] hole13 = new String[11];
	  	String[] hole14 = new String[11];
	  	String[] hole15 = new String[11];
	  	String[] hole16 = new String[11];
	  	String[] hole17 = new String[11];
	  	String[] hole18 = new String[11];

	  	hole1 = getScore(1);
	  	hole2 = getScore(2);
	  	hole3 = getScore(3);
	  	hole4 = getScore(4);
	  	hole5 = getScore(5);
	  	hole6 = getScore(6);
	  	hole7 = getScore(7);
	  	hole8 = getScore(8);
	  	hole9 = getScore(9);
	  	hole10 = getScore(10);
	  	hole11 = getScore(11);
	  	hole12 = getScore(12);
	  	hole13 = getScore(13);
	  	hole14 = getScore(14);
	  	hole15 = getScore(15);
	  	hole16 = getScore(16);
	  	hole17 = getScore(17);
	  	hole18 = getScore(18);

	  	//1ホール
	    TextView txtScorehole1 = (TextView)findViewById(R.id.txtScorehole1);
	    txtScorehole1.setText(hole1[3]);
	    TextView txtPuthole1 = (TextView)findViewById(R.id.txtPuthole1);
	    txtPuthole1.setText(hole1[4]);
	    TextView txtFwhole1 = (TextView)findViewById(R.id.txtFwhole1);
	    txtFwhole1.setText(hole1[7]);
	    TextView txtObhole1 = (TextView)findViewById(R.id.txtObhole1);
	    txtObhole1.setText(hole1[8]);
	    TextView txtPnhole1 = (TextView)findViewById(R.id.txtPnhole1);
	    txtPnhole1.setText(hole1[9]);
	    TextView txtBkhole1 = (TextView)findViewById(R.id.txtBkhole1);
	    txtBkhole1.setText(hole1[10]);

	  	//2ホール
	    TextView txtScorehole2 = (TextView)findViewById(R.id.txtScorehole2);
	    txtScorehole2.setText(hole2[3]);
	    TextView txtPuthole2 = (TextView)findViewById(R.id.txtPuthole2);
	    txtPuthole2.setText(hole2[4]);
	    TextView txtFwhole2 = (TextView)findViewById(R.id.txtFwhole2);
	    txtFwhole2.setText(hole2[7]);
	    TextView txtObhole2 = (TextView)findViewById(R.id.txtObhole2);
	    txtObhole2.setText(hole2[8]);
	    TextView txtPnhole2 = (TextView)findViewById(R.id.txtPnhole2);
	    txtPnhole2.setText(hole2[9]);
	    TextView txtBkhole2 = (TextView)findViewById(R.id.txtBkhole2);
	    txtBkhole2.setText(hole2[10]);

	  	//3ホール
	    TextView txtScorehole3 = (TextView)findViewById(R.id.txtScorehole3);
	    txtScorehole3.setText(hole3[3]);
	    TextView txtPuthole3 = (TextView)findViewById(R.id.txtPuthole3);
	    txtPuthole3.setText(hole3[4]);
	    TextView txtFwhole3 = (TextView)findViewById(R.id.txtFwhole3);
	    txtFwhole3.setText(hole3[7]);
	    TextView txtObhole3 = (TextView)findViewById(R.id.txtObhole3);
	    txtObhole3.setText(hole3[8]);
	    TextView txtPnhole3 = (TextView)findViewById(R.id.txtPnhole3);
	    txtPnhole3.setText(hole3[9]);
	    TextView txtBkhole3 = (TextView)findViewById(R.id.txtBkhole3);
	    txtBkhole3.setText(hole3[10]);

	  	//4ホール
	    TextView txtScorehole4 = (TextView)findViewById(R.id.txtScorehole4);
	    txtScorehole4.setText(hole4[3]);
	    TextView txtPuthole4 = (TextView)findViewById(R.id.txtPuthole4);
	    txtPuthole4.setText(hole4[4]);
	    TextView txtFwhole4 = (TextView)findViewById(R.id.txtFwhole4);
	    txtFwhole4.setText(hole4[7]);
	    TextView txtObhole4 = (TextView)findViewById(R.id.txtObhole4);
	    txtObhole4.setText(hole4[8]);
	    TextView txtPnhole4 = (TextView)findViewById(R.id.txtPnhole4);
	    txtPnhole4.setText(hole4[9]);
	    TextView txtBkhole4 = (TextView)findViewById(R.id.txtBkhole4);
	    txtBkhole4.setText(hole4[10]);

	  	//5ホール
	    TextView txtScorehole5 = (TextView)findViewById(R.id.txtScorehole5);
	    txtScorehole5.setText(hole5[3]);
	    TextView txtPuthole5 = (TextView)findViewById(R.id.txtPuthole5);
	    txtPuthole5.setText(hole5[4]);
	    TextView txtFwhole5 = (TextView)findViewById(R.id.txtFwhole5);
	    txtFwhole5.setText(hole5[7]);
	    TextView txtObhole5 = (TextView)findViewById(R.id.txtObhole5);
	    txtObhole5.setText(hole5[8]);
	    TextView txtPnhole5 = (TextView)findViewById(R.id.txtPnhole5);
	    txtPnhole5.setText(hole5[9]);
	    TextView txtBkhole5 = (TextView)findViewById(R.id.txtBkhole5);
	    txtBkhole5.setText(hole5[10]);

	  	//6ホール
	    TextView txtScorehole6 = (TextView)findViewById(R.id.txtScorehole6);
	    txtScorehole6.setText(hole6[3]);
	    TextView txtPuthole6 = (TextView)findViewById(R.id.txtPuthole6);
	    txtPuthole6.setText(hole6[4]);
	    TextView txtFwhole6 = (TextView)findViewById(R.id.txtFwhole6);
	    txtFwhole6.setText(hole6[7]);
	    TextView txtObhole6 = (TextView)findViewById(R.id.txtObhole6);
	    txtObhole6.setText(hole6[8]);
	    TextView txtPnhole6 = (TextView)findViewById(R.id.txtPnhole6);
	    txtPnhole6.setText(hole6[9]);
	    TextView txtBkhole6 = (TextView)findViewById(R.id.txtBkhole6);
	    txtBkhole6.setText(hole6[10]);

	  	//7ホール
	    TextView txtScorehole7 = (TextView)findViewById(R.id.txtScorehole7);
	    txtScorehole7.setText(hole7[3]);
	    TextView txtPuthole7 = (TextView)findViewById(R.id.txtPuthole7);
	    txtPuthole7.setText(hole7[4]);
	    TextView txtFwhole7 = (TextView)findViewById(R.id.txtFwhole7);
	    txtFwhole7.setText(hole7[7]);
	    TextView txtObhole7 = (TextView)findViewById(R.id.txtObhole7);
	    txtObhole7.setText(hole7[8]);
	    TextView txtPnhole7 = (TextView)findViewById(R.id.txtPnhole7);
	    txtPnhole7.setText(hole7[9]);
	    TextView txtBkhole7 = (TextView)findViewById(R.id.txtBkhole7);
	    txtBkhole7.setText(hole7[10]);

	  	//8ホール
	    TextView txtScorehole8 = (TextView)findViewById(R.id.txtScorehole8);
	    txtScorehole8.setText(hole8[3]);
	    TextView txtPuthole8 = (TextView)findViewById(R.id.txtPuthole8);
	    txtPuthole8.setText(hole8[4]);
	    TextView txtFwhole8 = (TextView)findViewById(R.id.txtFwhole8);
	    txtFwhole8.setText(hole8[7]);
	    TextView txtObhole8 = (TextView)findViewById(R.id.txtObhole8);
	    txtObhole8.setText(hole8[8]);
	    TextView txtPnhole8 = (TextView)findViewById(R.id.txtPnhole8);
	    txtPnhole8.setText(hole8[9]);
	    TextView txtBkhole8 = (TextView)findViewById(R.id.txtBkhole8);
	    txtBkhole8.setText(hole8[10]);

	  	//9ホール
	    TextView txtScorehole9 = (TextView)findViewById(R.id.txtScorehole9);
	    txtScorehole9.setText(hole9[3]);
	    TextView txtPuthole9 = (TextView)findViewById(R.id.txtPuthole9);
	    txtPuthole9.setText(hole9[4]);
	    TextView txtFwhole9 = (TextView)findViewById(R.id.txtFwhole9);
	    txtFwhole9.setText(hole9[7]);
	    TextView txtObhole9 = (TextView)findViewById(R.id.txtObhole9);
	    txtObhole9.setText(hole9[8]);
	    TextView txtPnhole9 = (TextView)findViewById(R.id.txtPnhole9);
	    txtPnhole9.setText(hole9[9]);
	    TextView txtBkhole9 = (TextView)findViewById(R.id.txtBkhole9);
	    txtBkhole9.setText(hole9[10]);

	  	//10ホール
	    TextView txtScorehole10 = (TextView)findViewById(R.id.txtScorehole10);
	    txtScorehole10.setText(hole10[3]);
	    TextView txtPuthole10 = (TextView)findViewById(R.id.txtPuthole10);
	    txtPuthole10.setText(hole10[4]);
	    TextView txtFwhole10 = (TextView)findViewById(R.id.txtFwhole10);
	    txtFwhole10.setText(hole10[7]);
	    TextView txtObhole10 = (TextView)findViewById(R.id.txtObhole10);
	    txtObhole10.setText(hole10[8]);
	    TextView txtPnhole10 = (TextView)findViewById(R.id.txtPnhole10);
	    txtPnhole10.setText(hole10[9]);
	    TextView txtBkhole10 = (TextView)findViewById(R.id.txtBkhole10);
	    txtBkhole10.setText(hole10[10]);

	    //11ホール
	    TextView txtScorehole11 = (TextView)findViewById(R.id.txtScorehole11);
	    txtScorehole11.setText(hole11[3]);
	    TextView txtPuthole11 = (TextView)findViewById(R.id.txtPuthole11);
	    txtPuthole11.setText(hole11[4]);
	    TextView txtFwhole11 = (TextView)findViewById(R.id.txtFwhole11);
	    txtFwhole11.setText(hole11[7]);
	    TextView txtObhole11 = (TextView)findViewById(R.id.txtObhole11);
	    txtObhole11.setText(hole11[8]);
	    TextView txtPnhole11 = (TextView)findViewById(R.id.txtPnhole11);
	    txtPnhole11.setText(hole11[9]);
	    TextView txtBkhole11 = (TextView)findViewById(R.id.txtBkhole11);
	    txtBkhole11.setText(hole11[10]);

	  	//12ホール
	    TextView txtScorehole12 = (TextView)findViewById(R.id.txtScorehole12);
	    txtScorehole12.setText(hole12[3]);
	    TextView txtPuthole12 = (TextView)findViewById(R.id.txtPuthole12);
	    txtPuthole12.setText(hole12[4]);
	    TextView txtFwhole12 = (TextView)findViewById(R.id.txtFwhole12);
	    txtFwhole12.setText(hole12[7]);
	    TextView txtObhole12 = (TextView)findViewById(R.id.txtObhole12);
	    txtObhole12.setText(hole12[8]);
	    TextView txtPnhole12 = (TextView)findViewById(R.id.txtPnhole12);
	    txtPnhole12.setText(hole12[9]);
	    TextView txtBkhole12 = (TextView)findViewById(R.id.txtBkhole12);
	    txtBkhole12.setText(hole12[10]);

	  	//13ホール
	    TextView txtScorehole13 = (TextView)findViewById(R.id.txtScorehole13);
	    txtScorehole13.setText(hole13[3]);
	    TextView txtPuthole13 = (TextView)findViewById(R.id.txtPuthole13);
	    txtPuthole13.setText(hole13[4]);
	    TextView txtFwhole13 = (TextView)findViewById(R.id.txtFwhole13);
	    txtFwhole13.setText(hole13[7]);
	    TextView txtObhole13 = (TextView)findViewById(R.id.txtObhole13);
	    txtObhole13.setText(hole13[8]);
	    TextView txtPnhole13 = (TextView)findViewById(R.id.txtPnhole13);
	    txtPnhole13.setText(hole13[9]);
	    TextView txtBkhole13 = (TextView)findViewById(R.id.txtBkhole13);
	    txtBkhole13.setText(hole13[10]);

	  	//14ホール
	    TextView txtScorehole14 = (TextView)findViewById(R.id.txtScorehole14);
	    txtScorehole14.setText(hole14[3]);
	    TextView txtPuthole14 = (TextView)findViewById(R.id.txtPuthole14);
	    txtPuthole14.setText(hole14[4]);
	    TextView txtFwhole14 = (TextView)findViewById(R.id.txtFwhole14);
	    txtFwhole14.setText(hole14[7]);
	    TextView txtObhole14 = (TextView)findViewById(R.id.txtObhole14);
	    txtObhole14.setText(hole14[8]);
	    TextView txtPnhole14 = (TextView)findViewById(R.id.txtPnhole14);
	    txtPnhole14.setText(hole14[9]);
	    TextView txtBkhole14 = (TextView)findViewById(R.id.txtBkhole14);
	    txtBkhole14.setText(hole14[10]);

	  	//15ホール
	    TextView txtScorehole15 = (TextView)findViewById(R.id.txtScorehole15);
	    txtScorehole15.setText(hole15[3]);
	    TextView txtPuthole15 = (TextView)findViewById(R.id.txtPuthole15);
	    txtPuthole15.setText(hole15[4]);
	    TextView txtFwhole15 = (TextView)findViewById(R.id.txtFwhole15);
	    txtFwhole15.setText(hole15[7]);
	    TextView txtObhole15 = (TextView)findViewById(R.id.txtObhole15);
	    txtObhole15.setText(hole15[8]);
	    TextView txtPnhole15 = (TextView)findViewById(R.id.txtPnhole15);
	    txtPnhole15.setText(hole15[9]);
	    TextView txtBkhole15 = (TextView)findViewById(R.id.txtBkhole15);
	    txtBkhole15.setText(hole15[10]);

	  	//16ホール
	    TextView txtScorehole16 = (TextView)findViewById(R.id.txtScorehole16);
	    txtScorehole16.setText(hole16[3]);
	    TextView txtPuthole16 = (TextView)findViewById(R.id.txtPuthole16);
	    txtPuthole16.setText(hole16[4]);
	    TextView txtFwhole16 = (TextView)findViewById(R.id.txtFwhole16);
	    txtFwhole16.setText(hole16[7]);
	    TextView txtObhole16 = (TextView)findViewById(R.id.txtObhole16);
	    txtObhole16.setText(hole16[8]);
	    TextView txtPnhole16 = (TextView)findViewById(R.id.txtPnhole16);
	    txtPnhole16.setText(hole16[9]);
	    TextView txtBkhole16 = (TextView)findViewById(R.id.txtBkhole16);
	    txtBkhole16.setText(hole16[10]);

	  	//17ホール
	    TextView txtScorehole17 = (TextView)findViewById(R.id.txtScorehole17);
	    txtScorehole17.setText(hole17[3]);
	    TextView txtPuthole17 = (TextView)findViewById(R.id.txtPuthole17);
	    txtPuthole17.setText(hole17[4]);
	    TextView txtFwhole17 = (TextView)findViewById(R.id.txtFwhole17);
	    txtFwhole17.setText(hole17[7]);
	    TextView txtObhole17 = (TextView)findViewById(R.id.txtObhole17);
	    txtObhole17.setText(hole17[8]);
	    TextView txtPnhole17 = (TextView)findViewById(R.id.txtPnhole17);
	    txtPnhole17.setText(hole17[9]);
	    TextView txtBkhole17 = (TextView)findViewById(R.id.txtBkhole17);
	    txtBkhole17.setText(hole17[10]);

	  	//18ホール
	    TextView txtScorehole18 = (TextView)findViewById(R.id.txtScorehole18);
	    txtScorehole18.setText(hole18[3]);
	    TextView txtPuthole18 = (TextView)findViewById(R.id.txtPuthole18);
	    txtPuthole18.setText(hole18[4]);
	    TextView txtFwhole18 = (TextView)findViewById(R.id.txtFwhole18);
	    txtFwhole18.setText(hole18[7]);
	    TextView txtObhole18 = (TextView)findViewById(R.id.txtObhole18);
	    txtObhole18.setText(hole18[8]);
	    TextView txtPnhole18 = (TextView)findViewById(R.id.txtPnhole18);
	    txtPnhole18.setText(hole18[9]);
	    TextView txtBkhole18 = (TextView)findViewById(R.id.txtBkhole18);
	    txtBkhole18.setText(hole18[10]);
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

    //スコア情報を取得
    private String[] getScore(int hole_num){
  	  helper = new DatabaseHelper(this);
  	  String[] array = new String[11];
  	  SQLiteDatabase db = helper.getReadableDatabase();
  	  String[] cols = {"id", "sid", "hole", "score", "put", "left", "right", "fw", "ob", "pn", "bk"};
        String[] params = {Integer.toString(SID), Integer.toString(hole_num)};
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