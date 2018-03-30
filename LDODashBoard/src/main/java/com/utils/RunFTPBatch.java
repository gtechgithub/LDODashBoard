package com.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPFileFilter;
import org.apache.log4j.Logger;

import com.unixconnection.FMFile;

public class RunFTPBatch {

	static Logger log = Logger.getLogger(RunFTPBatch.class.getName());
	
	//TODO need to redfine the FMFILE
	FMFile fileDef;
	
	RunFTPBatch(FMFile fileDef){
		this.fileDef =  fileDef;
	}
	
	public void downloadFtpFile() throws Exception
	{
		FTPClient ftpclient = null;
		
		try
		{
			ftpclient = new FTPClient();
			log.info((new StringBuilder()).append("Connecting to : ").append(fileDef.getFromHostName()).append(" with ").append(fileDef.getFromUserId()).toString());
			

			//ftp connect
			ftpclient.connect(fileDef.getFromHostName());
			showServerReply(ftpclient);

			//ftp login
			boolean loginStatus = ftpclient.login(fileDef.getFromUserId(), fileDef.getFromPassword());
			showServerReply(ftpclient);

            if(!loginStatus){
            	log.info(" Failed to login on FTP server: ");
            	throw new Exception((new StringBuilder()).append("Failed to login on FTP server: ").append(fileDef.getFromHostName()).toString());
            }
                
			//ftp change
            boolean changeDirStatus = ftpclient.changeWorkingDirectory(fileDef.getFromPathname());
            showServerReply(ftpclient);
            


            //TODO we need to write the function to get the previous date here
            /*** get the previous date**/
            Calendar cal  = Calendar.getInstance();
            //subtracting a day
            cal.add(Calendar.DATE, -1);

            SimpleDateFormat datefmt = new SimpleDateFormat("yyyyMMdd");
            String result = datefmt.format(new Date(cal.getTimeInMillis()));
            
            //Date yesterdayDate  = new Date(result);
            
            Calendar yesterdayDate =  Calendar.getInstance();
            yesterdayDate.set(Calendar.DATE, Integer.parseInt(result)) ;
            
            //apply date
            String fromFileNamedate = applyDate(fileDef.getFromFilename(), yesterdayDate.getTime());
            
            /******* needed for the download
            String toFileNamedate = applyDate(fileDef.getToFilename(), yesterdayDate.getTime());

            FileOutputStream fileoutputstream = new FileOutputStream((new StringBuilder()).append("M:\\tempReports\\").append(toFileNamedate).toString());

            log.info((new StringBuilder()).append("Downloading: ").append(fromFileNamedate).toString());
            
            boolean retrivestatus = ftpclient.retrieveFile(fromFileNamedate, fileoutputstream);
            
            showServerReply(ftpclient);
            
            if (retrivestatus){
            	log.info((new StringBuilder()).append("Download Complete for ").append(fromFileNamedate).toString());
            }
            else{
                    log.info((new StringBuilder()).append("File ").append(fromFileNamedate).append(" is not available").toString());
            }
            
            ******/
            
            //check the file is available.
            FTPFileFilterCustom filter =  new FTPFileFilterCustom();
            FTPFile[] ftpfile =  ftpclient.listFiles(fromFileNamedate, filter);
            showServerReply(ftpclient);
            
            for (int size = 0; size < ftpfile.length; size++) {
            	log.info("File present " + ftpfile[size].toString());
            }
            

            
            ftpclient.logout();
            showServerReply(ftpclient);
            
            ftpclient.disconnect();
            showServerReply(ftpclient);
            	
		}
		catch(Exception e){
			log.error("Exception received here !!! " + e.getMessage());
			
            ftpclient.logout();
            ftpclient.disconnect();
			
		}
		
	}
	
    private static void showServerReply(FTPClient ftpclient)
    {
        String as[] = ftpclient.getReplyStrings();
        
        if(as != null && as.length > 0)
        {
            String as1[] = as;
            int i = as1.length;
            for(int size = 0; size < as.length ; size++)
            {
                log.info((new StringBuilder()).append("SERVER: ").append(as[size].toString()).toString());
            }

        }
    }

    private String applyDate(String s, Date date)
    {
        if(s.contains("<DATE_"))
        {
            String s1 = s.split("<DATE_")[0];
            String s2 = s.split("<DATE_")[1];
            String s3 = s2.split(">")[0];
            String s4 = s2.split(">")[1];
            SimpleDateFormat simpledateformat = new SimpleDateFormat(s3);
            s = (new StringBuilder()).append(s1).append(simpledateformat.format(date)).append(s4).toString();
        } else
        if(s.contains("<DATE>"))
        {
            SimpleDateFormat simpledateformat1 = new SimpleDateFormat("yyyyMMdd");
            s = s.replace("<DATE>", simpledateformat1.format(date));
        } else
        if(s.contains("<TIME>"))
        {
            SimpleDateFormat simpledateformat2 = new SimpleDateFormat("yyyyMMdd hh:mm");
            s = s.replace("<TIME>", simpledateformat2.format(date));
        }
        return s;
    }

    
}

class FTPFileFilterCustom implements  FTPFileFilter {
	 
    public boolean accept(FTPFile ftpFile) {
 
        return ftpFile.isFile();
 
    }
};
