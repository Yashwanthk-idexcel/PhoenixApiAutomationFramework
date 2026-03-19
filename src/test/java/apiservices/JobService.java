package apiservices;

import static io.restassured.RestAssured.given;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ApiUtils.SpecUtils;
import Constants.Role;
import io.restassured.response.Response;
import requestmodel.CreateJobPayload;

public class JobService {

	private static final String CREATE_ENDPOINT = "/job/create";
	private static final String SEARCH_ENDPOINT = "/job/search";
	private static final Logger LOGGER = LogManager.getLogger(JobService.class);

	public Response create(Role role, CreateJobPayload payload) {
		LOGGER.info("Making create job request to: {} endpoint for the Role: {} with paylaod: {}", CREATE_ENDPOINT, role, payload);
		Response response = given().spec(SpecUtils.requestSpecWithAuth(role, payload)).when().post(CREATE_ENDPOINT);

		return response;

	}

	public Response search(Role role, Object payload) {
		LOGGER.info("Making search job request to: {} endpoint for the Role: {} with paylaod: {}", CREATE_ENDPOINT, role, payload);
		Response response = given().spec(SpecUtils.requestSpecWithAuth(role)).body(payload).post(SEARCH_ENDPOINT);

		return response;
	}

}
