package datadriventesting;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;

public class ReadFromCSV_MapToPojo {

	public static void main(String[] args) throws IOException, CsvException {

		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("test-data/LoginCreds.csv");
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
