package ApiUtils;

import org.hamcrest.Matchers;

import Constants.Role;
import apifilters.SensitiveDataFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecUtils {
	
	// Get and Delete
	public static RequestSpecification requestSpec() {

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON)
				.addFilter(new SensitiveDataFilter())
				.build();

		return requestSpecification;
	}

	// Put - Patch - Post -> RequestPayload
	// Object Class - Loose Coupling- this works for all API's payload class
	public static RequestSpecification requestSpec(Object payload) {

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.setBody(payload)
				.setAccept(ContentType.JSON)
				.addFilter(new SensitiveDataFilter())
				.build();

		return requestSpecification;
	}

	
	// Requests which requires Authorization - By Role
	public static RequestSpecification requestSpecWithAuth(Role role) {

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.setAccept(ContentType.JSON)
				.addFilter(new SensitiveDataFilter())
				.build();

		return requestSpecification;
	}

	// Requests which requires Authorization & Payload - By Role
	public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI"))
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role))
				.setAccept(ContentType.JSON)
				.setBody(payload)
				.addFilter(new SensitiveDataFilter())
				.build();

		return requestSpecification;
	}

	// For all API Requests with 200 Status code
	public static ResponseSpecification responseSpec_OK() {

		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200)
				.expectResponseTime(Matchers.lessThan(1000L))
				.build();

		return responseSpecification;
	}

	// For specific status code
	public static ResponseSpecification responseSpec_JSON(int statusCde) {

		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(statusCde)
				.expectResponseTime(Matchers.lessThan(1000L))
				.build();

		return responseSpecification;
	}

	// API which doesn't require any Header Type - Empty Header
	public static ResponseSpecification responseSpec_TEXT(int statusCde) {

		ResponseSpecification responseSpecification = new ResponseSpecBuilder()
				.expectStatusCode(statusCde)
				.expectResponseTime(Matchers.lessThan(1000L))
				.build();

		return responseSpecification;
	}

}
