package ApiUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class DateTimeUtil {

	private DateTimeUtil() {
		//Private constructor, to restrict the object creation
	}

	public static String getPreviousDateByDays(int days) {
		return Instant.now().minus(days, ChronoUnit.DAYS).toString();
	}
}
