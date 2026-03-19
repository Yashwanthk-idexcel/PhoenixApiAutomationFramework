package ApiTests.datadriven;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import Constants.Role;
import apiservices.JobService;
import requestmodel.CreateJobPayload;

public class CreateJobApiJsonDataDrivenTest {
	
	private JobService jobService;

	@BeforeMethod(description = "Initializing the JobService Class Object")
	public void setup() {
		jobService = new JobService();
	}

	@Test(description = "Verify if the CreateJob Api is working for iamfd", groups = { "datadriven", "api",
			"regression", "faker" }, dataProviderClass = dataproviders.DataProviderUtils.class, dataProvider = "CreateJobApiJsonDataProvider")
	public void createJobApiTest(CreateJobPayload createJobPayload) {

		jobService.create(Role.FD, createJobPayload)
				.then()
				.spec(SpecUtils.responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));
	}

}
