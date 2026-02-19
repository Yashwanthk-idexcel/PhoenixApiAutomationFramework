package ApiTests.datadriven;

import static io.restassured.RestAssured.*;
import static ApiUtils.DateTimeUtil.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import Constants.Model;
import Constants.OEM;
import Constants.Platform;
import Constants.Problem;
import Constants.Product;
import Constants.Role;
import Constants.ServiceLocation;
import Constants.WarrantyStatus;
import RequestModel.CreateJobPayload;
import RequestModel.Customer;
import RequestModel.CustomerAddress;
import RequestModel.CustomerProduct;
import RequestModel.Problems;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiFakerDataDrivenTest {

	@Test(description = "Verify if the CreateJob Api is working for iamfd", groups = { "datadriven", "api",
			"regression",
			"faker" }, dataProviderClass = dataproviders.DataProviderUtils.class, dataProvider = "CreateJobApiFakerDataProvider")
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
