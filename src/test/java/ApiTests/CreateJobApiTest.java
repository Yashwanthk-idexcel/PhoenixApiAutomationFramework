package ApiTests;

import static io.restassured.RestAssured.*;
import static ApiUtils.DateTimeUtil.*;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
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

public class CreateJobApiTest {

	@Test
	public void createJobApiTest() {

		Customer customer = new Customer("Yashwanth", "K", "9786754626", "", "yashwanthk@gmail.com", "");

		CustomerAddress customerAddress = new CustomerAddress("#99", "Shivakrupa", "Abhayappa Layout",
				"Balaji Gents PG", "BTM 2nd Stage", "560076", "India", "Karnataka");

		CustomerProduct customerPorduct = new CustomerProduct(getPreviousDateByDays(10), "81119011374519",
				"81119011374519", "81119011374519", getPreviousDateByDays(10), Product.NEXUS_2.getCode(),
				Model.NEXUS_2_BLUE.getCode());

		Problems problem = new Problems(Problem.OVERHEATING.getCode(), "Solution");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problem);

		CreateJobPayload payload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(),
				Platform.FRONT_DESK.getCode(), WarrantyStatus.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer,
				customerAddress, customerPorduct, problemsList);

		
		given().spec(SpecUtils.requestSpecWithAuth(Role.FD, payload)).when().post("/job/create").then()
				.spec(SpecUtils.responseSpec_OK())
				.body(JsonSchemaValidator
						.matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));

	}
}
