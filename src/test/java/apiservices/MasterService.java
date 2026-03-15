package apiservices;

import static io.restassured.RestAssured.given;

import ApiUtils.SpecUtils;
import Constants.Role;
import io.restassured.response.Response;

public class MasterService {
	
	private static final String MASTER_ENDPOINT = "master";
	
	public Response master(Role role) {
	
		Response response = given().spec(SpecUtils.requestSpecWithAuth(role))
		.when().post(MASTER_ENDPOINT);
		
		return response;
	}

}
