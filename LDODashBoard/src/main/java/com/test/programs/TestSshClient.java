package com.test.programs;

import java.util.List;
import com.unixconnection.SshClient;
import com.unixconnection.SSHClientCertificate;
import com.unixconnection.UnixProcessOutput;


public class TestSshClient {
	public static void main ( String args[]) {
		
		/*
		 String userName = "gcothak2";
	        String password = "3NriV3bD";
	        String host = "vpnl15a-0210";
	        
	        */
		String userName = "G224671";
        String password = "L0stcard123#";
        String host = "sgl20043451.ap.hedani.net";
     
		
	        String path = "/";
	        
	        try {

	        	/*
		        SshClient sshClient = new SshClient();
		        UnixProcessOutput output = sshClient.ExecuteCommandWrapper(userName, password, host, path);
				*/
	        	
	        	SSHClientCertificate sshClient =  new SSHClientCertificate();
		        UnixProcessOutput output = sshClient.ExecuteCommandWrapper(userName, password, host, path);

		        for (StringBuffer s: output.getOutput()) {
		            System.out.println(s.toString());
		        }

	        }
	        catch(Exception e){
	        	e.printStackTrace();
	        }
	}
}