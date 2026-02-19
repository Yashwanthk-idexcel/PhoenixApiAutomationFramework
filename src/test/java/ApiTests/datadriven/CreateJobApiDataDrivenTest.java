package ApiTests.datadriven;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import Constants.Role;
import RequestModel.CreateJobPayload;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiDataDrivenTest {
	
	@Test(description = "Verify if the CreateJob Api is working for iamfd", groups = { "datadriven", "api", "regression", "csv" }
	, dataProviderClass = dataproviders.DataProviderUtils.class
	, dataProvider = "CreateJobApiDataProvider")
	public void createJobApiTest(CreateJobPayload createJobPayload) {

		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayload)).when().post("/job/create").then()
				.spec(SpecUtils.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}
}
