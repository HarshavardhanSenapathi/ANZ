package com.anz.util;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;


import java.io.FileInputStream;
import java.io.IOException;

import java.util.HashMap;

import java.util.Map;
import java.util.Map.Entry;



import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private XSSFSheet excelWSheet;
	

	private ConfigFileReader configFileReader = new ConfigFileReader();

	public XSSFSheet getSheet(String sheetName) {
		Map<String, XSSFSheet> sheetFromExcel = new HashMap<>();
		StringBuilder newPath = new StringBuilder(configFileReader.getValue("TestData")).append("/").append("/")
				.append("TestData.xlsx");
		
		try (FileInputStream ExcelFile = new FileInputStream(newPath.toString());
				XSSFWorkbook excelWBook = new XSSFWorkbook(ExcelFile)) {

			// Access the required test data sheet

			for (int i = 0; i < excelWBook.getNumberOfSheets(); i++) {
				excelWSheet = excelWBook.getSheetAt(i);
				sheetFromExcel.put(excelWBook.getSheetAt(i).getSheetName(), excelWBook.getSheetAt(i));
			}
			for (Entry<String, XSSFSheet> entry : sheetFromExcel.entrySet()) {
				if (sheetName.equals(entry.getKey())) {
					excelWSheet = entry.getValue();
				}
			}
		} catch (IOException e) {
			Log.debug("Error message:" + e);
		}

		return excelWSheet;
	}

	public Map<String, String> getHashValueFromExcel(XSSFSheet excelWSheet, String testCaseId)

	{
		Log.debug("enter");
		Map<String, String> totHash = new HashMap<>();
		try {
			for (int i = 0; i <= excelWSheet.getLastRowNum(); i++) {

				if (testCaseId.equalsIgnoreCase(excelWSheet.getRow(i).getCell(0).getStringCellValue().trim())) {
					
						for (int j = 0; j <= excelWSheet.getRow(i).getLastCellNum() - 1; j++) {
							String keyForMap = "";
							keyForMap = excelWSheet.getRow(0).getCell(j).getStringCellValue().trim();
							if (excelWSheet.getRow(i).getCell(j) != null
									&& excelWSheet.getRow(i).getCell(j).getCellTypeEnum() != CellType.BLANK
									&& excelWSheet.getRow(i).getCell(j).getCellTypeEnum() == CellType.STRING) {
								totHash.put(keyForMap, excelWSheet.getRow(i).getCell(j).getStringCellValue().trim());
							} else if (excelWSheet.getRow(i).getCell(j) != null
									&& excelWSheet.getRow(i).getCell(j).getCellTypeEnum() != CellType.BLANK
									&& excelWSheet.getRow(i).getCell(j).getCellTypeEnum() == CellType.NUMERIC) {

								totHash.put(keyForMap, NumberToTextConverter
										.toText(excelWSheet.getRow(i).getCell(j).getNumericCellValue()));
							}
						}
						break;
					
				}

			}
		} catch (Exception e) {
			Log.info("Exception" + e);
		} 
		return totHash;
	}

	
	public String[][] gettestcaseID(XSSFSheet excelWSheet) {
		int num=excelWSheet.getLastRowNum();
		int j=0;
		String[][] list=new String[num][num];
		try {
			for (int i = 1; i <= excelWSheet.getLastRowNum(); i++) {
				if (excelWSheet.getRow(i).getCell(j) != null
						&& excelWSheet.getRow(i).getCell(j).getCellTypeEnum() != CellType.BLANK
						&& excelWSheet.getRow(i).getCell(j).getCellTypeEnum() == CellType.STRING) {
					String testcaseId= excelWSheet.getRow(i).getCell(j).getStringCellValue().trim();
					
						list[i-1][j]=testcaseId;
					
					
								} 
				

			}
		}catch(Exception e) {
			Log.info("Exception" + e);
		}
		return list;
	}
}