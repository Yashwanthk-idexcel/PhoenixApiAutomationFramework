package ApiTests;

import static io.restassured.RestAssured.*;
import org.testng.Assert;
import static ApiUtils.DateTimeUtil.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.testng.Assert.assertEquals;

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
import database.dao.CustomerAddressTableDao;
import database.dao.CustomerProductTableDao;
import database.dao.CustomerTableDao;
import databasemodel.CustomerAddressDBModel;
import databasemodel.CustomerDBModel;
import databasemodel.CustomerProductDBModel;
import reponsemodel.CreateJobApiResponseModal;
import requestmodel.CreateJobPayload;
import requestmodel.Customer;
import requestmodel.CustomerAddress;
import requestmodel.CustomerProduct;
import requestmodel.Problems;

public class CreateJobApiWithDBValidationTest2 {
	// Extracting the response data with Deserialization Concept

	private CreateJobPayload payload;
	// Made as Global, to make it accessible in createJobApiTest method for validation
	Customer customer; 
	CustomerAddress customerAddress;
	CustomerProduct customerPorduct;

	@BeforeMethod(description = "Creating the payload for the CreateJob Api")
	public void setup() {
		customer = new Customer("Yashwanth", "K", "9786754626", "", "yashwanthk@gmail.com", "");

		customerAddress = new CustomerAddress("#99", "Shivakrupa", "Abhayappa Layout", "Balaji Gents PG",
				"BTM 2nd Stage", "560076", "India", "Karnataka");

		customerPorduct = new CustomerProduct(getPreviousDateByDays(10), "23233310000519", "23233310000519",
				"23233310000519", getPreviousDateByDays(10), Product.NEXUS_2.getCode(), Model.NEXUS_2_BLUE.getCode());

		Problems problem = new Problems(Problem.OVERHEATING.getCode(), "Solution");
		List<Problems> problemsList = new ArrayList<Problems>();
		problemsList.add(problem);

		payload = new CreateJobPayload(ServiceLocation.SERVICE_LOCATION_A.getCode(), Platform.FRONT_DESK.getCode(),
				WarrantyStatus.IN_WARRANTY.getCode(), OEM.GOOGLE.getCode(), customer, customerAddress, customerPorduct,
				problemsList);
	}

	
	@Test(description = "Verify CreateJob Api is successfully updating the customer details inside database", groups = {
			"smoke", "api", "regression" })
	public void createJobApiTest() {

		CreateJobApiResponseModal responsePayload = given().spec(SpecUtils.requestSpecWithAuth(Role.FD, payload)).when()
				.post("/job/create").then().spec(SpecUtils.responseSpec_OK())
				.body(matchesJsonSchemaInClasspath("response-schema/CreateJobApiResponseSchema.json"))
				.body("message", Matchers.equalTo("Job created successfully. "))
				.body("data.mst_service_location_id", Matchers.equalTo(1))
				.body("data.job_number", Matchers.startsWith("JOB_"))
				.extract().as(CreateJobApiResponseModal.class);
		
		int customerID = responsePayload.getData().getTr_customer_id();

		
		// Validate tr_customer table details
		CustomerDBModel customerDataFromDB = CustomerTableDao.getCustomerInfo(customerID);

		Assert.assertEquals(customer.first_name(), customerDataFromDB.getFirst_name());
		Assert.assertEquals(customer.last_name(), customerDataFromDB.getLast_name());
		Assert.assertEquals(customer.mobile_number(), customerDataFromDB.getMobile_number());
		Assert.assertEquals(customer.mobile_number_alt(), customerDataFromDB.getMobile_number_alt());
		Assert.assertEquals(customer.email_id(), customerDataFromDB.getEmail_id());
		Assert.assertEquals(customer.email_id_alt(), customerDataFromDB.getEmail_id_alt());

		
		// Validate tr_customer_address table details
		CustomerAddressDBModel customerAddressDataFromDB = CustomerAddressTableDao.getCustomerAddressInfo(customerID);

		Assert.assertEquals(customerAddress.flat_number(), customerAddressDataFromDB.getFlat_number());
		Assert.assertEquals(customerAddress.apartment_name(), customerAddressDataFromDB.getApartment_name());
		Assert.assertEquals(customerAddress.area(), customerAddressDataFromDB.getArea());
		Assert.assertEquals(customerAddress.landmark(), customerAddressDataFromDB.getLandmark());
		Assert.assertEquals(customerAddress.state(), customerAddressDataFromDB.getState());
		Assert.assertEquals(customerAddress.street_name(), customerAddressDataFromDB.getStreet_name());
		Assert.assertEquals(customerAddress.country(), customerAddressDataFromDB.getCountry());
		Assert.assertEquals(customerAddress.pincode(), customerAddressDataFromDB.getPincode());

		
		// Validate tr_customer_product table details
		CustomerProductDBModel customerProductDataFromDB = CustomerProductTableDao.getCustomerProductInfo(customerID);

		Assert.assertEquals((customerPorduct.dop()).split("T")[0], customerProductDataFromDB.getDop());
		Assert.assertEquals(customerPorduct.serial_number(), customerProductDataFromDB.getSerial_number());
		Assert.assertEquals(customerPorduct.mst_model_id(), customerProductDataFromDB.getMst_model_id());
		Assert.assertEquals(customerPorduct.imei1(), customerProductDataFromDB.getImei1());
		Assert.assertEquals(customerPorduct.imei2(), customerProductDataFromDB.getImei2());
		Assert.assertEquals(customerPorduct.popurl(), customerProductDataFromDB.getPopurl());

	}
}
