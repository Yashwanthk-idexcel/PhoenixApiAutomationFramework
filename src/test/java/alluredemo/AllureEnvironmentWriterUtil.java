package alluredemo;

import java.io.File;
import org.apache.logging.log4j.LogManager;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.Listeners;

import ApiUtils.ConfigManager;

@Listeners(listeners.ApiTestListeners.class)
public class AllureEnvironmentWriterUtil {
	
	private static final Logger LOGGER = LogManager.getLogger(AllureEnvironmentWriterUtil.class);

	public static void creteEnvironmentPropertiesFile() {
		String folderPath = "target/allure-results";
		File file = new File(folderPath);
		file.mkdirs();
		
		Properties prop = new Properties();
		prop.setProperty("Project Name", "Phoenix Test Automation Framework");
		prop.setProperty("Environment", ConfigManager.env);
		prop.setProperty("BASE_URI", ConfigManager.getProperty("BASE_URI"));
		prop.setProperty("Operatin System Name", System.getProperty("os.name"));
		prop.setProperty("Operatin System Version", System.getProperty("os.version"));
		prop.setProperty("Java Version", System.getProperty("java.version"));

		FileWriter fw;
		try {
			fw = new FileWriter(folderPath+"/environment.properties");
			prop.store(fw, "My Environment Properties file");
			LOGGER.info("Created the Environment.properties file at {}", folderPath);
		} catch (IOException e) {
			LOGGER.error("Unable to create the environment.properties file", e);
			e.printStackTrace();
		}
		
	}

}
