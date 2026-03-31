package apifilters;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {
	
	private static final Logger LOGGER = LogManager.getLogger();

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		System.out.println("------------------ Hello from the Filter!!! ------------------------");
		redactRequestBody(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec);
		redactResponseBody(response);
		System.out.println("------------------ I got the respone from Filter!!! ------------------------");
		return response;
	}

	// Create a method to REDACT/Hide the password from the request payload
	public void redactRequestBody(FilterableRequestSpecification requestSpec) {
		String requestPayload = requestSpec.getBody().toString(); // Returns Body info in string

		// Journey to Hide the Payload starts here
		requestPayload = requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\": \"[REDACTED]\"");
		LOGGER.info("Request Body : {}", requestPayload);
	}

	public void redactResponseBody(Response response) {
		String responseBody = response.asPrettyString(); // Returns Body info in string

		// Journey to Hide the Response Body starts here
		responseBody = responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\": \"[REDACTED]\"");
		LOGGER.info("Response Body : {}", responseBody);
	}
}
