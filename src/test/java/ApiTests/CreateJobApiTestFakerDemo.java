package ApiTests;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ApiUtils.FakerDataGenerator;
import ApiUtils.SpecUtils;
import Constants.Role;
import RequestModel.CreateJobPayload;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiTestFakerDemo {
	private CreateJobPayload payload;

	@BeforeMethod(description = "Creating the payload for the CreateJob Api")
	public void setup() {
//		payload = DemoFaker2.payload();
		payload = FakerDataGenerator.generateFakeCreateJobData();
	}

	@Test(description = "Verify if the CreateJob Api is working for iamfd", groups = { "smoke", "api", "regression" })
	public void createJobApiTest() {

		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, payload)).when().post("/job/create").then()
				.spec(SpecUtils.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}
}
