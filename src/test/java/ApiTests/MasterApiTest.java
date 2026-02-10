package ApiTests;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import ApiUtils.AuthTokenProvider;
import ApiUtils.ConfigManager;
import Constants.Role;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;

import static io.restassured.RestAssured.*;

public class MasterApiTest {

	@Test
	public void verifyMasterApi() {
		
		given().baseUri(ConfigManager.getProperty("BASE_URI"))
		.headers("Authorization", AuthTokenProvider.getToken(Role.FD))
		.log().uri()
		.contentType("")
		.log().method()
		.log().headers()
		.when()
		.post("master") // RA default adds Content Type as "Content-Type=application/x-www-form-urlencoded"
		.then()
		.log().all()
		.statusCode(200)
		.time(Matchers.lessThan(1000L))
		.body("message", Matchers.equalTo("Success"))
		.body("data", Matchers.notNullValue())
		.body("$", Matchers.hasKey("message"))
		.body("$", Matchers.hasKey("data"))
		.body("data", Matchers.hasKey("mst_oem"))
		.body("data", Matchers.hasKey("mst_model"))
		.body("data.mst_oem.size()", Matchers.greaterThan(0))
		.body("data.mst_oem.id", Matchers.everyItem(Matchers.greaterThanOrEqualTo(1)))
		.body("data.mst_oem.name", Matchers.everyItem(Matchers.notNullValue()))
		.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("response-schema/MasterApiResponseSchema.json"));
	}
	
	
	@Test
	public void invalidTokenForMasterApi() {
		
		given().baseUri(ConfigManager.getProperty("BASE_URI"))
		.log().uri()
		.contentType("")
		.log().method()
		.log().headers()
		.when()
		.post("master") // RA default adds Content Type as "Content-Type=application/x-www-form-urlencoded"
		.then()
		.log().all()
		.statusCode(401);
	}
}
