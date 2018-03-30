package com.utils;

import com.unixconnection.FMFile;
import com.utils.RunFTPBatch;

public class TestRunFTPBatch {

	public static void main (String args[]){
		
		FMFile file =  new FMFile();
		
		/** set the credentails **/
		file.setBatchid("CFELCH001D");
		file.setDescription("T11 CCP ICE Batch 1");
		file.setFileId(49);
		file.setFromHostName("ftp-ib.csintra.net");
		file.setFromUserId("ftptoice");
		file.setFromPassword("uwz289MT");
		file.setFromPathname("/incoming/LCH/PROD");
		file.setFromFilename("REP00084c");
		file.setToFilename("REP00084c_<DATE>.csv");
		
		RunFTPBatch ftpbatch = new RunFTPBatch(file);
		
		try {
		ftpbatch.downloadFtpFile();
		}
		catch (Exception e)
		{
			System.out.println("exception:" + e.getMessage());
		}
	}
}
