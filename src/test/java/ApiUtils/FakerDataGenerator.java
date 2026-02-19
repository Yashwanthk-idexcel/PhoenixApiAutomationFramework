package ApiUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

import RequestModel.CreateJobPayload;
import RequestModel.Customer;
import RequestModel.CustomerAddress;
import RequestModel.CustomerProduct;
import RequestModel.Problems;

public class FakerDataGenerator {

	// Faker Data related to India
	private static Faker faker = new Faker(new Locale("en-IND"));
	private static final Random RANDOM = new Random();
	private static final String COUNTRY = "India";
	private static final int MST_SERVICE_LOCATION_ID = 0;
	private static final int MST_PLATFORM_ID = 2;
	private static final int MST_WARRENTY_STATUS_ID = 1;
	private static final int MST_OEM_ID = 1;
	private static final int PRODUCT_ID = 1;
	private static final int MST_MODEL_ID = 1;
	private static final int[] VALIDPROBLEMSIDS = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 15, 16, 17, 19, 20, 22, 24,
			26, 27, 28, 29 };

	private FakerDataGenerator() {

	}

	// Create Faker Payload for the CreateJobApi
	public static CreateJobPayload generateFakeCreateJobData() {

		Customer customer = generateFakeCustomerData();
		CustomerAddress customerAddress = generateFakeCustomerAddressData();
		CustomerProduct customerProduct = generateFakeCustomerProductData();
		List<Problems> problemList = generateFakeProblemsListData();

		CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
				MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);

		return payload;
	}

	// Create specific count of Faker Payload for the CreateJobApi
	public static Iterator<CreateJobPayload> generateFakeCreateJobData(int count) {

		List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();

		for (int i = 1; i <= count; i++) {

			Customer customer = generateFakeCustomerData();
			CustomerAddress customerAddress = generateFakeCustomerAddressData();
			CustomerProduct customerProduct = generateFakeCustomerProductData();
			List<Problems> problemList = generateFakeProblemsListData();

			CreateJobPayload payload = new CreateJobPayload(MST_SERVICE_LOCATION_ID, MST_PLATFORM_ID,
					MST_WARRENTY_STATUS_ID, MST_OEM_ID, customer, customerAddress, customerProduct, problemList);

			payloadList.add(payload);
		}

		return payloadList.iterator(); // Since DataProvider loves to have Iterator
	}

	// Creating Customer Faker Object
	private static Customer generateFakeCustomerData() {

		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String number = faker.numerify("99########");
		String altnumber = faker.numerify("98########");
		String email = faker.internet().emailAddress();
		String altemail = faker.internet().emailAddress();

		Customer customerData = new Customer(fname, lname, number, altnumber, email, altemail);

		return customerData;
	}

	// Creating Customer Address Faker Object
	private static CustomerAddress generateFakeCustomerAddressData() {

		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("######");
		String state = faker.address().state();

		CustomerAddress customerAddress = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area,
				pincode, COUNTRY, state);

		return customerAddress;
	}

	// Creating Customer Product Faker Object
	private static CustomerProduct generateFakeCustomerProductData() {

		String dop = DateTimeUtil.getPreviousDateByDays(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popUrl = faker.internet().url();

		CustomerProduct customerProduct = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popUrl, PRODUCT_ID, MST_MODEL_ID);

		return customerProduct;
	}

	// Creating Problems Faker Object
	private static List<Problems> generateFakeProblemsListData() {

		// Generate Random number between 1 - 27 [14,13,18... - Ids are not present ->
		// Inconsistency in ProblemIDs]

		int problemsCount = (RANDOM.nextInt(3) + 1); // 1 - 3 are valid number

		int randomIndex;
		String remark;
		List<Problems> problemsList = new ArrayList<Problems>();
		Problems problem;

		for (int i = 1; i <= problemsCount; i++) {

			randomIndex = RANDOM.nextInt(VALIDPROBLEMSIDS.length);
			remark = faker.lorem().sentence(3);

			problem = new Problems(VALIDPROBLEMSIDS[randomIndex], remark);

			problemsList.add(problem);
		}

		return problemsList;
	}

}
