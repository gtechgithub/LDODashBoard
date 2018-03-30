package com.implementation;

import com.common.CommonConstants;
import com.connection.impl.DBCheckoutImpl;
import com.implementation.LnkConnectionCheck;
import com.implementation.LnkConnectionResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.TrustManager;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.SecureRandom;


import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class LnkConnectionEngine {
	
	static Logger log = Logger.getLogger(LnkConnectionEngine.class.getName());
	
	public List <LnkConnectionResponse> processforLnkConnectionGroup(final List linkConnectionArray, final String linkGroup ) {

		log.info("Inside the processforLnkConnectionGroup");
		List <LnkConnectionResponse>  LnkConnectionResponseList = new ArrayList <LnkConnectionResponse> ();

		try{
			

			//Extract the requested group from the overall list
			List  checkURLconnnectivityWrapper = processExtractRequiredGroupFromList(linkConnectionArray,linkGroup);
			
			LnkConnectionResponseList = checkURLconnnectivityWrapper(checkURLconnnectivityWrapper);
			return LnkConnectionResponseList;
			
		}
		catch(Exception e){
	    	log.error("Exception Received here!!!");
	        e.printStackTrace();
		}
		return LnkConnectionResponseList;
		
	}
	
	public List processExtractRequiredGroupFromList(final List linkConnectionArray, final String linkGroup)
	{
		List linkConnectionArrayTemp = new  ArrayList();

		log.info("looping to extract for the URL connection Group:" + linkGroup);
		//loop until it parses all the element in the list
		for (int size=0; size < linkConnectionArray.size(); size++ )
		{
			//extract the single element from the list namely LnkConnectionCheck object
			LnkConnectionCheck linkConnector = (LnkConnectionCheck) linkConnectionArray.get(size);

			//if the linkgroup matches add in the temp list to be returned
			if (linkConnector.getUrlIDGroup().equals(linkGroup))
			{
				log.info("match found for the URL connection Group:" + linkGroup + " and its link id:" + linkConnector.getUrlIDDesc());
				linkConnectionArrayTemp.add(linkConnector);
			}
		}
	
		return linkConnectionArrayTemp;
	}
	
	public List <LnkConnectionResponse> checkURLconnnectivityWrapper(final List  linkConnArrayRequired) {


			
				List <LnkConnectionResponse>  LnkConnectionResponseList = new ArrayList <LnkConnectionResponse> ();
				LnkConnectionResponse Connresponse = new LnkConnectionResponse();
				
				//loop until it parses all the element in the list
				for (int size=0; size < linkConnArrayRequired.size(); size++ )
				{
					//extract the single element from the list namely LnkConnectionCheck object
					LnkConnectionCheck linkConnector = (LnkConnectionCheck) linkConnArrayRequired.get(size);
		

					try {
						
						//check the urlconnecitivity and get the response code
						Connresponse = checkURLconnnectivity(linkConnector.getUrlLink(),linkConnector.getUrlDescription());
					}
					catch(Exception e){
				    	log.error("Exception Received here!!!");
				        e.printStackTrace();
				        //throw e;
					}

					LnkConnectionResponseList.add(Connresponse);
				}
				return LnkConnectionResponseList;

	}

	public LnkConnectionResponse formLnkConnectionResponse(final String urlLink,
			                                               final String urlDescription, 
			                                               final int urlResponseCode,
			                                               final String urlResponseDescription,
			                                               final String urlResponseStatus)
	{
		LnkConnectionResponse connResponse  = new LnkConnectionResponse();
		
		log.info("URL Link:"+ urlLink);
		log.info("URL description:" +urlDescription );

		log.info("URL response code:" +urlResponseCode );
		log.info("URL response description :" + urlResponseDescription );
		
		log.info("URL response status :" + urlResponseStatus );
		
		connResponse.setUrlLink(urlLink);
		connResponse.setUrlDescription(urlDescription);
		connResponse.setUrlResponseCode(urlResponseCode);
		connResponse.setUrlResponseDescription(urlResponseDescription);
		connResponse.setUrlResponseStatus(urlResponseStatus);
		
		return connResponse;
	}
	
	public LnkConnectionResponse checkURLconnnectivity(String strUrl, String strUrlDescription)
    {
        URL url = null;
        HttpURLConnection urlConn = null;
        String urlResponseStatus = "";
        int urlResponseCode = 0;
        String urlResponseMessage = "";
        try
        {
            log.info((new StringBuilder()).append("testing the httpurlconnection for url:").append(strUrl).toString());
            url = new URL(strUrl);
            urlConn = (HttpURLConnection)url.openConnection();
            urlConn.connect();
            if(urlConn.getResponseCode() == 200)
            {
                log.info("url http connection is sucessfull");
                urlResponseStatus = "OK";
            } else
            {
                log.info((new StringBuilder()).append("url http connection failure, response code:").append(urlConn.getResponseCode()).toString());
                urlResponseStatus = "NOT OK";
            }
            urlResponseCode = urlConn.getResponseCode();
            urlResponseMessage = urlConn.getResponseMessage();
            LnkConnectionResponse Connresponse = formLnkConnectionResponse(strUrl, strUrlDescription, urlResponseCode, urlResponseMessage, urlResponseStatus);
            return Connresponse;
        }
        catch(Exception e)
        {
            log.error("Error creating HTTP connection");
            urlResponseMessage = e.getMessage();
            urlResponseStatus = "Check Manually";
            LnkConnectionResponse Connresponse = formLnkConnectionResponse(strUrl, strUrlDescription, -1, urlResponseMessage, urlResponseStatus);
            e.printStackTrace();
            return Connresponse;
        }
    }
	
	/*
	public LnkConnectionResponse checkURLconnnectivity(final String strUrl, final String strUrlDescription){

		URL url = null;
		HttpURLConnection urlConn = null;
		String urlResponseStatus = "";
		int urlResponseCode = 0;
		String urlResponseMessage = "";
		
	
	    try {
	    	log.info("testing the httpurlconnection for url:" + strUrl );
	    	
	        url = new URL(strUrl);
	        
	        //System.setProperty("javax.net.ssl.trustStore","pkcs12");
	        //System.setProperty("javax.net.ssl.trustStoreType","cer");
	        //System.setProperty("javax.net.ssl.truststoreFile","M:\\certificate_base64.cer");
	        //System.setProperty("javax.net.ssl.trustStore","javax.net.ssl.trustStore");
	        //System.setProperty("javax.net.ssl.trustStorePassword","");
	        System.setProperty("javax.net.debug","ssl,handshake"); // very verbose debug

	        //System.setProperty("javax.net.ssl.keystoreFile","certificate_base64.cer");
	        //System.setProperty("javax.net.ssl.keyStorePassword","");
	        //System.setProperty("javax.net.ssl.TrustManagerFactory.algorithm" ,"CS_PKI");
	        //System.setProperty("javax.net.ssl.KeyManagerFactory.algorithm","CS_PKI");


	        
	        log.info("before the sslsoketfactory" + strUrl );
	        
	        //SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
	        SSLSocketFactory sslsocketfactory = (SSLSocketFactory) createSslSocketFactory();
	        
	        log.info("before the openConnection" + strUrl );
	        HttpsURLConnection conn = (HttpsURLConnection)url.openConnection();
	        log.info("before the setSSLSocketFactory" + strUrl );
	        conn.setSSLSocketFactory(sslsocketfactory);
	        
	        InputStream inputstream = conn.getInputStream();
	        InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
	        BufferedReader bufferedreader = new BufferedReader(inputstreamreader);

	        String string = null;
	        while ((string = bufferedreader.readLine()) != null) {
	            System.out.println("Received " + string);
	        }
	        
	        	        
 
	      	urlConn = (HttpURLConnection) url.openConnection();
	      	urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)");
	      	urlConn.setRequestMethod("HEAD"); 
	      	
	        urlConn.connect();

	        if (urlConn.getResponseCode() == HttpURLConnection.HTTP_OK )
	        {
	        	log.info("url http connection is sucessfull");
       	
	        	//append the response status
	        	urlResponseStatus = "OK";
	        }
	        else
	        {
	        	log.info("url http connection failure, response code:" +  urlConn.getResponseCode());

	        	//append the response status
	        	urlResponseStatus = "NOT OK";
	        }
	        
	        urlResponseCode = urlConn.getResponseCode();
	        urlResponseMessage =  urlConn.getResponseMessage();
			//now form the output containing all the details to be display in the view.
			LnkConnectionResponse Connresponse = formLnkConnectionResponse(strUrl,
					                                                       strUrlDescription,
					                                                       urlResponseCode,
					                                                       urlResponseMessage,
						                                                   urlResponseStatus);
        	
        	return Connresponse;        
	        
	    } catch (Exception e) {
	    	log.error("Error creating HTTP connection");
	    	urlResponseMessage = e.getMessage();
	    	urlResponseStatus = "Check Manually";
	    	
			//now form the output containing all the details to be display in the view.
			LnkConnectionResponse Connresponse = formLnkConnectionResponse(strUrl,
                                                                           strUrlDescription,
                                                                           -1,
                                                                           urlResponseMessage,
                                                                           urlResponseStatus	);
			
	        e.printStackTrace();
	        return Connresponse;    
	    }
	}
	
	*/
	
	private static SSLSocketFactory createSslSocketFactory() throws Exception {
	     TrustManager[] byPassTrustManagers = new TrustManager[] { new X509TrustManager() {
	         public X509Certificate[] getAcceptedIssuers() {
	             return new X509Certificate[0];
	         }

	         public void checkClientTrusted(X509Certificate[] chain, String authType) {

	         }

	         public void checkServerTrusted(X509Certificate[] chain, String authType) {

	         }

	     } };
	     SSLContext sslContext = SSLContext.getInstance("TLS");
	     sslContext.init(null, byPassTrustManagers, new SecureRandom());
	     return sslContext.getSocketFactory();
	    }
	
}
