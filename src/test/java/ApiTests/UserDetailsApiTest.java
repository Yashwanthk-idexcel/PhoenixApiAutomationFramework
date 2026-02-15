package ApiTests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import ApiUtils.AuthTokenProvider;
import ApiUtils.SpecUtils;

import static Constants.Role.*;

import static ApiUtils.ConfigManager.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserDetailsApiTest {

	@Test
	public void userDetailsApiTest() {
						
		Response responseBody = given()
			.spec(SpecUtils.requestSpecWithAuth(FD))
		.when()
			.get("userdetails")
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
