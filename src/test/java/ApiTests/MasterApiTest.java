package ApiTests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import ApiUtils.AuthTokenProvider;
import ApiUtils.ConfigManager;
import ApiUtils.SpecUtils;
import Constants.Role;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class MasterApiTest {

	@Test(description = "Verify if the Master Api is giving correct response", groups = { "smoke", "api",
			"regression" })
	public void verifyMasterApi() {

		given().spec(SpecUtils.requestSpecWithAuth(Role.FD)).when().post("master") // RA default adds Content Type as
																					// "Content-Type=application/x-www-form-urlencoded"
				.then().spec(SpecUtils.responseSpec_OK()).body("message", Matchers.equalTo("Success"))
				.body("data", Matchers.notNullValue()).body("$", Matchers.hasKey("message"))
				.body("$", Matchers.hasKey("data")).body("data", Matchers.hasKey("mst_oem"))
				.body("data", Matchers.hasKey("mst_model")).body("data.mst_oem.size()", Matchers.greaterThan(0))
				.body("data.mst_oem.id", Matchers.everyItem(Matchers.greaterThanOrEqualTo(1)))
				.body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
				.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterApiResponseSchema.json"));
	}

	@Test(description = "Verify if the Login Api is working for iamfd for invalid token", groups = { "smoke", "api",
			"regression", "negative" })
	public void invalidTokenForMasterApi() {

		given().spec(SpecUtils.requestSpec()).when().post("master") // RA default adds Content Type as
																	// "Content-Type=application/x-www-form-urlencoded"
				.then().spec(SpecUtils.responseSpec_TEXT(401));
	}
}
