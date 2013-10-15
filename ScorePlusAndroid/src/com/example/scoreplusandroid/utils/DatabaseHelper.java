package com.example.scoreplusandroid.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
  static final private String DBNAME = "scoreplus.sqlite";
  static final private int VERSION = 1;

  public DatabaseHelper(Context context) {
    super(context, DBNAME, null, VERSION);
  }

  @Override
  public void onOpen(SQLiteDatabase db) {
    super.onOpen(db);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
	  //スコアカード情報初期化
	  System.out.println("テーブル1");
	  db.execSQL("CREATE TABLE courses (" +
		      "id INTEGER PRIMARY KEY, " +
		      "sid INTEGER, " +
		      "gid INTEGER, " +
		      "gname TEXT, " +
		      "first_cid INTEGER, " +
		      "last_cid INTEGER, " +
		      "first_cname TEXT, " +
		      "last_cname TEXT, " +
		      "tid INTEGER, " +
		      "tname TEXT, " +
		      "date DATE, " +
		      "weather INTEGER " +
		      ")"
			  );

	  System.out.println("テーブル2");

	  String gname = "嶋崎カントリー倶楽部";
	  String first_cname = "IN";
	  String last_cname = "OUT";
	  String tname = "レギュラー";
	  String date = "2013-10-10";

      db.execSQL("INSERT INTO courses(id, sid, gid, gname, first_cid, last_cid, first_cname, last_cname, tid, tname, date, weather)" +
      " VALUES(1, 1, 1, 1, 1, 2, 2, 3, 1 , 2, 0, 1)");

	  System.out.println("テーブル3");

      //ホール情報初期化
      db.execSQL("CREATE TABLE scores (" +
      "id INTEGER PRIMARY KEY, " +
      "sid INTEGER, "   +
      "hole INTEGER, "  +
      "score INTEGER, " +
      "put INTEGER, "   +
      "left INTEGER, "  +
      "right INTEGER, " +
      "fw INTEGER, " +
      "ob INTEGER, " +
      "pn INTEGER, " +
      "bk INTEGER "  +
      ")"
      );
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(1, 1, 1, 5, 2, 0, 1, 0, 0, 0, 0)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(2, 1, 2, 6, 3, 0, 1, 0, 1, 1, 0)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(3, 1, 3, 4, 2, 1, 1, 0, 0, 1, 1)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(4, 1, 4, 3, 1, 0, 1, 1, 0, 0, 0)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(5, 1, 5, 4, 1, 1, 1, 1, 1, 1, 1)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(6, 1, 6, 5, 1, 0, 1, 0, 0, 0, 0)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(7, 1, 7, 4, 2, 0, 0, 1, 1, 1, 1)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(8, 1, 8, 3, 1, 1, 1, 0, 1, 0, 0)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(9, 1, 9, 4, 2, 1, 0, 1, 1, 1, 1)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(10, 1, 10, 5, 3, 1, 1, 1, 0, 0, 1)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(11, 1, 11, 6, 2, 1, 0, 1, 0, 1, 0)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(12, 1, 12, 4, 3, 1, 1, 0, 0, 0, 1)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(13, 1, 13, 5, 2, 0, 0, 1, 1, 1, 1)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(14, 1, 14, 4, 1, 1, 1, 1, 1, 1, 1)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(15, 1, 15, 4, 2, 1, 1, 1, 1, 0, 0)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(16, 1, 16, 5, 2, 0, 0, 0, 0, 1, 1)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(17, 1, 17, 6, 1, 1, 1, 0, 1, 0, 0)");
      db.execSQL("INSERT INTO scores(id, sid, hole, score, put, left, right, fw, ob, pn, bk)" +
              " VALUES(18, 1, 18, 3, 2, 1, 0, 0, 0, 0, 0)");
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int old_v, int new_v) {
    db.execSQL("DROP TABLE IF EXISTS books");
    onCreate(db);
  }
}
