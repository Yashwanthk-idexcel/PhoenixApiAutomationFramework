package apiservices;

import static io.restassured.RestAssured.given;

import ApiUtils.SpecUtils;
import Constants.Role;
import RequestModel.CreateJobPayload;
import io.restassured.response.Response;

public class JobService {

	private static final String CREATE_ENDPOINT = "/job/create";
	
	public Response create(Role role, CreateJobPayload payload) {
		
		Response response = given().spec(SpecUtils.requestSpecWithAuth(role, payload))
		.when().post(CREATE_ENDPOINT);
		
		return response;
		
	}
	
}
