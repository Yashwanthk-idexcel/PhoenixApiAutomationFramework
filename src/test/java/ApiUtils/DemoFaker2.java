package ApiUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

import RequestModel.CreateJobPayload;
import RequestModel.Customer;
import RequestModel.CustomerAddress;
import RequestModel.CustomerProduct;
import RequestModel.Problems;

public class DemoFaker2 {

	private final static String COUNTRY = "India";

	public static CreateJobPayload payload() {
		// Create fake payload for CreateJobApi Request
		// Want to create Fake Customer Object

		Faker faker = new Faker(new Locale("en-IND")); // India Specific Fake Data

		// Creating Customer Faker Object
		String fname = faker.name().firstName();
		String lname = faker.name().lastName();
		String number = faker.numerify("99########");
		String altnumber = faker.numerify("98########");
		String email = faker.internet().emailAddress();
		String altemail = faker.internet().emailAddress();

		Customer cust = new Customer(fname, lname, number, altnumber, email, altemail);
		System.out.println(cust);

		// Creating Customer Address Faker Object
		String flatNumber = faker.numerify("###");
		String apartmentName = faker.address().streetName();
		String streetName = faker.address().streetName();
		String landmark = faker.address().streetName();
		String area = faker.address().streetName();
		String pincode = faker.numerify("######");
		String state = faker.address().state();

		CustomerAddress custAdd = new CustomerAddress(flatNumber, apartmentName, streetName, landmark, area, pincode,
				COUNTRY, state);
		System.out.println(custAdd);

		// Creating Customer Product Faker Object
		String dop = DateTimeUtil.getPreviousDateByDays(10);
		String imeiSerialNumber = faker.numerify("###############");
		String popUrl = faker.internet().url();

		CustomerProduct custProd = new CustomerProduct(dop, imeiSerialNumber, imeiSerialNumber, imeiSerialNumber,
				popUrl, 1, 1);
		System.out.println(custProd);

		// Creating Problems Faker Object
		// Generate Random number between 1 - 27
		Random random = new Random();
		int problemId = random.nextInt(1, 28); // 28 is exlusive
		String remark = faker.lorem().sentence(3);

		List<Problems> problemsList = new ArrayList<Problems>();
		Problems problem = new Problems(problemId, remark);
		problemsList.add(problem);
		System.out.println(problem);

		CreateJobPayload payload = new CreateJobPayload(0, 2, 1, 1, cust, custAdd, custProd, problemsList);
		System.out.println(payload);

		return payload;

	}

}
