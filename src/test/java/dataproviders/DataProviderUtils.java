package dataproviders;

import java.util.ArrayList;
import java.util.Iterator;

import org.testng.annotations.DataProvider;

import ApiUtils.CSVReaderUtils;
import ApiUtils.CreateJobBeanMapper;
import DataProvidersApiBeans.CreateJobBean;
import DataProvidersApiBeans.UserBean;
import RequestModel.CreateJobPayload;

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

}
