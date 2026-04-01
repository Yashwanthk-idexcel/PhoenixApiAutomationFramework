package ApiTests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import Constants.Role;
import apiservices.MasterService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.module.jsv.JsonSchemaValidator;

@Listeners(listeners.ApiTestListeners.class)

@Epic("Job Management")
@Feature("Master Api Request")
public class MasterApiTest {
	
	private MasterService masterService;
	
	@BeforeMethod(description = "Initializing MasterService Class Object")
	public void setup() {
		masterService = new MasterService();
	}

	@Story("Master Api should bring OEM Details, Problem Type, Warrenty Status")
	@Description("Verify if master Api is working correctly")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Verify if the Master Api is giving correct response", groups = { "smoke", "api",
			"regression" })
	public void verifyMasterApi() {

		// RA default adds Content Type as"Content-Type=application/x-www-form-urlencoded"
		masterService.master(Role.FD) 
				.then().spec(SpecUtils.responseSpec_OK())
				.body("message", Matchers.equalTo("Success"))
				.body("data", Matchers.notNullValue())
				.body("$", Matchers.hasKey("message"))
				.body("$", Matchers.hasKey("data"))
				.body("data", Matchers.hasKey("mst_oem"))
				.body("data", Matchers.hasKey("mst_model"))
				.body("data.mst_oem.size()", Matchers.greaterThan(0))
				.body("data.mst_oem.id", Matchers.everyItem(Matchers.greaterThanOrEqualTo(1)))
				.body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterApiResponseSchema.json"));
	}

	@Test(description = "Verify if the Login Api is working for iamfd for invalid token", groups = { "smoke", "api",
			"regression", "negative" })
	public void invalidTokenForMasterApi() {

		 // RA default adds Content Type as "Content-Type=application/x-www-form-urlencoded"
		given().spec(SpecUtils.requestSpec())
				.when()
				.post("master")
				.then()
				.spec(SpecUtils.responseSpec_TEXT(401));
	}
}
