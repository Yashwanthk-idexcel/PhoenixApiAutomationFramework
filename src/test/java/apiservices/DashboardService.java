package apiservices;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ApiUtils.SpecUtils;
import Constants.Role;
import io.restassured.response.Response;

public class DashboardService {
	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	private static final String COUNT_DETAILS = "/dashboard/details";
	private static final Logger LOGGER = LogManager.getLogger(DashboardService.class);

	
	public Response count(Role role) {
		LOGGER.info("Making Login request to: {} for the Role: {}", COUNT_ENDPOINT, role);
		Response response = given()
								.spec(SpecUtils.requestSpecWithAuth(role))
								.when()
								.get(COUNT_ENDPOINT);
		
		return response;
	}
	
	public Response countWithNoAuth() {
		LOGGER.info("Making Login request to: {} with no auth token", COUNT_ENDPOINT);
		Response response = given().spec(SpecUtils.requestSpec())
								.when()
								.get(COUNT_ENDPOINT);
		
		return response;
	}
	
	public Response details(Role role, Object payload) {
		LOGGER.info("Making Login request to details endpoint: {} for the Role: {} with payload: {}", COUNT_ENDPOINT, role, payload);
		Response response = given()
								.spec(SpecUtils.requestSpecWithAuth(role))
								.body(payload)
								.when()
								.post(COUNT_DETAILS);
		
		return response;
	}

}
