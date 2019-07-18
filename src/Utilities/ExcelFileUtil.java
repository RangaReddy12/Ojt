package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {
	Workbook wb;
	
	public ExcelFileUtil() throws Throwable	
	{
	FileInputStream fis=new FileInputStream("./TestInputs/InputSheet.xlsx")	;
	wb=WorkbookFactory.create(fis);
	}
	//count no of rows in sheet
	public int rowCount(String sheetname)
	{
	return wb.getSheet(sheetname).getLastRowNum();
	}
	//count no of columns
	public int colCount(String sheetname,int rownum)
	{
	return wb.getSheet(sheetname).getRow(rownum).getLastCellNum();
	}
	//get cell value
	public String getData(String sheetname,int rounum,int colnum)
	{
		String data="";
	if(wb.getSheet(sheetname).getRow(rounum).getCell(colnum).getCellType()==Cell.CELL_TYPE_NUMERIC)
	{
	int celldata=(int)wb.getSheet(sheetname).getRow(rounum).getCell(colnum).getNumericCellValue();
	data=String.valueOf(celldata);
	}
	else{
		data=wb.getSheet(sheetname).getRow(rounum).getCell(colnum).getStringCellValue();
	}
	return data;
	}
	//writing data into cell
	public void setData(String sheetname,int row,int column,String status) throws Throwable	{
		Sheet sh=wb.getSheet(sheetname);
		Row rownum = sh.getRow(row);
		Cell cell = rownum.createCell(column);
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("Pass"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			//Apply color To The Text
			font.setColor(IndexedColors.GREEN.getIndex());
			//Apply Bold To The Text
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		if(status.equalsIgnoreCase("Fail"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			//Apply color To The Text
			font.setColor(IndexedColors.RED.getIndex());
			//Apply Bold To The Text
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		if(status.equalsIgnoreCase("Not Executed"))
		{
			CellStyle style = wb.createCellStyle();
			Font font = wb.createFont();
			//Apply color To The Text
			font.setColor(IndexedColors.BLUE.getIndex());
			//Apply Bold To The Text
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rownum.getCell(column).setCellStyle(style);
		}
		
		FileOutputStream fos = new FileOutputStream("./TestOutput/Results.xlsx");
		wb.write(fos);
		fos.close();
	}
}
