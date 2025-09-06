package com.creatio.crm.framework.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	// This class will contain common methods to read data from Excel files.
	
	
	/**
	 * This method reads data from an Excel file and returns it as a list of maps.
	 * Each map represents a row in the Excel sheet, with column headers as keys.
	 * 
	 * @param ExcelFileName The name of the Excel file (with .xlsx extension).
	 * @param sheetName The name of the sheet to read data from.
	 * @return A list of maps containing the data from the specified sheet.
	 */
	public static List<Map<String,String>> readData(String ExcelFileName, String sheetName) {
		
		//Create a list of maps to hold the data
		List<Map<String,String>> data = new ArrayList<Map<String,String>>();
		
		
		try {
			// Read the Excel file and sheet
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + ExcelFileName);			
			
			//Load the file into Excel Class
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			//Read the sheet from excel workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			//Get the total number of rows and columns having data in the sheet
			int totalRows = sheet.getPhysicalNumberOfRows();
			int totalColumns = sheet.getRow(0).getPhysicalNumberOfCells();
			
			//Iterate through the rows and columns to read the data (data starts from row 1-row 0 is header)
			
			//iteration start from row 1 to till the last row
			for (int r = 1; r < totalRows; r++) {
				
				//Create a map to hold the data for each row
				Map<String, String> rowData = new HashMap<String, String>();
				
				//iteration start from column 0 to till the last cell in each row
				for (int c = 0; c < totalColumns; c++) {					
					String columnHeader = sheet.getRow(0).getCell(c).getStringCellValue();
					String columnValue= sheet.getRow(r).getCell(c).getStringCellValue();
					rowData.put(columnHeader, columnValue);
				}
				
				//store the row data in the list
				data.add(rowData);
				
			}			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		
		return data;		
	}
	
	// This method will read data from an Excel file and return it as a 2D array.			
	public static String [][] readExcelData(String ExcelFileName, String sheetName) {
		String [][] data = null;
		
		try {
			// Read the Excel file and sheet
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\TestData\\" + ExcelFileName);			
			
			//Load the file into Excel Class
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			
			//Read the sheet from excel workbook
			XSSFSheet sheet = workbook.getSheet(sheetName);
			
			//Get the total number of rows and columns having data in the sheet
			int totalRows = sheet.getPhysicalNumberOfRows();
			int totalColumns = sheet.getRow(0).getPhysicalNumberOfCells();
			
			//Initialize the 2D array with the size of total rows and columns
			data = new String[totalRows-1][totalColumns];	
			
			//Iterate through the rows and columns to read the data (data starts from row 1-row 0 is header)
			for (int r = 1; r < totalRows; r++) {
				for (int c = 0; c < totalColumns; c++) {
					data[r-1][c]=sheet.getRow(r).getCell(c).getStringCellValue();
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}			
		
		return data;
	}

}
