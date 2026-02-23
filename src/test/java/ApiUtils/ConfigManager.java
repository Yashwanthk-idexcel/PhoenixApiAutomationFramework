package ApiUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
// Program to read the Properties file from src/test/resources/config/config.properties

	private static Properties prop = new Properties();
	private static String path = "config/config.qa.properties";
	private static String env;

	private ConfigManager() {
	}

	// Operation of loading the property file in memory
	// static block will only executed once during the class loading time
	static {

		env = System.getProperty("env", "qa").toLowerCase().trim();

		switch (env) {
		case "dev" -> path = "config/config.dev.properties"; // Arrow Operator
		case "qa" -> path = "config/config.qa.properties";
		case "uat" -> path = "config/config.uat.properties";
		default -> path = "config/config.qa.properties";
		}

		// Most optimized way to get the config file content
		InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);

		if (input == null)
			throw new RuntimeException("Can't find the file at the path:" + path);

		try {
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
