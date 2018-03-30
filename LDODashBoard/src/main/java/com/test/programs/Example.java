package com.test.programs;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
 
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
 
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;


public class Example {
    public static void main(String[] args) throws Exception {
    	
    	   KeyStore clientStore = KeyStore.getInstance("PKCS12");
           clientStore.load(new FileInputStream("M:\\Learning\\TIPD8_certificates\\Apps\\Tip80\\cs\\pki\\etc\\spid_S107077_T.p12"), "eGdCY@#932".toCharArray());

           KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
           kmf.init(clientStore, "eGdCY@#932".toCharArray());
           KeyManager[] kms = kmf.getKeyManagers();

           KeyStore trustStore = KeyStore.getInstance("jks");
           trustStore.load(new FileInputStream("m:\\gcothak2keystore.jks"), "password".toCharArray());

           TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
           tmf.init(trustStore);
           TrustManager[] tms = tmf.getTrustManagers();

           SSLContext sslContext = null;
           sslContext = SSLContext.getInstance("TLS");
           sslContext.init(kms, tms, new SecureRandom());

           HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
           URL url = new URL("https://cdg-prd.csintra.net/cdg/login.html");

           HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
           
           urlConn.connect();
           urlConn.getInputStream();
           urlConn.disconnect();
    }
}