package ApiTests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import DataProvidersApiBeans.UserBean;
import apiservices.AuthService;
import requestmodel.UserCredentials;

public class LoginApiTest {
	private UserBean userCreds;
	private AuthService authService;

	@BeforeMethod(description = "Create the request payload for the login api & Initializing AuthService class object")
	public void setup() {
		userCreds = new UserBean("iamfd", "password");
		authService = new AuthService();
	}

	@Test(description = "Verify if the Login Api is working for iamfd", groups = { "smoke", "api", "regression" })
	public void loginApiTest() {

		authService.login(userCreds)
				.then().spec(SpecUtils.responseSpec_OK())
				.and().body("message", equalTo("Success"))
				.and().body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
	}

}
