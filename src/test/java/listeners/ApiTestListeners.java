package listeners;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ApiTestListeners implements ITestListener {

	private static final Logger LOGGER = LogManager.getLogger(ApiTestListeners.class);

	
	public void onTestStart(ITestResult result) {
		LOGGER.info("-------->>>>>>>> Starting the Test: {} <<<<<<<<<<<----------", result.getName());
		LOGGER.info("-------->>>>>>>>> Test class name: {} <<<<<<<<<<<----------", result.getMethod().getTestClass());
		LOGGER.info("-------->>>>>>>>> Description of Test method: {} <<<<<<<<<<<----------", result.getMethod().getDescription());
		LOGGER.info("-------->>>>>>>>> Api Tests Groups: {} <<<<<<<<<<<----------", Arrays.toString(result.getMethod().getGroups()));
	}
	
	public void onTestSuccess(ITestResult result) {
		long startTime = result.getStartMillis();
		long endTime = result.getEndMillis();
		LOGGER.info("-------->>>>>>>> Total Duration: {}ms <<<<<<<<<<<----------", (endTime - startTime));
		LOGGER.info("-------->>>>>>>> {} - Api Test Passed. <<<<<<<<<<<----------", result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		LOGGER.error("-------->>>>>>>> {} - Api Test Failed.!! <<<<<<<<<<<----------", result.getName());
		LOGGER.error("-------->>>>>>>> Error Message {} <<<<<<<<<<<----------", result.getThrowable().getMessage());
		LOGGER.error(result.getThrowable());
	}
	
	public void onTestSkipped(ITestResult result) {
		LOGGER.info("-------->>>>>>>> {} - Api Test Skipper.!! <<<<<<<<<<<----------", result.getName());
		LOGGER.error("-------->>>>>>>> Error Message {} <<<<<<<<<<<----------", result.getThrowable().getMessage());
		LOGGER.error(result.getThrowable());
	}
	
	public void onStart(ITestContext context) {
		LOGGER.info("-------->>>>>>>> Start of the Phoenix Api Framework <<<<<<<<<<<----------");
	}
	
	public void onFinish(ITestContext context) {
		LOGGER.info("-------->>>>>>>> Finished!! <<<<<<<<<<<----------");
	}

}
