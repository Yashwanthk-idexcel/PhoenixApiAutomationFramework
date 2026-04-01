package ApiTests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import Constants.Role;
import apiservices.DashboardService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import requestmodel.Details;

@Listeners(listeners.ApiTestListeners.class)
@Epic("Job Management")
@Feature("Job Details")
public class DashboardDetailsAPITest {

	private DashboardService dashboardService;
	private Details detailsPayload;

	@BeforeMethod(description = "Initializing the DashboardService class Object")
	public void setup() {
		dashboardService = new DashboardService();
		detailsPayload = new Details("created_today");
	}

	@Story("Job Details is shown correctly for FD")
	@Description("Verify if jobDetails Api is working correctly")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "verify if Details API is working properly", groups = { "api", "smoke", })
	public void detailsApiTest() {

		dashboardService.details(Role.FD, detailsPayload).then().spec(SpecUtils.responseSpec_OK()).body("message",
				Matchers.equalTo("Success"));
	}
}
