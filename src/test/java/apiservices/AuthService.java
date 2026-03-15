package apiservices;

import static io.restassured.RestAssured.given;

import ApiUtils.SpecUtils;
import RequestModel.UserCredentials;
import io.restassured.response.Response;

public class AuthService {
	// Service class is going to hold

	private static final String lOGIN_ENDPOINT = "login";

	// Method name should be same as the endpoint
	public Response login(UserCredentials userCredsPayload) {

		Response response = given().spec(SpecUtils.requestSpec(userCredsPayload)).when().post(lOGIN_ENDPOINT);

		return response;

	}

}
