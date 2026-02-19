package ApiTests.datadriven;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import DataProvidersApiBeans.UserBean;
import RequestModel.UserCredentials;

public class LoginApiJsonDataDrivenTest {

	@Test(description = "Verify if the Login Api is working for iamfd", groups = { "datadriven", "api", "regression",
			"csv" }, dataProviderClass = dataproviders.DataProviderUtils.class, dataProvider = "LoginApiJsonDataProvider")
	public void loginApiTest(UserCredentials userCreds) {

		given().spec(SpecUtils.requestSpec(userCreds)).when().post("login").then().spec(SpecUtils.responseSpec_OK())
				.and().body("message", equalTo("Success")).and()
				.body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
	}

}
