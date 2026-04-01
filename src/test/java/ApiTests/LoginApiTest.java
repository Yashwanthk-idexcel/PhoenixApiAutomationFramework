package ApiTests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import DataProvidersApiBeans.UserBean;
import apiservices.AuthService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Listeners(listeners.ApiTestListeners.class)
@Epic("User Management")
@Feature("Authentication")
public class LoginApiTest {
	private UserBean userCreds;
	private AuthService authService;

	@BeforeMethod(description = "Create the request payload for the login api & Initializing AuthService class object")
	public void setup() {
		userCreds = new UserBean("iamfd", "password");
		authService = new AuthService();
	}

	@Story("Valid user should be able to login into the system")
	@Description("Verify if FD USer is able to login via API")
	@Severity(SeverityLevel.CRITICAL)
	@Test(description = "Verify if the Login Api is working for iamfd", groups = { "smoke", "api", "regression" },
		retryAnalyzer = retry.RetryAnalyzer.class)
	public void loginApiTest() {

		authService.login(userCreds)
				.then().spec(SpecUtils.responseSpec_OK())
				.and().body("message", equalTo("Success"))
				.and().body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
	}

}
