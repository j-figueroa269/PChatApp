package com.pager.utility;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author JFigu This class is to read test data from an excel sheet
 */

public class ExcelDataProvider {
	XSSFWorkbook wb;
	XSSFSheet ws;
	XSSFRow row;
	XSSFCell cell;


	public ExcelDataProvider() {
		// set file path
		File src = new File("./TestData/PagerTestData.xlsx");

		try {
			// convert file into raw data using fileinputstream
			FileInputStream fis = new FileInputStream(src);

			// to read xlsx file
			wb = new XSSFWorkbook(fis);
		} catch (Exception e) {
			System.err.println("Unable to read Excel file " + e.getMessage());
		}
	}
	
	//method to get string data 
	public String getStringData(String sheetName, int rowNum, int colNum) { 	 
		try {
			ws = wb.getSheet(sheetName);
			row = ws.getRow(rowNum);
			cell = row.getCell(colNum);
		} catch (Exception e) {
			 System.err.println("NullPointerException thrown! " + "\n"+ "\t -Either Sheet name does not exist, Row number or cell number provided is not defined in sheet");
		 }
		//navigate to the sheet and row and column
		return wb.getSheet(sheetName).getRow(rowNum).getCell(colNum).getStringCellValue();
	}

	 //method to get string data via index 
	  public String getStringData(int sheetIndex, int rowNum, int colNum) { 		
			try {
				 ws = wb.getSheetAt(sheetIndex);
				row = ws.getRow(rowNum);
				 cell = row.getCell(colNum);
			} catch (Exception e) {
				 System.err.println("NullPointerException thrown! " + "\n"+ "\t -Either Sheet index does not exist, Row number or cell number provided is not defined in sheet");
			 }
			//navigate to the sheet and row and colum
			return wb.getSheetAt(sheetIndex).getRow(rowNum).getCell(colNum).getStringCellValue();
	   }
	
	  
	  //method to get numeric data via index 
	  public double getNumericData(int sheetIndex, int row, int column) { 
		  //navigate to the sheet and row and column
		  return wb.getSheetAt(sheetIndex).getRow(row).getCell(column).getNumericCellValue();
	  }
	  
	  //method to get numeric data()
	   public double getNumericData(String sheetName, int row, int column) { 
		   //navigate to the sheet and row and column 
		   return wb.getSheet(sheetName).getRow(row).getCell(column).getNumericCellValue();
	  
	  }
}	   