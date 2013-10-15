package com.example.scoreplusandroid.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import com.example.scoreplusandroid.R;

public class MenuController extends Activity{
    @SuppressWarnings("serial")
    public boolean submit(Context context, final MenuItem item ){
        switch(item.getItemId()){
        	case R.id.menu_start:
        		Intent i_start = new Intent(context, com.example.scoreplusandroid.StartActivity.class);
        		context.startActivity(i_start);
        		return true;
            case R.id.menu_settings:
        		Intent i_setting = new Intent(context, com.example.scoreplusandroid.SettingActivity.class);
        		context.startActivity(i_setting);
        		return true;
            case R.id.menu_top:
        		Intent i_top = new Intent(context, com.example.scoreplusandroid.MainActivity.class);
        		context.startActivity(i_top);
        		return true;
            case R.id.menu_registration:
        		Intent i_registration = new Intent(context, com.example.scoreplusandroid.RegistrationActivity.class);
        		context.startActivity(i_registration);
        		return true;
            case R.id.menu_login:
        		Intent i_login = new Intent(context, com.example.scoreplusandroid.LoginActivity.class);
        		context.startActivity(i_login);
        		return true;
            case R.id.menu_account:
        		Intent i_account = new Intent(context, com.example.scoreplusandroid.AccountActivity.class);
                context.startActivity(i_account);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}