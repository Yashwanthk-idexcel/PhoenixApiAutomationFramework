package ApiTests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.module.jsv.JsonSchemaValidator;

import static ApiUtils.AuthTokenProvider.*;
import static Constants.Role.*;

import static ApiUtils.ConfigManager.*;

public class CountApiTest {

	@Test
	public void verifyCountApiResponse() {
		
		given().baseUri(getProperty("BASE_URI"))
		.and()
		.header("Authorization", getToken(FD))
		.log().uri()
		.log().headers()
		.log().method()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(200)
		.body("message", equalTo("Success"))
		.time(lessThan(1000L))
		.body("data", notNullValue())
		.body("data.size()", equalTo(3))
		.body("data.count", everyItem(greaterThanOrEqualTo(0)))
		.body("data.label", everyItem(not(blankOrNullString())))
		.body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/CountApiResponseSchema-FD.json"));	
	}
	
	@Test
	public void countApiTest_MissingAuthToken() {
		given().baseUri(getProperty("BASE_URI"))
		.and()
		.log().uri()
		.log().headers()
		.log().method()
		.when()
		.get("/dashboard/count")
		.then()
		.log().all()
		.statusCode(401);
	}
}
