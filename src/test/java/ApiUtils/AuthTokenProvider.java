package ApiUtils;

import static Constants.Role.ENG;
import static Constants.Role.FD;
import static Constants.Role.QC;
import static Constants.Role.SUP;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import Constants.Role;
import io.restassured.http.ContentType;
import requestmodel.UserCredentials;

public class AuthTokenProvider {

	private static Map<Role, String> tokenCache = new ConcurrentHashMap<Role, String>();
	private static final Logger LOGGER = LogManager.getLogger(AuthTokenProvider.class);

	public static String getToken(Role role) {

		UserCredentials userCredential = null;

		LOGGER.info("Checking the token is present in the cache for role: {}", role);
		if (tokenCache.containsKey(role)) {
			LOGGER.info("Token found for the role: {}", role);
			return tokenCache.get(role);
		}
		LOGGER.info("Token not found, & making the login request for the role: {}", role);


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

		LOGGER.info("Token cached for future request for the role: {}", role);
		tokenCache.put(role, token);
		return token;

	}

}
