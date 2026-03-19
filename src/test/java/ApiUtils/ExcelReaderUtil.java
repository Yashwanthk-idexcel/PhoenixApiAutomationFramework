package ApiUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.poiji.bind.Poiji;

public class ExcelReaderUtil {

	private static final Logger LOGGER = LogManager.getLogger(ExcelReaderUtil.class);

	private ExcelReaderUtil() {

	}

	public static <T> Iterator<T> loadExcelData(String filePath, String SheetName, Class<T> clazz) {
		LOGGER.info("Reading the Test data from xlsx file {} and sheet name is {}", filePath, SheetName);

		// Apache poi ooxml Library
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);

		XSSFWorkbook myWorkBook = null;

		try {
			myWorkBook = new XSSFWorkbook(is);
		} catch (IOException e) {
			LOGGER.error("Can't read the excel file {}", filePath, e);
			e.printStackTrace();
		}

		XSSFSheet mySheet = myWorkBook.getSheet(SheetName);

		LOGGER.info("Converting the XFFSsheeet {} to POJO class of type {}", SheetName, clazz);

		List<T> details = Poiji.fromExcel(mySheet, clazz);
		return details.iterator();
	}

}
