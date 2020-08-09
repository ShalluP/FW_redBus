package com.FW_redBus.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class ExcelUtils_redBus {
	public Workbook wb;
	public Sheet sh;
	String Excel_file_loc = "C:\\Users\\Shallu\\Desktop\\TestData_Excel_FrameWork\\Netbanking_Guru99_DDT.xlsx";

	public void setUpXlFile(String xl_filelocation, String sheetName) {

		try {
			FileInputStream fis = new FileInputStream(xl_filelocation);
			wb = WorkbookFactory.create(fis);
			sh = wb.getSheet(sheetName);
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	@DataProvider
	public Object[][] ExcelData_redBus() throws Exception, InvalidFormatException, IOException {
		/*
		 * FileInputStream fis_excel = new FileInputStream(Excel_file_loc); wb =
		 * WorkbookFactory.create(fis_excel); sh = wb.getSheet(Sheetname);
		 */// excel workbook and the sheet has been reached
			// to read the excel cells the limit of rows and columns has to be reached
		int tot_row_num = sh.getLastRowNum() + 1;
		int tot_col_num = sh.getRow(1).getLastCellNum();

		Object[][] read_Exceldata = new Object[tot_row_num][tot_col_num];// array is declared to store the cell value
																			// from excel sheet
// the cell value at index 1,0 (as 0th row is a header row generally)is transferred to the index 0,0 of array
//to read the excel sheet from 1st row till the last specified row, the for loop has to be created
// i and j are the index specifiers for arrays 
		// and the index for array starts from 0, so i starts from 0
		for (int i = 0; i < tot_row_num; i++) // outer loop to read the rows
		{
			for (int j = 0; j < tot_col_num; j++) // inner loop to read the columns
			{
				read_Exceldata[i][j] = sh.getRow(i).getCell(j).toString();
			}

		}

		return read_Exceldata;
	}
}
