package ApiTests;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import ApiUtils.SpecUtils;
import Constants.Role;
import RequestModel.CreateJobPayload;
import RequestModel.Customer;
import RequestModel.CustomerAddress;
import RequestModel.CustomerProduct;
import RequestModel.Problems;
import io.restassured.module.jsv.JsonSchemaValidator;

public class CreateJobApiTest {

	@Test
	public void createJobApiTest() {

		Customer customer = new Customer("Yashwanth", "K", "9786754626", "", "yashwanthk@gmail.com", "");
		CustomerAddress customerAddress = new CustomerAddress("#99", "Shivakrupa", "Abhayappa Layout",
				"Balaji Gents PG", "BTM 2nd Stage", "560076", "India", "Karnataka");
		CustomerProduct customerPorduct = new CustomerProduct("2025-10-14T18:30:00.000Z", "77311111374519",
				"77311111374519", "77311111374519", "2025-10-14T18:30:00.000Z", 1, 1);
		Problems problem = new Problems(7, "Solution");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problem);

		CreateJobPayload payload = new CreateJobPayload(0, 2, 1, 1, customer, customerAddress, customerPorduct,
				problemsList);

		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, payload)).when().post("/job/create").then()
				.spec(SpecUtils.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}
}
