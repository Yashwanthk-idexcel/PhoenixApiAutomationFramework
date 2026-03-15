package ApiTests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ApiUtils.SpecUtils;
import RequestModel.UserCredentials;
import apiservices.AuthService;

public class LoginApiTest {
	private UserCredentials userCreds;
	private AuthService authService;

	@BeforeMethod(description = "Create the request payload for the login api")
	public void setup() {
		userCreds = new UserCredentials("iamfd", "password");
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
