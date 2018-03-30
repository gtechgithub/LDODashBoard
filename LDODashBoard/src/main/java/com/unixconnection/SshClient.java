package com.unixconnection;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.implementation.TestLnkConnection;

public class SshClient {

	static Logger log = Logger.getLogger(SshClient.class.getName());	

    public UnixProcessOutput ExecuteCommandWrapper(String userName, String password, String host, Command commandList, String jobDescription) throws IOException {
        Connection connection = null;
        StringBuffer stringbufferTemp =  null;
        List <StringBuffer> results = new ArrayList <StringBuffer>();
        UnixProcessOutput process;
        
        try {
        	log.info("host:" + host);
        	log.info("userName:" + userName);
        	log.info("password:" + password);

            connection = connectTo(host, userName, password);
            
            
            for(String command : commandList.getCommand()){
            	 log.info("Command:" + command);
                stringbufferTemp  = executeCommand(connection,command);
                results.add(stringbufferTemp);
            }
            
            
            //command = "grep -E 'ATL_REP_0|: ED0|: EDU|: EDT|: ATE|: TMP|: EO2|: BNY|: OPS|: TRADES|ATL_EOD|: ATL_EOD_B|: LDM1_EOD_B|: LDM4_EOD_B|: ATLEOD' `ls -ltr /app/ControlMagent6.2/prod/PNYSHCTM01/sysout/COMFEE04D* | tail -1 | awk  'BEGIN { FS=\" \" } { print $8 }'` | grep  -v '/app/java/bin/java'";
          
        } catch(Exception e) {
        	e.printStackTrace();
            if (connection != null) {
                connection.close();
            }
           
        }
        
        process = new UnixProcessOutput();
        
        //assing the results of the commfee04d output
        process.setOutput(results);
        process.setDescription(jobDescription);
        
        for ( StringBuffer res: results){
            log.info("Output:" + res.toString());        	
        }

        //return results;
        return process;
    }

    private Connection connectTo(String host, String userName, final String password) throws IOException {
        Connection connection = new Connection(host);
        connection.connect();
        connection.authenticateWithPassword(userName, password);

        return connection;
    }
    
    private StringBuffer executeCommand(Connection connection, String command) throws IOException {
        StringBuffer results = new StringBuffer();
        String temp = null;
        Session session = null;
        

        
       try {
    	   
    	   //System.out.println("Command:" + command);
    	   log.info("Command:" + command);
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


