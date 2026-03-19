package apiservices;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ApiUtils.SpecUtils;
import io.restassured.response.Response;
import requestmodel.UserCredentials;

public class AuthService {
	// Service class is going to hold

	private static final String lOGIN_ENDPOINT = "login";
	private static final Logger LOGGER = LogManager.getLogger(AuthService.class);

	// Method name should be same as the endpoint
	public Response login(Object loginApiPayload) {

		LOGGER.info("Making Login requrest for the Payload: {}", ((UserCredentials)loginApiPayload).username());
		Response response = given().spec(SpecUtils.requestSpec(loginApiPayload)).when().post(lOGIN_ENDPOINT);

		return response;

	}

}
