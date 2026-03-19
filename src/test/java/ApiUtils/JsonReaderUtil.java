package ApiUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReaderUtil {

	private static final Logger LOGGER = LogManager.getLogger(JsonReaderUtil.class);

	public static <T> Iterator<T> loadJSON(String filePath, Class<T[]> clazz) {

		LOGGER.info("Reading the Test data from json file {}", filePath);

		// Demo.json file needs to be read -> test-data\\LoginApiTestData.json
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

		// Convert Json object into the Java Object - Deserialization => Help of Jackson
		// Databind library [ObjectMapper Class]
		ObjectMapper objMap = new ObjectMapper();

		// This create an Java Object of Type UserCredentails class - record - Already
		// created to map username and password from Json File
		T[] obj = null;
		List<T> objList = null;

		try {
			LOGGER.info("Converting the Json data to the Bean class {}", clazz);

			obj = objMap.readValue(inputStream, clazz);

			// Converting to List -> So that i can introduce Iterator over List
			objList = Arrays.asList(obj);

		} catch (IOException e) {
			LOGGER.error("Cannot read the Json from the file {}", filePath, e);
			e.printStackTrace();
		}

		// Converted List as iterator; -> Easily send this to DataProvider method
		return objList.iterator();
	}

}
