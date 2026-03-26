package dataproviders;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import ApiUtils.CSVReaderUtils;
import ApiUtils.CreateJobBeanMapper;
import ApiUtils.ExcelReaderUtil;
import ApiUtils.FakerDataGenerator;
import ApiUtils.JsonReaderUtil;
import DataProvidersApiBeans.CreateJobBean;
import DataProvidersApiBeans.UserBean;
import database.dao.CreateJobPayloadDataDao;
import requestmodel.CreateJobPayload;
import requestmodel.UserCredentials;

public class DataProviderUtils {

	private static final Logger LOGGER = LogManager.getLogger(DataProviderUtils.class);
	
	@DataProvider(name = "LoginApiDataProvider", parallel = true)
	public static Iterator<UserBean> loginApiDataProvider() {
		LOGGER.info("Loading data from the CSV file at test-data\\LoginCreds.csv");
		return CSVReaderUtils.loadCSV("test-data\\LoginCreds.csv", UserBean.class);
	}

	@DataProvider(name = "CreateJobApiCsvDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobApiCsvDataProvider() {
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;
		
		LOGGER.info("Loading data from the CSV file at test-data\\CreateJobData.csv");
		Iterator<CreateJobBean> createJobBeanIterator = CSVReaderUtils.loadCSV("test-data\\CreateJobData.csv",
				CreateJobBean.class);

		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		while (createJobBeanIterator.hasNext()) {
			tempBean = createJobBeanIterator.next();
			tempPayload = CreateJobBeanMapper.mapper(tempBean);

			payloadList.add(tempPayload);
		}

		return payloadList.iterator();
	}

	@DataProvider(name = "CreateJobApiFakerDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobApiFakerDataProvider() {
		LOGGER.info("Generating the Random Faker Data for Create Job Api");
		
		int fakerCount = Integer.parseInt(System.getProperty("fakerCount", "5"));
		Iterator<CreateJobPayload> payloadIterator = FakerDataGenerator.generateFakeCreateJobData(fakerCount);
		return payloadIterator;
	}

	@DataProvider(name = "LoginApiJsonDataProvider", parallel = true)
	public static Iterator<UserBean> loginApiJsonDataProvider() {
		LOGGER.info("Loading data from the JSON file at test-data\\LoginApiTestData.json");
		return JsonReaderUtil.loadJSON("test-data\\LoginApiTestData.json", UserBean[].class);
	}

	@DataProvider(name = "CreateJobApiJsonDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobApiJsonDataProvider() {
		LOGGER.info("Loading data from the JSON file at test-data\\CreateJobApiData.json");
		return JsonReaderUtil.loadJSON("test-data\\CreateJobApiData.json", CreateJobPayload[].class);
	}

	@DataProvider(name = "LoginApiExcelDataProvider", parallel = true)
	public static Iterator<UserBean> loginApiExcelDataProvider() {
		LOGGER.info("Loading data from the EXCEL file at test-data\\PhoenixTestData.xlsx and Sheet - LoginTestData");
		return ExcelReaderUtil.loadExcelData("test-data\\PhoenixTestData.xlsx", "LoginTestData", UserBean.class);
	}

	@DataProvider(name = "CreateJobApiExcelDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobApiExcelDataProvider() {
		CreateJobBean tempBean;
		CreateJobPayload tempPayload;
		LOGGER.info("Loading data from the EXCEL file at test-data\\PhoenixTestData.xlsx and Sheet - CreateJobTestData");

		Iterator<CreateJobBean> createJobBeanIterator = ExcelReaderUtil.loadExcelData("test-data\\PhoenixTestData.xlsx",
				"CreateJobTestData", CreateJobBean.class);

		ArrayList<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		while (createJobBeanIterator.hasNext()) {
			tempBean = createJobBeanIterator.next();
			tempPayload = CreateJobBeanMapper.mapper(tempBean);

			payloadList.add(tempPayload);
		}

		return payloadList.iterator();
	}
	
	@DataProvider(name = "CreateJobApiDBDataProvider", parallel = true)
	public static Iterator<CreateJobPayload> createJobApiDBDataProvider() {
		LOGGER.info("Loading data from the Database for CreateJobPayload");
		
	    List<CreateJobBean> beanPayload = CreateJobPayloadDataDao.getCreateJobPayloadData();
	    List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
	    
	    for (CreateJobBean bean : beanPayload) {
	    	CreateJobPayload payload = CreateJobBeanMapper.mapper(bean);
	    	payloadList.add(payload);
		}
	    	    
	    return payloadList.iterator();
	}

}
