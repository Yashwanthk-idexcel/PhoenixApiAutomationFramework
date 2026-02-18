package dataproviders;

import java.util.Iterator;

import org.testng.annotations.DataProvider;

import ApiUtils.CSVReaderUtils;
import DataProvidersApiBeans.UserBean;

public class DataProviderUtils {

	@DataProvider(name = "LoginApiDataProvider", parallel = true)
	public static Iterator<UserBean> loginApiDataProvider() {
		return CSVReaderUtils.loadCSV("test-data\\LoginCreds.csv");
	}
}
