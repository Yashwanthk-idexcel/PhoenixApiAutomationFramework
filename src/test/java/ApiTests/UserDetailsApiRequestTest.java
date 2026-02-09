package ApiTests;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import static ApiUtils.ConfigManager.*;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserDetailsApiRequestTest {

	@Test
	public void userDetailsApiTest() {
		
		String accessToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6NCwiZmlyc3RfbmFtZSI6ImZkIiwibGFzdF9uYW1lIjoiZmQiLCJsb2dpbl9pZCI6ImlhbWZkIiwibW9iaWxlX251bWJlciI6Ijg4OTk3NzY2NTUiLCJlbWFpbF9pZCI6Im1hcmtAZ21haWwuY29tIiwicGFzc3dvcmQiOiI1ZjRkY2MzYjVhYTc2NWQ2MWQ4MzI3ZGViODgyY2Y5OSIsInJlc2V0X3Bhc3N3b3JkX2RhdGUiOm51bGwsImxvY2tfc3RhdHVzIjowLCJpc19hY3RpdmUiOjEsIm1zdF9yb2xlX2lkIjo1LCJtc3Rfc2VydmljZV9sb2NhdGlvbl9pZCI6MSwiY3JlYXRlZF9hdCI6IjIwMjEtMTEtMDNUMDg6MDY6MjMuMDAwWiIsIm1vZGlmaWVkX2F0IjoiMjAyMS0xMS0wM1QwODowNjoyMy4wMDBaIiwicm9sZV9uYW1lIjoiRnJvbnREZXNrIiwic2VydmljZV9sb2NhdGlvbiI6IlNlcnZpY2UgQ2VudGVyIEEiLCJpYXQiOjE3NzA1MTQ3OTZ9.EQk5CBu9KPfwXkZwlVA7Mwh0LlO3mZ5gPj91k45OyNQ";
		Header authHeader = new Header("Authorization", accessToken); //header object
				
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
