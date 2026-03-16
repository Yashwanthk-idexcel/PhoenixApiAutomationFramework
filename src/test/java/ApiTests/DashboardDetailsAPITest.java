package ApiTests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import Constants.Role;
import RequestModel.Details;
import apiservices.DashboardService;

public class DashboardDetailsAPITest {

	private DashboardService dashboardService;
	private Details detailsPayload;

	@BeforeMethod(description = "Initializing the DashboardService class Object")
	public void setup() {
		dashboardService = new DashboardService();
		detailsPayload = new Details("created_today");
	}

	@Test(description = "verify if Details API is working properly", groups = { "api", "smoke", })
	public void detailsApiTest() {

		dashboardService.details(Role.FD, detailsPayload).then().spec(SpecUtils.responseSpec_OK()).body("message",
				Matchers.equalTo("Success"));
	}
}
