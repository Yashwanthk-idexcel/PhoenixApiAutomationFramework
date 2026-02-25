package ApiUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import RequestModel.UserCredentials;

public class ExcelReaderUtil {

	private ExcelReaderUtil() {

	}

	public static Iterator<UserCredentials> loadExcelData(String path) throws IOException {
		// Apache Poi ooxml Lib

		InputStream is = Thread.currentThread().getContextClassLoader()
				.getResourceAsStream(path);
		XSSFWorkbook myWorkbook = new XSSFWorkbook(is);

		XSSFSheet sheet = myWorkbook.getSheet("LoginTestData");

		XSSFRow rowData;
		XSSFCell myCell;

		// Printing the excel details in console
//		int lastRowIndex = sheet.getLastRowNum();
//		System.out.println(lastRowIndex);
//		
//		int totalColumns = sheet.getRow(0).getLastCellNum();
//		System.out.println(totalColumns);
//		
//		int lastIndexOfCol = totalColumns -1;
//		System.out.println(lastIndexOfCol);
//		
//		// Fetch the details from the Excel Sheet
//		for (int rowIndex = 0; rowIndex <= lastRowIndex; rowIndex++) {
//			for (int colIndex = 0; colIndex <= lastIndexOfCol; colIndex++) {
//				
//				rowData = sheet.getRow(rowIndex);
//				myCell = rowData.getCell(colIndex);
//				System.out.print(myCell + " ");
//			}
//			System.out.println("");
//		}
// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

		// Store the excel details into the List
		// Read the excel file and store it in the ArrayList<UserCredentials>
		// I want to know the indexes of username and password

		UserCredentials credentials = null;

		XSSFRow headerRows = sheet.getRow(0);
		int usernameIndex = -1;
		int passwordIndex = -1;

		for (Cell cell : headerRows) {
			if (cell.getStringCellValue().trim().equalsIgnoreCase("username"))
				usernameIndex = cell.getColumnIndex();

			if (cell.getStringCellValue().trim().equalsIgnoreCase("password"))
				passwordIndex = cell.getColumnIndex();
		}

		int lastRowIndex = sheet.getLastRowNum();
		ArrayList<UserCredentials> userList = new ArrayList<UserCredentials>();

		for (int rowIndex = 0; rowIndex <= lastRowIndex; rowIndex++) {
			rowData = sheet.getRow(rowIndex);
			credentials = new UserCredentials(rowData.getCell(usernameIndex).toString(),
					rowData.getCell(passwordIndex).toString());
			
			userList.add(credentials);
		}

		return userList.iterator();
	}

}
