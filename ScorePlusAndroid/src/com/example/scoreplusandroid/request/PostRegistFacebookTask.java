package com.example.scoreplusandroid.request;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.os.AsyncTask;

import com.example.scoreplusandroid.utils.LowSecuritySSLSocketFactory;

public class PostRegistFacebookTask extends AsyncTask<String, Integer, Integer> {
	private static final String REQUEST_URI = "https://develop.golfans.me/api/register";
    @Override
    protected Integer doInBackground(String... contents) {

		KeyStore trustStore = null;
		try {
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
		} catch (KeyStoreException e1) {
			e1.printStackTrace();
		}
		try {
			trustStore.load(null, null);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (CertificateException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		SSLSocketFactory sf = null;
		try {
			sf = new LowSecuritySSLSocketFactory(trustStore);
		} catch (KeyManagementException e1) {
			e1.printStackTrace();
		} catch (UnrecoverableKeyException e1) {
			e1.printStackTrace();
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (KeyStoreException e1) {
			e1.printStackTrace();
		}

		sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		HttpParams hparams = new BasicHttpParams();
		HttpProtocolParams.setVersion(hparams, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(hparams, HTTP.UTF_8);
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		registry.register(new Scheme("https", sf, 443));
		ClientConnectionManager ccm = new ThreadSafeClientConnManager(hparams, registry);
		DefaultHttpClient httpClient = new DefaultHttpClient(ccm, hparams);
		HttpPost post = new HttpPost(REQUEST_URI);

		System.out.println("フェイスブック登録");
		System.out.println(contents[0]); //Facebook_id
		System.out.println(contents[1]); //ユーザネーム
		System.out.println(contents[2]); //OAuthトークン

		ArrayList <NameValuePair> params = new ArrayList <NameValuePair>();
        params.add(new BasicNameValuePair("auth_type", "2"));
        params.add(new BasicNameValuePair("service_user_id", contents[0]));
        params.add(new BasicNameValuePair("username", contents[1]));
        params.add(new BasicNameValuePair("oauth_token", contents[2]));

        HttpResponse res = null;
        try {
        	post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
            res = httpClient.execute(post);
            System.out.println(res.getStatusLine().getStatusCode());
        } catch (IOException e) {
        	e.printStackTrace();
        }
        try{
        	return Integer.valueOf(res.getStatusLine().getStatusCode());
        }catch(Exception e){
        	e.printStackTrace();
    		return null;
        }
    }
}
