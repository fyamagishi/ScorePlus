package to.msn.wings.preferencebasic;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
    String msg = "";
    msg += "ユーザー名：" + pref.getString("edittext_name", "ゲスト");
    msg += "\nパスワード：" + pref.getString("edittext_pw", "123abc");
    msg += "\n年齢：" + pref.getString("edittext_age", "20");
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
  }

  public void onClick(View view) {
    Intent i = new Intent(this, MyConfig.class);
    startActivity(i);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.activity_main, menu);
    return true;
  }
}
