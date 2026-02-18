package ApiUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import DataProvidersApiBeans.UserBean;

public class CSVReaderUtils {
	/**
	 * Constructor is private All the methods are static
	 * Purpose: Help me to read csv file and map it to Bean class
	 */

	private CSVReaderUtils() {

	}

	public static <T> Iterator<T> loadCSV(String pathOfCsvFile, Class<T> bean) {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(pathOfCsvFile);
		InputStreamReader reader = new InputStreamReader(is);
		CSVReader csvRead = new CSVReader(reader);

		// Code to map the csv to pojo
		CsvToBean<T> csvToBean = new CsvToBeanBuilder(csvRead)
				.withType(bean)
				.withIgnoreEmptyLine(true)
				.build();

		List<T> list = csvToBean.parse();
		return list.iterator();
	}

}
