package ApiUtils;

import java.util.Locale;
import com.github.javafaker.Faker;

public class Demo_Faker {

	public static void main(String[] args) {

		// We'll be using Faker Library for our Fake test data creation
		// We'll be creating a fakerUtil that uses this faker library

		Faker faker = new Faker(new Locale("en-IND")); // To get the India related names

		String firstName = faker.name().firstName();
		String lastName = faker.name().lastName();
		System.out.println(firstName + " " + lastName);
		
		String buildingNumber = faker.address().buildingNumber();
		System.out.println(buildingNumber);
		
		String streetAddress = faker.address().streetAddress();
		System.out.println(streetAddress);
		
		System.out.println(faker.address().streetName());
		
		System.out.println(faker.address().city());
		
		System.out.println(faker.number().digit());
		
		System.out.println(faker.number().digits(20));
		
		System.out.println(faker.phoneNumber().phoneNumber());
		
		// Alternate for Phone Number Generation
		System.out.println(faker.numerify("990#######"));
		System.out.println(faker.numerify("990#######"));
		System.out.println(faker.numerify("990#######"));
		System.out.println(faker.numerify("990#######"));
		
		System.out.println(faker.internet().emailAddress());

	}

}
