package ApiUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import datadriventesting.UserBean;

public class CSVReaderUtility {
	/**
	 * Constructor is private All the methods are static
	 * Purpose: Help me to read csv file and map it to Bean class
	 */

	private CSVReaderUtility() {

	}

	public static void loadCSV(String pathOfCsvFile) {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCsvFile);
		InputStreamReader reader = new InputStreamReader(is);
		CSVReader csvRead = new CSVReader(reader);

		// Code to map the csv to pojo
		CsvToBean<UserBean> csvToBean = new CsvToBeanBuilder(csvRead)
				.withType(UserBean.class)
				.withIgnoreEmptyLine(true)
				.build();

		List<UserBean> userList = csvToBean.parse();
		System.out.println(userList);
		System.out.println(userList.get(1).getUsername());
		System.out.println(userList.get(0).getUsername());
	}

}
