package ApiTests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.testng.annotations.Test;

import static ApiUtils.ConfigManager.*;
import PojoClasses.UserCredentials;
import io.restassured.http.ContentType;

public class LoginApiRequestTest {
	
	UserCredentials userCreds = new UserCredentials("iamfd", "password");
	
	@Test
	public void loginApiTest() {
		
		given()
			.baseUri(getProperty("BASE_URI"))
			.and()
			.contentType(ContentType.JSON)
			.and()
			.accept(ContentType.JSON)
			.and()
			.body(userCreds)
			.and()
			.log().uri()
			.log().method()
			.log().headers()
			.log().body()
		.when()
			.post("login")
		.then()
			.log().all()
			.and()
			.statusCode(200)
			.and()
			.body("message", equalTo("Success"))
			.and()
			.time(lessThan(1500L))
			.and()
			.body(matchesJsonSchemaInClasspath("response-schema/LoginApiResponseSchema.json"));
	}
	
}
