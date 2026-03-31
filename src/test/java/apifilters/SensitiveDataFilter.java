package apifilters;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class SensitiveDataFilter implements Filter {

	private static final Logger LOGGER = LogManager.getLogger(SensitiveDataFilter.class);

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		LOGGER.info("**************** Request Details ****************");
		LOGGER.info("BASE URI: \n {}", requestSpec.getURI());
		LOGGER.info("HTTP Method: \n {}", requestSpec.getMethod());
		redactHeaderInfo(requestSpec);

		redactRequestBody(requestSpec);
		Response response = ctx.next(requestSpec, responseSpec);

		LOGGER.info("**************** Response Details ****************");
		LOGGER.info("STATUS CODE: \n {}", response.getStatusCode());
		LOGGER.info("RESPONSE TIME in ms: \n {}", response.timeIn(TimeUnit.MILLISECONDS));
		LOGGER.info("RESPONSE Headers \n {}", response.getHeaders());
		redactResponseBody(response);
		return response;
	}

	// Create a method to REDACT/Hide the password from the request payload
	public void redactRequestBody(FilterableRequestSpecification requestSpec) {
		if (requestSpec.getBody() != null) {
			String requestPayload = requestSpec.getBody().toString(); // Returns Body info in string

			// Journey to Hide the Payload starts here
			requestPayload = requestPayload.replaceAll("\"password\"\s*:\s*\"[^\"]+\"", "\"password\": \"[REDACTED]\"");
			LOGGER.info("Request Body: \n {}", requestPayload);
		}
	}

	public void redactResponseBody(Response response) {
		String responseBody = response.asPrettyString(); // Returns Body info in string

		// Journey to Hide the Response Body starts here
		responseBody = responseBody.replaceAll("\"token\"\s*:\s*\"[^\"]+\"", "\"token\": \"[REDACTED]\"");
		LOGGER.info("Response Body: \n {}", responseBody);
	}

	public void redactHeaderInfo(FilterableRequestSpecification requestSpec) {
		List<Header> headerList = requestSpec.getHeaders().asList();

		for (Header header : headerList) {
			if (header.getName().equalsIgnoreCase("Authorization"))
				LOGGER.info("Header {} : {}", header.getName(), "\"[REDACTED]\"");
			else
				LOGGER.info("Header {} : {}", header.getName(), header.getValue());
		}

	}
}
