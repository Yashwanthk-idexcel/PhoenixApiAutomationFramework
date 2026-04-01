package ApiTests;

import static ApiUtils.DateTimeUtil.getPreviousDateByDays;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
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
import apiservices.JobService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import requestmodel.CreateJobPayload;
import requestmodel.Customer;
import requestmodel.CustomerAddress;
import requestmodel.CustomerProduct;
import requestmodel.Problems;

@Listeners(listeners.ApiTestListeners.class)
@Epic("Job Management")
@Feature("Job Creation")
public class CreateJobApiTest {
	private CreateJobPayload payload;
	private JobService jobService;

	@BeforeMethod(description = "Creating the payload for the CreateJob Api")
	public void setup() {
		
		jobService = new JobService();
		
		Customer customer = new Customer("Yashwanth", "K", "9786754626", "", "yashwanthk@gmail.com", "");

		CustomerAddress customerAddress = new CustomerAddress("#99", "Shivakrupa", "Abhayappa Layout",
				"Balaji Gents PG", "BTM 2nd Stage", "560076", "India", "Karnataka");

		CustomerProduct customerPorduct = new CustomerProduct(getPreviousDateByDays(10), "80033331374519",
				"80033331374519", "80033331374519", getPreviousDateByDays(10), Product.NEXUS_2.getCode(),
				Model.NEXUS_2_BLUE.getCode());

		Problems problem = new Problems(Problem.OVERHEATING.getCode(), "Solution");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problem);

		payload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(),
				WarrantyStatus.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerPorduct,
				problemsList);
	}

	@Story("FD should be able to create job")
	@Description("Verify if createJob Api is able to create inwarrenty job")
	@Severity(SeverityLevel.BLOCKER)
	@Test(description = "Verify if the CreateJob Api is working for iamfd", groups = { "smoke", "api", "regression" })
	public void createJobApiTest() {

		jobService.create(Role.FD, payload)
				.then()
				.spec(SpecUtils.responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"));
	}
}
