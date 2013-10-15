package com.example.scoreplusandroid;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scoreplusandroid.controller.ActivityController;
import com.example.scoreplusandroid.controller.MenuController;
import com.example.scoreplusandroid.request.PostRegistFacebookTask;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookOperationCanceledException;
import com.facebook.FacebookRequestError;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.Session.OpenRequest;
import com.facebook.SessionState;
import com.facebook.model.GraphObject;
import com.facebook.model.GraphUser;

/*
 * Facebook連携画面
 */
public class FacebookaccountActivity extends Activity {
	//private static final String TAG = "MainActivity";
    private static final String TAG = "AccountActivity";
    private static final List<String> PERMISSIONS = Arrays.asList("publish_actions");
    private EditText mEditText;
    private class SessionStatusCallback implements Session.StatusCallback {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            //updateView();
            Log.d(TAG, "SessionStatusCallback");
            onSessionStateChange(session, state, exception);
        }
    }
    private Session.StatusCallback statusCallback = new SessionStatusCallback();

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        // Facebook ログイン管理
        Session session = Session.getActiveSession();
        if (session == null) {
            if (savedInstanceState != null) {
                session = Session.restoreSession(this, null, statusCallback, savedInstanceState);
            }
            if (session == null) {
                session = new Session(this);
            }
            Session.setActiveSession(session);

            if (session.getState().equals(SessionState.CREATED_TOKEN_LOADED)) {
                //session.openForPublish(getOpenRequest());
                session.openForRead(new OpenRequest(this));
            }
        }

        // ログイン状態の確認
        if (! session.isOpened()) {
            doLogin();
        }
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
	    //preparePublish();
    	new ActivityController().submit(this, v);
    }

    @Override
    public void onStart() {
        super.onStart();
        Session.getActiveSession().addCallback(statusCallback);
        Log.d(TAG,"onStart");
    }

    @Override
    public void onStop() {
        super.onStop();
        Session.getActiveSession().removeCallback(statusCallback);
        Log.d(TAG,"onStop");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Session session = Session.getActiveSession();
        Log.d(TAG,"onResume:" + "session state is " + session.getState());

        if (session.getState().equals(SessionState.CLOSED_LOGIN_FAILED) || session.getState().equals(SessionState.CLOSED)) {
            Toast.makeText(this, "Facebook認証に失敗しました。", Toast.LENGTH_LONG).show();
            finish();
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,"onPause");
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG,"onActivityResult");
        Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG,"onSaveInstanceState");
        Session session = Session.getActiveSession();
        Session.saveSession(session, outState);
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if ((exception instanceof FacebookOperationCanceledException ||
                exception instanceof FacebookAuthorizationException)) {
            Log.w(TAG, "error occured:" + exception.getMessage());
        } else if (state == SessionState.OPENED_TOKEN_UPDATED) {
        	doPost();
        }
    }

    class FacebookGraphUserCallback implements Request.GraphUserCallback {
    	  private ProgressDialog mProgress = null;
    	  public FacebookGraphUserCallback(String message) {
    	    //mProgress = new ProgressDialog(FacebookActivity.this);
    		mProgress = new ProgressDialog(FacebookaccountActivity.this);
    		mProgress.setMessage(message);
    	    mProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    	    mProgress.show();
    	  }
    	  @Override
    	  public void onCompleted(GraphUser user, Response response) {
    	    mProgress.dismiss();
    	  }
    	}

    private void doLogin() {
        Session session = Session.getActiveSession();
        Log.d(TAG,"doLogin: session state is " + session.getState() + ", isOpend:" + session.isOpened() + ", isClosed:" + session.isClosed());
        if (!session.isOpened()) {
            if (session.isClosed()) {
                session = new Session(this);
                Session.setActiveSession(session);
            }
            //session.openForPublish(getOpenRequest());
            session.openForRead(new OpenRequest(this));
        } else {
            Session.openActiveSession(this, true, statusCallback);
        }
    }

    private boolean hasPublishPermission() {
        Session session = Session.getActiveSession();
        return session != null && session.getPermissions().contains("publish_actions");
    }

    private void preparePublish() {
        Session session = Session.getActiveSession();
    	// GraphAPIのmeリクエストを呼び出す
        Request.executeMeRequestAsync(session, new FacebookGraphUserCallback("wait...") {
            @Override
            public void onCompleted(GraphUser user, Response response) {
            	super.onCompleted(user, response);
    	        EditText username = (EditText)findViewById(R.id.txtUserName);
     	    	Session session   = Session.getActiveSession();
                String[] array = new String[3];
    	    	array[0] = user.getId().toString();             //ServiceUserId
    	    	array[1] = username.getText().toString();       //ユーザネーム
    	    	array[2] = session.getAccessToken().toString(); //AccessToken
    	    	PostRegistFacebookTask post = new PostRegistFacebookTask();
                post.execute(array);
             }
        });

        if (session != null) {
            if (hasPublishPermission()) {
                // We can do the action right away.
                doPost();
            } else {
                // We need to get new permissions, then complete the action when we get called back.
                session.requestNewPublishPermissions(new Session.NewPermissionsRequest(this, PERMISSIONS));
            }
        }
    }

    private void doPost() {
        Log.d(TAG,"doPost");
        final String message = "投稿テストです:" + (new Date().toString()) + "\n" + mEditText.getText().toString();
        Request request = Request
                .newStatusUpdateRequest(Session.getActiveSession(), message, new Request.Callback() {
                    @Override
                    public void onCompleted(Response response) {
                        showPublishResult(message, response.getGraphObject(), response.getError());
                    }
                });

        request.executeAsync();
    }

    private interface GraphObjectWithId extends GraphObject {
        String getId();
    }
    private void showPublishResult(String message, GraphObject result, FacebookRequestError error) {
        Log.d(TAG,"showPublishResult");
        String title = null;
        String alertMessage = null;
        if (error == null) {
            title = "ステータスアップデート成功";
            String id = result.cast(GraphObjectWithId.class).getId();
            alertMessage = "ID=" + id;

            //ログアウト（仮）
      	    Session session = Session.getActiveSession();
      	    if (!session.isClosed()) {
      	      // セッションとトークン情報を廃棄する。
      	      session.closeAndClearTokenInformation();
      	    }

        } else {
            title = "ステータスアップデート失敗";
            alertMessage = error.getErrorMessage();
        }

        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(alertMessage)
                .setPositiveButton("OK", null)
                .show();
    }
}