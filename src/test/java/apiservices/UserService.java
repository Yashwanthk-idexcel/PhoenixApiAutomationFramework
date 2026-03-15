package apiservices;

import static Constants.Role.FD;
import static io.restassured.RestAssured.given;

import ApiUtils.SpecUtils;
import Constants.Role;
import io.restassured.response.Response;

public class UserService {
	
	private static  String USERDETAILS_ENDPOINT = "userdetails";
	
	public Response userDetails(Role role) {
		Response response = given().spec(SpecUtils.requestSpecWithAuth(role))
		.when().get(USERDETAILS_ENDPOINT);
		
		return response;
	}
}
