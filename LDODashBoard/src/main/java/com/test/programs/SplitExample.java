package com.test.programs;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SplitExample {

	public void searchString(){
		
		boolean found = false;
		String searchString = "^CLEARVISION CONTRACT FILE$";
		String subject = "CLEARVISION CONTRACT FILE";
		
      	Pattern p = Pattern.compile(searchString);
        Matcher m = p.matcher(subject); // get a matcher object
        int count = 0;
        
        //System.out.println("subject:" + subject);
        //System.out.println("searchString:" + searchString);
        
        while(m.find()) {
            count++;
        }

        if (count > 0){
        	System.out.println("count:" + count);
        	found = true;
        }

	}
        
        public void splitString() {
        	String foldersToSearch = "LDO-Inbox";

        	
        	//convert the folders string to array with the provided deliminator 
        	String folderArray [] = foldersToSearch.split("-");

        	for (String s:folderArray){
        		System.out.println("folderArray:" + s);
        	}
        	
        }
	
	public static void main (String args[]){
		SplitExample split = new SplitExample ();
		split.searchString();
	
  }
}



