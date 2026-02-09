package ApiUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManagerOld {
// Program to read the Properties file from src/test/resources/config/config.properties

	private static Properties prop = new Properties();

	private ConfigManagerOld() {
		// The constructor ConfigManager() is not visible - Error will display, if
		// anyone tries to create an object
		// Implementing this to restrict creation of Object of ConfirManager to anywhere
		// outisde this class.
		// This enables user to not create the object of the ConfigManager
	}

	// Operation of loading the property file in memory
	// static block will only executed once during the class loading time
	static {
		FileReader fileReader;
		File configFile = new File(System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "config" + File.separator + "config.properties");

		try {
			fileReader = new FileReader(configFile);
			prop.load(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		return prop.getProperty(key);
	}
}
