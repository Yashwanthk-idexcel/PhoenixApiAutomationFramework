package ApiTests.datadriven;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import apiservices.AuthService;
import requestmodel.UserCredentials;

public class LoginApiJsonDataDrivenTest {
	
	private AuthService authService;

	@BeforeMethod(description = "Initializing AuthService class object")
	public void setup() {
		authService = new AuthService();
	}

	@Test(description = "Verify if the Login Api is working for iamfd", groups = { "datadriven", "api", "regression",
			"csv" }, dataProviderClass = dataproviders.DataProviderUtils.class, dataProvider = "LoginApiJsonDataProvider")
	public void loginApiTest(UserCredentials userCreds) {

		authService.login(userCreds)
				.then()
				.spec(SpecUtils.responseSpec_OK())
				.and()
				.body("message", equalTo("Success"))
				.and()
				.body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
	}
}
