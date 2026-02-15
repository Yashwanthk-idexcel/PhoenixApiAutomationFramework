package ApiTests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.Test;
import ApiUtils.SpecUtils;
import RequestModel.UserCredentials;

public class LoginApiTest {
	
	UserCredentials userCreds = new UserCredentials("iamfd", "password");
	
	@Test
	public void loginApiTest() {
		
		given()
			.spec(SpecUtils.requestSpec(userCreds))
		.when()
			.post("login")
		.then()
			.spec(SpecUtils.responseSpec_OK())
			.and()
			.body("message", equalTo("Success"))
			.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
	}
	
}
