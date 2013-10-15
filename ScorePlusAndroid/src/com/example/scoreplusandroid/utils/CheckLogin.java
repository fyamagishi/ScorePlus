package com.example.scoreplusandroid.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.app.Activity;
import android.content.Context;

public class CheckLogin extends Activity{
    //取得したクッキー情報をキャッシュ
	public void writeCookie(String str){
		try{
    		System.out.println("チェックログイン1-0");
    		System.out.println(Context.MODE_PRIVATE);
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("login.dat", Context.MODE_PRIVATE)));
			System.out.println("チェックログイン1-1");
        	writer.write(str);
            writer.close();
        }catch(FileNotFoundException e){
        	e.printStackTrace();
        }catch(IOException e){
        	e.printStackTrace();
        }
	}

    //クッキー情報をキャッシュから読込み
	public String readCookie(){
	      StringBuffer str = new StringBuffer();
	      try{
	          BufferedReader reader = new BufferedReader(new InputStreamReader(openFileInput("login.dat")));
	      	  while(reader.ready()){
	      		  str.append(reader.readLine());
	      	  }
	      	  reader.close();
	      }catch(FileNotFoundException e){
	      	  e.printStackTrace();
	      }catch(IOException e){
	      	  e.printStackTrace();
	      }
	      return str.toString();
	}

    //キャッシュのクッキー情報を削除
	public void deleteCookie(){
		System.out.println("チェックログイン");
    	try{
        	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(openFileOutput("login.dat", Context.MODE_PRIVATE)));
            writer.write("");
            writer.close();
        }catch(FileNotFoundException e){
        	e.printStackTrace();
        }catch(IOException e){
        	e.printStackTrace();
        }
	}
}
