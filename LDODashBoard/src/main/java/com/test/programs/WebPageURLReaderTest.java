package com.test.programs;

import java.io.BufferedReader;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WebPageURLReaderTest {
	public static void main(String[] args) {
        URL url = null;
        HttpURLConnection urlConn = null;
        String strUrl = "http://appview.csfb.net/appview/Job_Status.asp?ID=3&APPLIC=LDO-INF&APPLGROUP=LDM-RECS"; 
        		
        try {
            
        	/*
            url = new URL(strUrl);
            urlConn = (HttpURLConnection)url.openConnection();
            urlConn.connect();

            
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String inputLine; 
 
            while ((inputLine = in.readLine()) != null) {
                // Process each line.
                System.out.println(inputLine);
            }
            in.close();
            */ 
           
        	//Document doc = Jsoup.parse(strUrl);
        	//System.out.println ("doc data" + doc.html());
        	
        	Document doc = Jsoup.connect(strUrl).get();
        	String text = doc.body().text();
        	
        	//System.out.println ("doc body text:" + text);
        	//System.out.println ("body" + doc.body().toString());
        	
        	for (Element elementtable : doc.select("table")){
	        	for( Element elementtr : elementtable.select("tr") )
	        	{
	        		System.out.println();
	        		for (Element elementtd : elementtr.select("td")){
	            	    System.out.print("|"+ elementtd.text());        			
	        		}
	        	}
        	}            	
        	
        	System.out.println("\n program ended successfully !!!!");
        	
 
        } catch (Exception ioe) {
            System.out.println(ioe.getMessage());
            ioe.printStackTrace();
        }
    }//end main
}
