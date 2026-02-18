package ApiTests.datadriven;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import DataProvidersApiBeans.UserBean;

public class LoginApiDataDrivenTest {

	@Test(description = "Verify if the Login Api is working for iamfd", groups = { "smoke", "api", "regression" }, dataProviderClass = dataproviders.DataProviderUtils.class
			,dataProvider = "LoginApiDataProvider")
	public void loginApiTest(UserBean userbean) {
		System.out.println(userbean.toString());
		
		given().spec(SpecUtils.requestSpec(userbean)).when().post("login").then().spec(SpecUtils.responseSpec_OK())
				.and().body("message", equalTo("Success")).and()
				.body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
	}

}
