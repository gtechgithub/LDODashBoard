package com.test.programs;

import com.pff.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailReadProcess {
		static  boolean fileFound = false;
		private Properties props = new Properties();
		
		private String configFilename;
		private String foldersToSearch;
		private String foldersSearchDelim;
		private List <String> foldersInList;
		private String subjectToSearch;
		private String ostFilepath;
		private boolean rerunOnceAgain;
		private String timeToRun;
		private String attachDownloadPath;

		
		public void readFromConfig(String filename) throws Exception {
			
			//path of the class file
			String filepath = EmailReadProcess.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			
			//String filepath = "M:\\Documents\\workspace-sts-3.7.0.RELEASE\\LDODashBoard\\target\\classes";
			
			//path of the config file
			configFilename = filename;

			props.load(new FileInputStream(filepath + "\\"+ configFilename));
			
			//it will be separated by pile delimited
			foldersToSearch = props.getProperty("FOLDERS_TO_SEARCH");
			subjectToSearch = props.getProperty("SUBJECT_TO_SEARCH");
			ostFilepath = props.getProperty("OST_FILE_PATH");
			foldersSearchDelim = props.getProperty("FOLDERS_SEPARATOR_DELIM");
			
			System.out.println("filepath:"  + filepath);
			System.out.println("configFilename:"  + configFilename);
			System.out.println("ost filename:" + ostFilepath);
			System.out.println("foldersToSearch:"+ foldersToSearch);
			System.out.println("subjectToSearch:"+ subjectToSearch);
			
			System.out.println("foldersSearchDelim:"  + foldersSearchDelim);
					
			if (props.getProperty("RERUN_ONCE_AGAIN").equalsIgnoreCase("true")){
				rerunOnceAgain = true;
			}
			else{
				rerunOnceAgain = false;
			}

			//eg: 16:00 or 09:01 
			timeToRun = props.getProperty("TIME_TO_RUN");
			attachDownloadPath = props.getProperty("ATTACHMENT_DOWNLOAD_PATH");
			System.out.println("attachDownloadPath:"  + attachDownloadPath);

			
			foldersInList = convertFoldersToList();
		}

        public static void main(String[] args)
        {
        	//String filename  = "C:\\Users\\gcothak2\\AppData\\Local\\Microsoft\\Outlook\\gopala.cothakotai@credit-suisse.com - Default Outlook Profile.ost";
        	//new EmailReadProcess(args[0]);
        	
        	String filename = "TSEEmailconfig.cfg";
        	EmailReadProcess emailProcess = new EmailReadProcess();
        	
        	try {
        		emailProcess.readFromConfig(filename);
        		emailProcess.EmailReadExecute();
        	}
        	catch(Exception e){
        		e.printStackTrace();
        	}
        	
        }

        public void EmailReadExecute() {
                try {
                	//ostFilepath = "C:\\Users\\gcothak2\\AppData\\Local\\Microsoft\\Outlook\\gopala.cothakotai@credit-suisse.com - Default Outlook Profile.ost";
                        PSTFile pstFile = new PSTFile(ostFilepath);
                        System.out.println(pstFile.getMessageStore().getDisplayName());
                        processFolder(pstFile.getRootFolder());
                } catch (Exception err) {
                        err.printStackTrace();
                }
        }

        int depth = -1;
        /**
         * @param folder
         * @throws PSTException
         * @throws java.io.IOException
         */
        /**
         * @param folder
         * @throws PSTException
         * @throws java.io.IOException
         */
        /**
         * @param folder
         * @throws PSTException
         * @throws java.io.IOException
         */
        public void processFolder(PSTFolder folder)
                        throws PSTException, java.io.IOException, Exception
        {
        	
        		if (fileFound) {
        			return;
        		}
        			
                depth++;
                // the root folder doesn't have a display name
                if (depth > 0) {
                        printDepth();
                        System.out.println(folder.getDisplayName());
                }

                
                // go through the folders...
                if (folder.hasSubfolders()) {
                	System.out.println("parent child folder:" + folder.getDisplayName());
                        Vector<PSTFolder> childFolders = folder.getSubFolders();
                        for (PSTFolder childFolder : childFolders) {
                                processFolder(childFolder);
                        }
                }

                //skip if not below folders
                /**
                if(! ((folder.getDisplayName().equals("Inbox"))
                		|| (folder.getDisplayName().equals("LDO"))) ){
                	return;
                }
                ***/
                
                //found not found in the search folder list, return here
                if (! folderFoundinSearchFoldersList(folder.getDisplayName())){
                	//System.out.println("folder.getDisplayName():" + folder.getDisplayName());
                	return;
                }
                
                // and now the emails for this folder
                if (folder.getContentCount() > 0) {
                	
                    //skip if not below folders
                	/**
                    if(! ((folder.getDisplayName().equals("Inbox"))
                    		|| (folder.getDisplayName().equals("LDO"))) ){
                    	return;
                    }
                    **/
                        depth++;
                        PSTMessage email = (PSTMessage)folder.getNextChild();
                        while (email != null) {

                        	//System.out.print("before the subject found:");
                                //if (email.getSubject().equals(subjectToSearch))
                        	    boolean issubjectfound = subjectFound(email.getSubject(),subjectToSearch);

                        	    
                                if (issubjectfound)	
                                {
                            	    System.out.println("is subject found:" +issubjectfound );

                                    System.out.println("Email: "+email.getSubject());
                                    System.out.println("Time:" + email.getCreationTime().toString());
                                    
                                    int year  = email.getCreationTime().getYear();
                                    int month  = email.getCreationTime().getMonth();
                                    int dayofmonth  = email.getCreationTime().getDate();

                                    //get the system date 
                                    String dateString =  year + "." +  month + "." + dayofmonth; 
                                    DateFormat format = new SimpleDateFormat("yyyy.MM.dd" ,Locale.ENGLISH);
                                    Date date  = format.parse(dateString);
                        
                                    DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                                    Date systemdate = new Date();
                                    System.out.println(dateFormat.format(systemdate)); //2014/08/06 15:59:48
                                    
                                    int systemyear =systemdate.getYear();
                                    int systemmonth = systemdate.getMonth();
                                    int systemdayofmonth =  systemdate.getDate();
                                    
                                    if ( year == systemyear && month == systemmonth && dayofmonth ==  systemdayofmonth)
                                    {
                                    	System.out.println(" file donwload for the today");
	                                	//TODO check the subject has attachment
	                                	downloadAttachment(email);
	                                    printDepth();
	                                    fileFound =  true;
	                                    //break;
	                                    return;
                                    }
                                }
                                email = (PSTMessage)folder.getNextChild();
                        }
                        depth--;
                }
                depth--;
        }

        public void printDepth() {
                for (int x = 0; x < depth-1; x++) {
                        System.out.print(" | ");
                }
                System.out.print(" |- ");
        }
        
        private boolean subjectFound(String subject,String searchString){
        	boolean found = false;
        	
        	//System.out.println("inside subject found function");
        	
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

            return found;
        }
        
        private List <String> convertFoldersToList(){
        	List <String> folderToList = null;
        	
        	//convert the folders string to array with the provided deliminator 
        	String folderArray [] = foldersToSearch.split("-");
        	
        	for (String s:folderArray){
        		System.out.println("folderArray:" + s);
        	}
        	
        	//convert array to list
        	folderToList =  Arrays.asList(folderArray);
        	
        	return folderToList;
        }
        
        private boolean folderFoundinSearchFoldersList(String emailFolderName){ 
        
        	boolean folderNamefound = false;
        	
        	for (String item:foldersInList ){
        		//System.out.println("item:" + item);
        		if ( item.equals(emailFolderName)){
        			System.out.println("folder name found:" + emailFolderName);
        			folderNamefound =  true;
        			break;
        		}
        	}
        	
        	return folderNamefound;
        }
        
        private void downloadAttachment(PSTMessage email ) throws Exception{
        	
        int numberOfAttachments = email.getNumberOfAttachments();
        for (int x = 0; x < numberOfAttachments; x++) {
                PSTAttachment attach = email.getAttachment(x);
                InputStream attachmentStream = attach.getFileInputStream();
                // both long and short filenames can be used for attachments
                String filename = attach.getLongFilename();
                if (filename.isEmpty()) {
                        filename = attach.getFilename();
                }
                FileOutputStream out = new FileOutputStream(attachDownloadPath + "\\" + filename);
                // 8176 is the block size used internally and should give the best performance
                int bufferSize = 8176;
                byte[] buffer = new byte[bufferSize];
                int count = attachmentStream.read(buffer);
                while (count == bufferSize) {
                        out.write(buffer);
                        count = attachmentStream.read(buffer);
                }
                byte[] endBuffer = new byte[count];
                System.arraycopy(buffer, 0, endBuffer, 0, count);
                out.write(endBuffer);
                out.close();
                attachmentStream.close();
        	}
        }
}