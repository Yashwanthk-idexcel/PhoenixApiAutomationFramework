package ApiUtils;

import static io.restassured.RestAssured.*;

import static org.hamcrest.Matchers.*;

import Constants.Role;
import RequestModel.UserCredentials;

import static Constants.Role.*;

import io.restassured.http.ContentType;

public class AuthTokenProvider {

	public static String getToken(Role role) {

		UserCredentials userCredential = null;

		if (role == FD)
			userCredential = new UserCredentials("iamfd", "password");
		else if (role == SUP)
			userCredential = new UserCredentials("iamsup", "password");
		else if (role == ENG)
			userCredential = new UserCredentials("iameng", "password");
		else if (role == QC)
			userCredential = new UserCredentials("iamqc", "password");

		String token = given().baseUri(ConfigManager.getProperty("BASE_URI")).contentType(ContentType.JSON)
				.body(userCredential).when().post("login").then().log().ifValidationFails().statusCode(200)
				.body("message", equalTo("Success")).extract().body().jsonPath().getString("data.token");

		return token;

	}

}
