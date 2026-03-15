package apiservices;

import static ApiUtils.ConfigManager.getProperty;
import static Constants.Role.FD;
import static io.restassured.RestAssured.given;

import ApiUtils.SpecUtils;
import Constants.Role;
import io.restassured.response.Response;

public class DashboardService {
	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	
	public Response count(Role role) {
		Response response = given()
								.spec(SpecUtils.requestSpecWithAuth(role))
								.when()
								.get(COUNT_ENDPOINT);
		
		return response;
	}
	
	public Response countWithNoAuth() {
		Response response = given().spec(SpecUtils.requestSpec())
								.when()
								.get(COUNT_ENDPOINT);
		
		return response;
	}

}
