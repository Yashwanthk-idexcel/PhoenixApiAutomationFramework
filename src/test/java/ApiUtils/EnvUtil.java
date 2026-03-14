package ApiUtils;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvUtil {
	
	private static Dotenv dotenv;
	
	static {
		dotenv = Dotenv.load();
	}

	private EnvUtil() {
		
	}
	
	// Wrapper method
	public static String getValue(String varName) {
		return dotenv.get(varName);
	}
}
