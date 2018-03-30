package com.excelreader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.controller.DisplayController;

public class HostChecker {

	private final String FILE_PATH   = "M:\\tempReports\\cvservers.xlsx";
	static Logger log = Logger.getLogger(HostChecker.class.getName());
	List <HostDetailsMaper> HostDetailsMaperArray  = new ArrayList <HostDetailsMaper>();
	Set worksheetNameSet = new HashSet();
	
	
	public static void main (String Args[]){
		
		HostChecker hostchecker = new HostChecker ();
		
		//this will read server details from excel and store in the list
		hostchecker.readFromExcel();
		
		//this will check the host status from the server in the lists
		hostchecker.checkDirectoryisAccessibleWrapper();
		
		//this will write the status to the excel
		hostchecker.WriteContentsToExcelWrapper();
	}
	
	public void readFromExcel()
	{
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(FILE_PATH);
			// Using XSSF for xlsx format, for xls use HSSF
			Workbook workbook = new XSSFWorkbook(fis);
			
			int numberOfSheets = workbook.getNumberOfSheets();
			
			log.info("No of sheets:" + numberOfSheets);

			for (int i = 0; i < numberOfSheets; i++) {
				
				HostDetailsMaper hostdetails = null;
				Sheet sheet = workbook.getSheetAt(i);
				
				worksheetNameSet.add(workbook.getSheetName(i));
				
				//row interator
				Iterator rowIterator = sheet.iterator();
				
				//set the sheet name
				
				//iterating each row
				while (rowIterator.hasNext()) {
					
					hostdetails =  new HostDetailsMaper();
					hostdetails.setWorksheetName( workbook.getSheetName(i));
					log.info("hostdetails worksheetname:" + hostdetails.getWorksheetName());

					Row row = (Row) rowIterator.next();
					
					//TODO check header row for all the valid columns 

					Iterator cellIterator = row.cellIterator();

					 //Iterating over each cell (column wise)  in a particular row.
					
					 //initialize the cell index
					 int cellindex = 0;
					 
					 //looping for the column in the row
                     while (cellIterator.hasNext()) {
                    	 
                    	 Cell cell = (Cell) cellIterator.next();
                    	 
                    	 if (cellindex == 0){
                    		 hostdetails.setServerNameDesc(cell.getStringCellValue());
                    		 log.info("hostdetails servername description:" + hostdetails.getServerNameDesc());
                    	 }
                    	 else if (cellindex == 1) {
                    		 hostdetails.setProdhostname(cell.getStringCellValue());
                    		 log.info("hostdetails prodhostname:" + hostdetails.getProdhostname());
                    	 }
                    	 else if (cellindex ==2) {
                    		 if (! cell.getStringCellValue().isEmpty()) {
                    		 hostdetails.setProdHostshareDrivePath(cell.getStringCellValue());
                    		 log.info("hostdetails prodsharedrivepath:" + hostdetails.getProdHostshareDrivePath());
                    		 }
                    	 }
                    	 else if (cellindex == 3) {
                    		 hostdetails.setDrHostname(cell.getStringCellValue());
                    		 log.info("hostdetails drhostname:" + hostdetails.getDrHostname());
                    	 }
                    	 else if (cellindex == 4) {
                    		 if (! cell.getStringCellValue().isEmpty()) {
                    			 hostdetails.setDrHostshareDrivePath(cell.getStringCellValue());
                    			 log.info("hostdetails drsharedrivepath:" + hostdetails.getDrHostshareDrivePath());
                    		 }
                    	 }
                    	 cellindex++;
                     }
                     HostDetailsMaperArray.add(hostdetails);
				}
						
				
			}

				
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	public boolean checkDirectoryisAccessible(String sharedDrivefolderpath){
		boolean accessible = false;
		
		File file = new File(sharedDrivefolderpath);
		boolean isDirectory= file.isDirectory();
		boolean isExists =  file.exists();
		
		//the folder should exist and it should be directory
		accessible = isDirectory && isExists;
		
		log.info("is directory:" +  isDirectory );
		log.info("is Exists:" +  isExists );
		
		return accessible ;
	}
	
	public 	void checkDirectoryisAccessibleWrapper(){
		
		for (int index =0 ; index < HostDetailsMaperArray.size(); index ++)
		{
			HostDetailsMaper hostdetails = HostDetailsMaperArray.get(index);

			if (!hostdetails.getProdHostshareDrivePath().isEmpty()){
				//call the function to check the prod directory is accessible
				boolean isProdAccessible = checkDirectoryisAccessible(hostdetails.getProdHostshareDrivePath());

				//set the status of the folder accessible
				HostDetailsMaperArray.get(index).setProdHostConnectionStatus(isProdAccessible);
			}
			
			if (! hostdetails.getDrHostshareDrivePath().isEmpty()) {
				//call the function to check the DR directory is accessible
				boolean isDRAccessible = checkDirectoryisAccessible(hostdetails.getDrHostshareDrivePath());
				
				//set the status of the folder accessible
				HostDetailsMaperArray.get(index).setProdHostConnectionStatus(isDRAccessible);			
			}
			
		}
	}
	
	public void WriteContentsToExcelWrapper()
	{
		for (Iterator <String> iter = worksheetNameSet.iterator(); iter.hasNext();)
		{
			String sheetname  = iter.next();
			log.info(" creating list for the worksheet:"+ sheetname);
			
			List <HostDetailsMaper> returnlist = new ArrayList <HostDetailsMaper>();

			returnlist = extractRequiredElmentFromList(sheetname);
			
			writeContentsToExcel(returnlist,sheetname);
		}
		
	}
	
	public List <HostDetailsMaper> extractRequiredElmentFromList(final String sheetName)
	{
		List <HostDetailsMaper> returnlist = new ArrayList <HostDetailsMaper>();
		
		for (Iterator iter = HostDetailsMaperArray.iterator(); iter.hasNext();)
		{
			HostDetailsMaper hostdetails =  (HostDetailsMaper) iter.next();
			
			if ( sheetName.equals(hostdetails.getWorksheetName()) ) {
				
				log.info("matched for the sheetname:" + sheetName);
				
				//if (!hostdetails.getServerNameDesc().equals("server name")){
					returnlist.add(hostdetails);	
				//}
			}
		}
		return returnlist;
	}
	
	public void writeContentsToExcel(final List <HostDetailsMaper> hostdetails,final String sheetname)
	{
		log.info("inside the writeContentsToExcel");
		
		Workbook workbook = new XSSFWorkbook();
		
		Sheet hostdetailsheet = workbook.createSheet(sheetname);

		int rowIndex=0;
		
		for (Iterator iter = hostdetails.iterator(); iter.hasNext();)
		{
			log.info("looping for the " + rowIndex);
			 Row row = hostdetailsheet.createRow(rowIndex++);
			 HostDetailsMaper hostdetail = (HostDetailsMaper) iter.next();
			
			int cellIndex = 0;
			
			//place the header row
			if (rowIndex == 1)
			{
				//server name
				row.createCell(cellIndex++).setCellValue("server name") ;

				//prod host
				row.createCell(cellIndex++).setCellValue("prod host") ;
				
				//prod share dirve	
				row.createCell(cellIndex++).setCellValue("prod share drive") ;
				
				//dr host
				row.createCell(cellIndex++).setCellValue("dr host") ;
				
				//dr share drive
				row.createCell(cellIndex++).setCellValue("dr share drive") ;

				//prod status
				row.createCell(cellIndex++).setCellValue("prod status") ;
				
				//dr status
				row.createCell(cellIndex++).setCellValue("dr status") ;

			}
			else
			{
				//server name
				row.createCell(cellIndex++).setCellValue(hostdetail.getServerNameDesc()) ;

				//prod host
				row.createCell(cellIndex++).setCellValue(hostdetail.getProdhostname()) ;
				
				//prod share dirve	
				row.createCell(cellIndex++).setCellValue(hostdetail.getProdHostshareDrivePath()) ;
				
				//dr host
				row.createCell(cellIndex++).setCellValue(hostdetail.getDrHostname()) ;
				
				//dr share drive
				row.createCell(cellIndex++).setCellValue(hostdetail.getProdHostshareDrivePath()) ;

				//prod status
				row.createCell(cellIndex++).setCellValue(hostdetail.getProdHostConnectionStatus()) ;
				
				//dr status
				row.createCell(cellIndex++).setCellValue(hostdetail.getDrHostConnectionStatus()) ;
				
			}
		}
	
		//write this workbook in excel file.
		try {
			
			FileOutputStream fos = new FileOutputStream(FILE_PATH);
			workbook.write(fos);
			fos.close();
			
			System.out.println(FILE_PATH + " is successfully written");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	public boolean extractHonstConnectionStatus(final String hostname,final String sheetname)
	{
		boolean extractStatus = false;
		
		Iterator iter = HostDetailsMaperArray.iterator();
		
		while (iter.hasNext()){
			HostDetailsMaper hostdetails = (HostDetailsMaper) iter.next();
			
			if (hostdetails.getWorksheetName().equals(sheetname) && 
					hostdetails.getProdhostname().equals(hostname)) {
				extractStatus = hostdetails.getProdHostConnectionStatus();
				break;
				
			}
			else if (hostdetails.getWorksheetName().equals(sheetname) && 
					hostdetails.getDrHostname().equals(hostname)) {
				extractStatus = hostdetails.getDrHostConnectionStatus();
				break;
				
			}
		}
		return extractStatus;
	}
}


class HostDetailsMaper{
	
	private String worksheetName = "";
	private String serverNameDesc = "";
	private String prodhostname = "";
	private String prodHostshareDrivePath = "";
	private String drHostname= "";
	private String drHostshareDrivePath = "";
	private boolean prodHostConnectionStatus = false;
	private boolean drHostConnectionStatus = false;
	
	public String getServerNameDesc() {
		return serverNameDesc;
	}
	public void setServerNameDesc(String serverNameDesc) {
		this.serverNameDesc = serverNameDesc;
	}

	
	public String getWorksheetName() {
		return worksheetName;
	}
	public void setWorksheetName(String worksheetName) {
		this.worksheetName = worksheetName;
	}
	public String getProdhostname() {
		return prodhostname;
	}
	public void setProdhostname(String prodhostname) {
		this.prodhostname = prodhostname;
	}

	public String getDrHostname() {
		return drHostname;
	}
	public void setDrHostname(String drHostname) {
		this.drHostname = drHostname;
	}

	public boolean getProdHostConnectionStatus() {
		return prodHostConnectionStatus;
	}
	public void setProdHostConnectionStatus(boolean prodHostConnectionStatus) {
		this.prodHostConnectionStatus = prodHostConnectionStatus;
	}
	public boolean getDrHostConnectionStatus() {
		return drHostConnectionStatus;
	}
	public void setDrHostConnectionStatus(boolean drHostConnectionStatus) {
		this.drHostConnectionStatus = drHostConnectionStatus;
	}

	public String getProdHostshareDrivePath() {
		return prodHostshareDrivePath;
	}
	public void setProdHostshareDrivePath(String prodHostshareDrivePath) {
		this.prodHostshareDrivePath = prodHostshareDrivePath;
	}
	public String getDrHostshareDrivePath() {
		return drHostshareDrivePath;
	}
	public void setDrHostshareDrivePath(String drHostshareDrivePath) {
		this.drHostshareDrivePath = drHostshareDrivePath;
	}
	
}

