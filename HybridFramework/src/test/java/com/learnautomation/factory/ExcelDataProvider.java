package com.learnautomation.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelDataProvider 
{
	
	XSSFWorkbook wb;
	
	public ExcelDataProvider()
	{
		
		try 
		{
			wb=new XSSFWorkbook(new FileInputStream(new File(System.getProperty("user.dir")+"/TestData/Data.xlsx")));
		} catch (IOException e) {
			
			System.out.println("Exception while loading excel "+e.getMessage());
		}
		
	}
	
	public String getData(String sheetName,int row, int column) 
	{
		return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();		
	}
	

}
