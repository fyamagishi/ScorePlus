package com.example.scoreplusandroid.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.scoreplusandroid.AccountActivity;
import com.example.scoreplusandroid.FacebookActivity;
import com.example.scoreplusandroid.FacebookaccountActivity;
import com.example.scoreplusandroid.HistoryActivity;
import com.example.scoreplusandroid.LoginActivity;
import com.example.scoreplusandroid.MainActivity;
import com.example.scoreplusandroid.PlayActivity;
import com.example.scoreplusandroid.ProfileActivity;
import com.example.scoreplusandroid.R;
import com.example.scoreplusandroid.RegistrationActivity;
import com.example.scoreplusandroid.ScoreActivity;
import com.example.scoreplusandroid.ScorecardActivity;
import com.example.scoreplusandroid.ScoremenuActivity;
import com.example.scoreplusandroid.SearchgolfcourseActivity;
import com.example.scoreplusandroid.SettingActivity;
import com.example.scoreplusandroid.UsernameActivity;

public class ActivityController extends Activity{
	//トップ画面
    @SuppressWarnings("serial")
    public void submit(MainActivity activity, final View v){
		if(v.getId()==R.id.btnStart){
			Intent i = new Intent(activity, com.example.scoreplusandroid.StartActivity.class);
			activity.startActivity(i);
		}
		if(v.getId()==R.id.btnRegistration){
			Intent i = new Intent(activity, com.example.scoreplusandroid.RegistrationActivity.class);
			activity.startActivity(i);
		}
		if(v.getId()==R.id.btnLogin){
			Intent i = new Intent(activity, com.example.scoreplusandroid.LoginActivity.class);
			activity.startActivity(i);
		}
    }

	//登録画面
    @SuppressWarnings("serial")
    public void submit(RegistrationActivity activity, final View v){
        if(v.getId()==R.id.btnRegistration){
            Intent i = new Intent(activity, com.example.scoreplusandroid.SettingActivity.class);
            activity.startActivity(i);
	    }
		if(v.getId()==R.id.btnFacebook){
            Intent i = new Intent(activity, com.example.scoreplusandroid.FacebookActivity.class);
            activity.startActivity(i);
		}
    }

	//ログイン画面
    @SuppressWarnings("serial")
    public void submit(LoginActivity activity, final View v){
		if(v.getId()==R.id.btnLogin){
			Intent i = new Intent(activity, com.example.scoreplusandroid.SettingActivity.class);
			activity.startActivity(i);
		}
    }

	//スコアメニュー画面
    @SuppressWarnings("serial")
    public void submit(ScoremenuActivity activity, final View v){
        if(v.getId()==R.id.btnPlay){
            Intent i = new Intent(activity, com.example.scoreplusandroid.PlayActivity.class);
            activity.startActivity(i);
        }
        if(v.getId()==R.id.btnScore){
            Intent i = new Intent(activity, com.example.scoreplusandroid.ScoreActivity.class);
            activity.startActivity(i);
        }
        if(v.getId()==R.id.btnScoreDisplay){
            Intent i = new Intent(activity, com.example.scoreplusandroid.ScoredisplayActivity.class);
            activity.startActivity(i);
        }
    }

    //Facebook登録画面（Facebook会員登録時）
    @SuppressWarnings("serial")
    public void submit(FacebookActivity activity, final View v){
        Intent i = new Intent(activity, com.example.scoreplusandroid.SettingActivity.class);
        activity.startActivity(i);
    }

    //Facebook外部連携画面（Facebook会員登録時）
    @SuppressWarnings("serial")
    public void submit(FacebookaccountActivity activity, final View v){
        Intent i = new Intent(activity, com.example.scoreplusandroid.AccountActivity.class);
        activity.startActivity(i);
    }

    //ユーザネーム登録画面（Facebook会員登録時）
    @SuppressWarnings("serial")
    public void submit(UsernameActivity activity, final View v){
        Intent i = new Intent(activity, com.example.scoreplusandroid.SettingActivity.class);
        activity.startActivity(i);
    }

    //ゴルフ場検索画面
    @SuppressWarnings("serial")
    public void submit(SearchgolfcourseActivity activity, final View v){
        Intent i = new Intent(activity, com.example.scoreplusandroid.PlayActivity.class);
        activity.startActivity(i);
    }

	//スコア入力画面
    @SuppressWarnings("serial")
    public void submit(ScorecardActivity activity, final View v){
    	/*同伴者機能用
    	if(v.getId()==R.id.btnPlayer1){
            Intent i = new Intent(activity, com.example.scoreplusandroid.ScorecardActivity.class);
            activity.startActivity(i);
    	}
		if(v.getId()==R.id.btnPlayer2){
	        Intent i = new Intent(activity, com.example.scoreplusandroid.ScorecardActivity.class);
	        activity.startActivity(i);
		}
		if(v.getId()==R.id.btnPlayer3){
	        Intent i = new Intent(activity, com.example.scoreplusandroid.ScorecardActivity.class);
	        activity.startActivity(i);
	    }
		if(v.getId()==R.id.btnPlayer4){
	        Intent i = new Intent(activity, com.example.scoreplusandroid.ScorecardActivity.class);
	        activity.startActivity(i);
	    }
	    */
    }

    //スコア入力項目選択画面（新規用）
    @SuppressWarnings("serial")
    public void submit(PlayActivity activity, final View v){
        Intent i = new Intent(activity, com.example.scoreplusandroid.ScoreActivity.class);
        activity.startActivity(i);
    }

    @SuppressWarnings("serial")
    public void submit(ScoreActivity activity, final View v){
/*
    	if(v.getId()==R.id.btnLeft){
    		int hole = ScoreActivity.HOLE;
    		ScoreActivity.HOLE = hole--;
			Intent i = new Intent(scoreActivity, com.example.scoreplusandroid.ScoreActivity.class);
			scoreActivity.startActivity(i);
		}
		if(v.getId()==R.id.btnRight){
    		int hole = ScoreActivity.HOLE;
    		ScoreActivity.HOLE = hole++;
			Intent i = new Intent(scoreActivity, com.example.scoreplusandroid.ScoreActivity.class);
			scoreActivity.startActivity(i);
		}
*/
    }

    //セッティング画面
    @SuppressWarnings("serial")
    public void submit(SettingActivity activity, final View v, String str){
  	  if(str.equals("プロフィール")){
		  Intent intent = new Intent(activity, ProfileActivity.class);
		  activity.startActivity(intent);
	  }else if(str.equals("外部アカウント連携")){
		  Intent intent = new Intent(activity, AccountActivity.class);
		  activity.startActivity(intent);
	  }else{//ログアウト
		  Intent intent = new Intent(activity, MainActivity.class);
		  activity.startActivity(intent);
	  }
    }

    //ゴルフ場検索画面
    @SuppressWarnings("serial")
    public void submit(SearchgolfcourseActivity activity, final View v, ArrayAdapter<String> adapter, int pos){
	    Intent intent = new Intent(activity, PlayActivity.class);
        if (intent != null) {
            intent.putExtra("color", adapter.getItem(pos));
            activity.startActivity(intent);
        }
    }

    //検索履歴画面
    @SuppressWarnings("serial")
    public void submit(HistoryActivity activity, final View v, ArrayAdapter<String> adapter, int pos){
	    Intent intent = new Intent(activity, PlayActivity.class);
        if (intent != null) {
            intent.putExtra("color", adapter.getItem(pos));
            activity.startActivity(intent);
        }
    }

}