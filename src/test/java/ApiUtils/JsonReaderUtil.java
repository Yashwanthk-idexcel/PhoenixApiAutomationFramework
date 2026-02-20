package ApiUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import RequestModel.UserCredentials;

public class JsonReaderUtil {

	public static <T> Iterator<T> loadJSON(String filePath, Class<T[]> clazz) {

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
			obj = objMap.readValue(inputStream, clazz);

			// Converting to List -> So that i can introduce Iterator over List
			objList = Arrays.asList(obj);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Converted List as iterator; -> Easily send this to DataProvider method
		return objList.iterator();
	}

}
