package com.unixconnection;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.implementation.TestLnkConnection;

public class SSHClientCertificate {

	static Logger log = Logger.getLogger(TestLnkConnection.class.getName());	

    public UnixProcessOutput ExecuteCommandWrapper(String userName, String password, String host, String path) throws IOException {
        Connection connection = null;
        StringBuffer stringbufferTemp =  null;
        List <StringBuffer> results = new ArrayList <StringBuffer>();
        String command = null;
        UnixProcessOutput process;
        
        try {
            connection = connectTo(host, userName, password);
            
            command = "date";
            stringbufferTemp  = executeCommand(path, connection,command);
            results.add(stringbufferTemp);
            
            
            //command = "ls -ltr " + path; 
            command = "ls -ltr /app/ControlMagent6.2/prod/PNYSHCTM01/sysout/COMFEE04D* | tail -1";
            
            stringbufferTemp  = executeCommand(path, connection,command);
            results.add(stringbufferTemp);
            
            //command = "tail -f `ls -ltr  COMFEE04D* |tail -1 | awk  'BEGIN { FS=\" \" } { print $8 }'`"; 
            //command  = "tail -15 `ls -ltr /app/ControlMagent6.2/prod/PNYSHCTM01/sysout/COMFEE04D* | tail -1 | awk  \'BEGIN { FS=\" \" } { print $8 }\'`";
            command = "grep -E 'ATL_REP_0|: ED0|: EDU|: EDT|: ATE|: TMP|: EO2|: BNY|: OPS|: TRADES|ATL_EOD|: ATL_EOD_B|: LDM1_EOD_B|: LDM4_EOD_B|: ATLEOD' `ls -ltr /app/ControlMagent6.2/prod/PNYSHCTM01/sysout/COMFEE04D* | tail -1 | awk  'BEGIN { FS=\" \" } { print $8 }'` | grep  -v '/app/java/bin/java'";
            stringbufferTemp  = executeCommand(path, connection,command);
            results.add(stringbufferTemp);
               
          
        } catch(Exception e) {
        	e.printStackTrace();
            if (connection != null) {
                connection.close();
            }
           
        }
        
        process = new UnixProcessOutput();
        
        //assing the results of the commfee04d output
        process.setOutput(results);
        process.setDescription("COMMFEE04D Job Monitoring");
        
        for ( StringBuffer res: results){
            log.info("Output:" + res.toString());        	
        }

        //return results;
        return process;
    }

    private Connection connectTo(String host, String userName, final String password) throws IOException {
        Connection connection = new Connection(host);

        String  [] ciphers = {
        		                //"crypticore128@ssh.com",
        						"aes128-cbc",
        						"aes192-cbc",
        						"aes256-cbc",
        						"aes128-ctr",
        						"aes192-ctr",
        						"aes256-ctr",
        						"3des-cbc" //,
        						//"seed-cbc@ssh.com" 
        						};
        
        String [] macs = { 
        		            //"crypticore-mac@ssh.com",
        					"hmac-md5",
        					"hmac-sha1" };
        						
        						
        String [] algos  = {
        					"ssh-dss",
        					"ssh-rsa"//,
        					//"ssh-dss-sha256@ssh.com",
        					//"ssh-rsa-sha256@ssh.com",
        					//"x509v3-sign-dss",
        					//"x509v3-sign-rsa",
        					//"x509v3-sign-dss-sha256@ssh.com",
        					//"x509v3-sign-rsa-sha256@ssh.com"
        					};
        
        log.info("before connect");
        
        connection.setServer2ClientCiphers(ciphers);
        connection.setServer2ClientMACs(macs);
        connection.setServerHostKeyAlgorithms(algos);
        connection.connect();

        File file = new File("C:\\Program Files (x86)\\SSH Communications Security\\SSH Tectia\\Root-CA-Certificates\\prod_csi_ca.pem.cer");
        
        log.info("before authentication with public key");
        
        boolean isAuthenticated = connection.authenticateWithPublicKey(userName, file, password);
        
        log.info(" is authenticated:" + isAuthenticated);



        return connection;
    }

    private StringBuffer executeCommand(String path, Connection connection, String command) throws IOException {
        //String command = "ls -la " + path;
        StringBuffer results = new StringBuffer();
        String temp = null;
        Session session = null;
        

        
       try {
    	   
    	   //System.out.println("Command:" + command);
           //open connection session
           session = connection.openSession();
            
           session.execCommand(command);
           results.append("<DIV align=left>");
            InputStream stdout = new StreamGobbler(session.getStdout());

            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            
                while ((temp = br.readLine()) != null) {
                	results.append("<br>" + temp);
            }
            results.append("</DIV>");
       }
       finally {
           if (session != null) {
               session.close();
           }
       }

        return results;
    }
}


