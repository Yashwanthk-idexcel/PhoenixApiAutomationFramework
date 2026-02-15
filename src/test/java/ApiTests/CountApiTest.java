package ApiTests;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import io.restassured.module.jsv.JsonSchemaValidator;

import static Constants.Role.*;

import static ApiUtils.ConfigManager.*;

public class CountApiTest {

	@Test(description = "Verify if the Count Api is working for iamfd", groups = { "smoke", "api", "regression" })
	public void verifyCountApiResponse() {

		given().baseUri(getProperty("BASE_URI")).spec(SpecUtils.requestSpecWithAuth(FD)).when().get("/dashboard/count")
				.then().spec(SpecUtils.responseSpec_OK()).body("message", equalTo("Success"))
				.body("data", notNullValue()).body("data.size()", equalTo(3))
				.body("data.count", everyItem(greaterThanOrEqualTo(0)))
				.body("data.label", everyItem(not(blankOrNullString())))
				.body("data.key", containsInAnyOrder("pending_for_delivery", "created_today", "pending_fst_assignment"))
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CountApiResponseSchema-FD.json"));
	}

	@Test(description = "Verify if the Count Api is working for iamfd with invalid token", groups = { "smoke", "api",
			"regression", "negative" })
	public void countApiTest_MissingAuthToken() {
		given().baseUri(getProperty("BASE_URI")).spec(SpecUtils.requestSpec()).when().get("/dashboard/count").then()
				.spec(SpecUtils.responseSpec_TEXT(401));
	}
}
