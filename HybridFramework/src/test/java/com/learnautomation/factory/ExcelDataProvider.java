package com.learnautomation.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
	
	public String getCellData(String sheetName,int row,int column)
	{
		XSSFCell cell=wb.getSheet(sheetName).getRow(row).getCell(column);
		
		String data=null;
		
		if(cell.getCellType()==CellType.STRING)
		{
			data=cell.getStringCellValue();
		}
		else if(cell.getCellType()==CellType.NUMERIC)
		{
			data=String.valueOf(cell.getNumericCellValue());
		}
		else if(cell.getCellType()==CellType.BLANK)
		{
			data="";
		}

		return data;
		
	}
	
	
	public String getData(String sheetName,int row, int column) 
	{
		return wb.getSheet(sheetName).getRow(row).getCell(column).getStringCellValue();		
	}
	

}
