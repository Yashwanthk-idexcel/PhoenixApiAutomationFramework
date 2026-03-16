package ApiTests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import Constants.Role;
import RequestModel.Search;
import apiservices.JobService;

public class SearchJobApiTest {

	private static final String JOB_NUMBER = "JOB_217401";
	private JobService jobService;
	private Search searchPayload;
	
	@BeforeMethod(description = "Initializing JobService class object & creating Search payload")
	public void setup() {
		jobService = new JobService();
		searchPayload = new Search(JOB_NUMBER);
	}
	
	@Test(description = "Verify if the search api is working properly", groups = {"api", "smoke"})
	public void searchApiTest() {
		jobService.search(Role.FD, searchPayload)
			.then()
			.spec(SpecUtils.responseSpec_OK())
			.body("message", Matchers.equalTo("Success"));
	}
		
	
}
