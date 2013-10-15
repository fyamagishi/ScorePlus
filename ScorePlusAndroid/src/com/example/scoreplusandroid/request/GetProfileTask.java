package com.example.scoreplusandroid.request;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.example.scoreplusandroid.LoginActivity;
import com.example.scoreplusandroid.ProfileActivity;
import com.example.scoreplusandroid.utils.LowSecuritySSLSocketFactory;

public class GetProfileTask extends AsyncTask<String, Integer, Integer> {
	private static final String REQUEST_URI = "https://develop.golfans.me/api/profile";
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

		HttpGet get = new HttpGet(REQUEST_URI);
		DefaultHttpClient client = new DefaultHttpClient(ccm, hparams);
		get.setHeader("Connection", "Keep-Alive");
		HttpResponse res = null;

        try{
        	client.setCookieStore(LoginActivity.CS);
        	res = client.execute(get);
        	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            res.getEntity().writeTo(outputStream);

            String data = outputStream.toString();
            JSONObject rootObject = new JSONObject(data);
            JSONObject responseObject = rootObject.getJSONObject("response");

            String email = responseObject.get("email").toString();
            String username = responseObject.get("username").toString();

            ProfileActivity.EMAIL = email;
            ProfileActivity.USERNAME = username;

        	res.getStatusLine().getStatusCode();
        	return Integer.valueOf(res.getStatusLine().getStatusCode());
        }catch(IOException e){
        	e.printStackTrace();
    		return null;
        }catch(JSONException e){
			e.printStackTrace();
			return null;
        }
    }
}