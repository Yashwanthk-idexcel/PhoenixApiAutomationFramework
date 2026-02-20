package dataproviders;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import ApiUtils.CSVReaderUtils;
import ApiUtils.CreateJobBeanMapper;
import ApiUtils.FakerDataGenerator;
import ApiUtils.JsonReaderUtil;
import DataProvidersApiBeans.CreateJobBean;
import DataProvidersApiBeans.UserBean;
import RequestModel.CreateJobPayload;
import RequestModel.UserCredentials;

public class DataProviderUtils {

	@DataProvider(name = "LoginApiDataProvider", parallel = true)
	public static Iterator<UserBean> loginApiDataProvider() {
		return CSVReaderUtils.loadCSV("test-data\\LoginCreds.csv", UserBean.class);
	}

	@DataProvider(name = "CreateJobApiDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobApiDataProvider() {
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;

		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtils.loadCSV("test-data\\CreateJobData.csv",
				CreateJobBean.class);

		ArrayList<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		while (createJobBeanIterator.hasNext()) {
			tempBean = createJobBeanIterator.next();
			tempPayload = CreateJobBeanMapper.mapper(tempBean);

			payloadList.add(tempPayload);
		}

		return payloadList.iterator();
	}

	@DataProvider(name = "CreateJobApiFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobApiFakerDataProvider() {
		String fakerCount = System.getProperty("fakerCount", "5");
		int fakerCountInt = Integer.parseInt(fakerCount);
		Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.generateFakeCreateJobData(fakerCountInt);
		return payloadIterator;
	}

	@DataProvider(name = "LoginApiJsonDataProvider", parallel = true)
	public static Iterator<UserCredentials> loginApiJsonDataProvider() {
		return JsonReaderUtil.loadJSON("test-data\\LoginApiTestData.json", UserCredentials[].class);
	}
	
	@DataProvider(name = "CreateJobApiJsonDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobApiJsonDataProvider() {
		return JsonReaderUtil.loadJSON("test-data\\CreateJobApiData.json", CreateJobPayload[].class);
	}
	
	

}
