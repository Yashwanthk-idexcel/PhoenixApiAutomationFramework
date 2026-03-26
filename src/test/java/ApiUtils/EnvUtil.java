package ApiUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {
	
	private static Dotenv dotenv;
	private static final Logger LOGGER = LogManager.getLogger(EnvUtil.class);
	
	static {
		LOGGER.info("Loading the dotenv file");
		dotenv = Dotenv.load();
	}

	private EnvUtil() {
		
	}
	
	// Wrapper method
	public static String getValue(String varName) {
		LOGGER.info("Reading the value of {} from the dotenv file", varName);
		return dotenv.get(varName);
	}
}
