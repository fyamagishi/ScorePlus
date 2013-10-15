package com.example.scoreplusandroid.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.widget.EditText;

import com.example.scoreplusandroid.FacebookActivity;
import com.example.scoreplusandroid.LoginActivity;
import com.example.scoreplusandroid.R;
import com.example.scoreplusandroid.RegistrationActivity;
import com.example.scoreplusandroid.UsernameActivity;

public class GateChecker extends Activity{

	//email用。適宜変更。
	private static final String EMAILPATTERN = "^[a-zA-Z0-9\\._\\-\\+]+@[a-zA-Z0-9_\\-]+\\.[a-zA-Z\\.]+[a-zA-Z]$";

    public static boolean validate(LoginActivity activity){
    	EditText email = (EditText)activity.findViewById(R.id.txtEmail);
		EditText password = (EditText)activity.findViewById(R.id.txtPassword);
		if(isEmail(email.getText().toString()) && isPassword(password.getText().toString())){
            return true;
		}else{
			return false;
		}
    }

    public static boolean validate(RegistrationActivity activity){
        EditText username = (EditText)activity.findViewById(R.id.txtUserName);
    	EditText email = (EditText)activity.findViewById(R.id.txtEmail);
		EditText password = (EditText)activity.findViewById(R.id.txtPassword);
		if(isUsername(username.getText().toString()) && isEmail(email.getText().toString()) && isPassword(password.getText().toString())){
            return true;
		}else{
			return false;
		}
    }

    public static boolean validate(UsernameActivity activity){
        EditText username = (EditText)activity.findViewById(R.id.txtUserName);
		if(isUsername(username.getText().toString())){
            return true;
		}else{
			return false;
		}
    }


    public static boolean validate(FacebookActivity activity){
        EditText username = (EditText)activity.findViewById(R.id.txtUserName);
		if(isUsername(username.getText().toString())){
            return true;
		}else{
			return false;
		}
    }

    /**
     * @param email string
     * @return true if the email format is valid
     */
    public static boolean isEmail(String input) {
        Pattern p = Pattern.compile(EMAILPATTERN);
        Matcher m = p.matcher(input);
        p = null;
        input = null;
        return m.matches();
    }

    /**
     * @param password string
     * @return true if the passowrd format is valid
     */
    public static boolean isPassword(String input) {
  	    if((input==null) || (input.length() < 6)|| (input.length() > 11)){
            return false;
  	    }else{
  	    	return true;
  	    }
    }

    /**
     * @param username string
     * @return true if the username format is valid
     */
    public static boolean isUsername(String input) {
  	    if((input==null) || (input.length() < 1)|| (input.length() > 21)){
            return false;
  	    }else{
  	    	return true;
  	    }
    }

    /**
     * 数値チェック
     * double に変換できない文字列が渡された場合は false を返します。
     * @param str チェック文字列
     * @return 引数の文字列が数値である場合 true を返す。
     */
    public static boolean check(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
