package com.example.scoreplusandroid.utils;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.SSLSocketFactory;

/**
 * 自己証明書エラースルー版SSLSocketFactory
 */
public class LowSecuritySSLSocketFactory extends SSLSocketFactory {
    private SSLContext mSslContext = SSLContext.getInstance("TLS");

    public LowSecuritySSLSocketFactory(KeyStore truststore)
            throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(truststore);

        TrustManager trustManager = new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                    String authType) throws CertificateException {
            }
        };
        mSslContext.init(null, new TrustManager[] { trustManager }, null);
    }

    @Override
    public Socket createSocket(Socket socket, String host, int port,
            boolean autoClose) throws IOException, UnknownHostException {
        return mSslContext.getSocketFactory().createSocket(socket, host, port,
                autoClose);
    }

    @Override
    public Socket createSocket() throws IOException {
        return mSslContext.getSocketFactory().createSocket();
    }
}
