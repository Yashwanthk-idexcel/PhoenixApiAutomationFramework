package datadriventesting;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class ReadFromCSV {

	public static void main(String[] args) throws IOException, CsvException {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("test-data/LoginCreds.csv");
		InputStreamReader reader = new InputStreamReader(is);
		
		/**
		 * The below code, is not suggested to use - Hard Coding of file path
		 * File file = new File("C:\\Users\\yashwanth.k\\OneDrive - Idexcel\\A SDET 2026\\Java-Rest Assured API Testing\\PhoenixApiAutomationFramework\\src\\main\\resources\\test-data\\LoginCreds.csv");
		 * FileReader reader = new FileReader(file);
		 */
		
		CSVReader csvRead = new CSVReader(reader);

		List<String[]> dataList = csvRead.readAll();

		for (String[] dataArray : dataList) {
			System.out.println(Arrays.toString(dataArray));
		}

		long lines = csvRead.getLinesRead();
		System.out.println(lines); // Returns number of Rows

		long records = csvRead.getRecordsRead();
		System.out.println(records);
	}
}
