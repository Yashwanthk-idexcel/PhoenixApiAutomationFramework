package ApiTests.datadriven;

import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import ApiUtils.SpecUtils;
import Constants.Role;
import RequestModel.CreateJobPayload;

public class CreateJobApiFakerDataDrivenTest {

	@Test(description = "Verify if the CreateJob Api is working for iamfd", groups = { "datadriven", "api",
			"regression",
			"faker" }, dataProviderClass = dataproviders.DataProviderUtils.class, dataProvider = "CreateJobApiFakerDataProvider")
	public void createJobApiTest(CreateJobPayload createJobPayload) {

		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, createJobPayload))
				.when()
				.post("/job/create")
				.then()
				.spec(SpecUtils.responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));
	}

}
