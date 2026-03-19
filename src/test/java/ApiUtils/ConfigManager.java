package ApiUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfigManager {
// Program to read the Properties file from src/test/resources/config/config.properties

	private static Properties prop = new Properties();
	private static String path = "config/config.qa.properties";
	private static String env;
	private static final Logger LOGGER = LogManager.getLogger(ConfigManager.class);

	private ConfigManager() {
	}

	// Operation of loading the property file in memory
	// static block will only executed once during the class loading time
	static {
		LOGGER.info("Reading env value passed from terminal");

		if (System.getProperty("env") == null)
			LOGGER.warn("Env Variable is not sent... Using qa as default env");

		env = System.getProperty("env", "qa").toLowerCase().trim();
		LOGGER.info("Running the test in the environment: {}", env);

		switch (env) {
		case "dev" -> path = "config/config.dev.properties"; // Arrow Operator
		case "qa" -> path = "config/config.qa.properties";
		case "uat" -> path = "config/config.uat.properties";
		default -> path = "config/config.qa.properties";
		}
		LOGGER.info("Using the property file from the path: {}", path);

		// Most optimized way to get the config file content
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (input == null) {
			LOGGER.error("Can't find the file at the path: {}", path);
			throw new RuntimeException("Can't find the file at the path:" + path);
		}

		try {
			prop.load(input);
		} catch (FileNotFoundException e) {
			LOGGER.error("Can't find the file at the path: {}", path, e);
			e.printStackTrace();
		} catch (IOException e) {
			LOGGER.error("Something went wrong, please check the file at path: {}", path, e);
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
