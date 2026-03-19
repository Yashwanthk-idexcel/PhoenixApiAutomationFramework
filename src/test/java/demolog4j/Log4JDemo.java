package demolog4j;

import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

public class Log4JDemo {

	private static Logger logger = LogManager.getLogger(Log4JDemo.class);

	public static void main(String[] args) {
		System.out.println("Inside the Main method");
		logger.info("Inside the Main method");
		int a = 10;
		System.out.println("Value of a is assigned as: " + a);
		logger.info("Value of a is assigned as: {}", a);
		int b = 20;
		System.out.println("Value of b is assigned as: " + b);
		logger.info("Value of b is assigned as: {}", b);

		int result = a + b;
		System.out.println("Result of a is Addition as: " + result);
		logger.info("Result of a is Addition as: {}", result);

		
		int c = 10;
		int d = 0;
		
		if (d == 0) {
			logger.warn("Value of b is: {}", b);
		} else {
			logger.info("Value of b is: {}", b);
		}
		
		int result1;
		
		try {
			result1= c/d;
		} catch(ArithmeticException e) {
			logger.error("Operation can't be performed", e);
		}

		System.out.println("Program ended");
		logger.info("Program ended");

	}

}
