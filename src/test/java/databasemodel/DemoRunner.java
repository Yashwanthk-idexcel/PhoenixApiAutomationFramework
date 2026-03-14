package databasemodel;

import database.dao.CustomerAddressTableDao;
import database.dao.CustomerProductTableDao;
import database.dao.CustomerTableDao;
import database.dao.JobHeadTableDao;
import database.dao.MapJobProblemTableDao;

public class DemoRunner {

	public static void main(String[] args) {
		
		CustomerDBModel cust = CustomerTableDao.getCustomerInfo(215160);
		System.out.println(cust);
		
		CustomerAddressDBModel custAddress = CustomerAddressTableDao.getCustomerAddressInfo(215160);
		System.out.println(custAddress);
		
		CustomerProductDBModel custProduct = CustomerProductTableDao.getCustomerProductInfo(215160);
		System.out.println(custProduct);
		
		MapJobProblemModel problemDetails = MapJobProblemTableDao.getProblemDetails(215142);
		System.out.println(problemDetails);
		
		JobHeadDBModel jobHeadInfo = JobHeadTableDao.getJobHeadInfo(215160);
		System.out.println(jobHeadInfo);

	}
}
