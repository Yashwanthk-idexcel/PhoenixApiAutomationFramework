package apiservices;

import static io.restassured.RestAssured.given;

import ApiUtils.SpecUtils;
import Constants.Role;
import io.restassured.response.Response;

public class DashboardService {
	
	private static final String COUNT_ENDPOINT = "/dashboard/count";
	private static final String COUNT_DETAILS = "/dashboard/details";
	
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
	
	public Response details(Role role, Object payload) {
		Response response = given()
								.spec(SpecUtils.requestSpecWithAuth(role))
								.body(payload)
								.when()
								.post(COUNT_DETAILS);
		
		return response;
	}

}
