package ApiTests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import Constants.Role;
import apiservices.UserService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


@Listeners(listeners.ApiTestListeners.class)
@Epic("User Management")
@Feature("User Details")
public class UserDetailsApiTest {
	
	private UserService userService;

	@BeforeMethod(description = "Initializing the UserService class oject")
	public void setup() {
		userService = new UserService();
	}

	
	@Story("User Details should be shown")
	@Description("Verify the UserDetails Api response is dipslaying correctly")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify the user details api response is shown correctly", groups = { "api", "smole",
			"regression" })
	public void userDetailsApiTest() {

		Response responseBody = userService.userDetails(Role.FD)
									.then()
									.spec(SpecUtils.responseSpec_OK())
									.body("message", equalTo("Success"))
									.body("data", notNullValue())
									.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsApiResponseSchema.json"))
									.extract().response();

		JsonPath jsonBody = responseBody.jsonPath();
		int iD = jsonBody.getInt("data.id");
		System.out.println(iD);

	}
}
