package ApiTests;

import static Constants.Role.FD;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.blankOrNullString;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import apiservices.DashboardService;


public class CountApiTest {
	
	DashboardService dashboardService;
	
	@BeforeMethod(description = "Initializing DashboardService Class Object")
	public void setup() {
		dashboardService = new DashboardService();
	}

	@Test(description = "Verify if the Count Api is working for iamfd", groups = { "smoke", "api", "regression" })
	public void verifyCountApiResponse() {

		dashboardService.count(FD)
				.then()
				.spec(SpecUtils.responseSpec_OK())
				.body("message", equalTo("Success"))
				.body("data", notNullValue())
				.body("data.size()", equalTo(3))
				.body("data.count", everyItem(greaterThanOrEqualTo(0)))
				.body("data.label", everyItem(not(blankOrNullString())))
				.body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
				.body(matchesJsonSchemaInClasspath("response-schema/CountApiResponseSchema-FD.json"));
	}

	@Test(description = "Verify if the Count Api is working for iamfd with invalid token", groups = { "smoke", "api",
			"regression", "negative" })
	public void countApiTest_MissingAuthToken() {
		dashboardService.countWithNoAuth()
				.then()
				.spec(SpecUtils.responseSpec_TEXT(401));
	}
}
