package ApiTests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import ApiUtils.AuthTokenProvider;
import static Constants.Role.*;

import static ApiUtils.ConfigManager.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserDetailsApiRequestTest {

	@Test
	public void userDetailsApiTest() {
		
		Header authHeader = new Header("Authorization", AuthTokenProvider.getToken(FD)); //header object
				
		Response responseBody = given()
			.baseUri(getProperty("BASE_URI"))
			.and()
			.accept(ContentType.ANY)
			.and()
			.header(authHeader)
		.when()
			.get("userdetails")
		.then()
			.log().all()
			.and()
			.statusCode(200)
			.body("message", equalTo("Success"))
			.body("data", notNullValue())
			.body(matchesJsonSchemaInClasspath("response-schema/UserDetailsApiResponseSchema.json"))
			.extract().response();
		
		JsonPath jsonBody = responseBody.jsonPath();
		int iD = jsonBody.getInt("data.id");
		System.out.println(iD);
		
	}
}
