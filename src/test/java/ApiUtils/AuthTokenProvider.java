package ApiUtils;

import static Constants.Role.ENG;
import static Constants.Role.FD;
import static Constants.Role.QC;
import static Constants.Role.SUP;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import Constants.Role;
import RequestModel.UserCredentials;
import io.restassured.http.ContentType;

public class AuthTokenProvider {

	private static Map<Role, String> tokenCache = new ConcurrentHashMap<Role, String>();

	public static String getToken(Role role) {

		UserCredentials userCredential = null;

		if (tokenCache.containsKey(role))
			return tokenCache.get(role);

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

		tokenCache.put(role, token);
		return token;
		
	}

}
