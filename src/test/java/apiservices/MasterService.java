package apiservices;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ApiUtils.SpecUtils;
import Constants.Role;
import io.restassured.response.Response;

public class MasterService {
	
	private static final String MASTER_ENDPOINT = "master";
	private static final Logger LOGGER = LogManager.getLogger(MasterService.class);
	
	public Response master(Role role) {
		LOGGER.info("Making master api request to: {} for the Role: {}", MASTER_ENDPOINT, role);
		
		Response response = given().spec(SpecUtils.requestSpecWithAuth(role))
		.when().post(MASTER_ENDPOINT);
		
		return response;
	}

}
