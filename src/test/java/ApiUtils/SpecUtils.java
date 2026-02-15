package ApiUtils;

import org.hamcrest.Matchers;

import Constants.Role;
import RequestModel.UserCredentials;
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
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.setAccept(ContentType.JSON).log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS)
				.log(LogDetail.BODY).build();

		return requestSpecification;
	}

	// Put - Patch - Post -> RequestPayload
	public static RequestSpecification requestSpec(Object payload) {

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON).setBody(payload)
				.setAccept(ContentType.JSON).log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS)
				.log(LogDetail.BODY).build();

		return requestSpecification;
	}

	public static RequestSpecification requestSpecWithAuth(Role role) {

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role)).setAccept(ContentType.JSON)
				.log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY).build();

		return requestSpecification;
	}

	public static RequestSpecification requestSpecWithAuth(Role role, Object payload) {

		RequestSpecification requestSpecification = new RequestSpecBuilder()
				.setBaseUri(ConfigManager.getProperty("BASE_URI")).setContentType(ContentType.JSON)
				.addHeader("Authorization", AuthTokenProvider.getToken(role)).setAccept(ContentType.JSON)
				.setBody(payload).log(LogDetail.URI).log(LogDetail.METHOD).log(LogDetail.HEADERS).log(LogDetail.BODY)
				.build();

		return requestSpecification;
	}

	public static ResponseSpecification responseSpec_OK() {

		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(200).expectResponseTime(Matchers.lessThan(1000L)).log(LogDetail.ALL).build();

		return responseSpecification;
	}

	public static ResponseSpecification responseSpec_JSON(int statusCde) {

		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectContentType(ContentType.JSON)
				.expectStatusCode(statusCde).expectResponseTime(Matchers.lessThan(1000L)).log(LogDetail.ALL).build();

		return responseSpecification;
	}

	public static ResponseSpecification responseSpec_TEXT(int statusCde) {

		ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(statusCde)
				.expectResponseTime(Matchers.lessThan(1000L)).log(LogDetail.ALL).build();

		return responseSpecification;
	}

}
