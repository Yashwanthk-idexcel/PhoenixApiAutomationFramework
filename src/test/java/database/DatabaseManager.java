package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ApiUtils.ConfigManager;

public class DatabaseManager {

	private static final String DB_URL = ConfigManager.getProperty("DB_URL");
	private static final String DB_USERNAME = ConfigManager.getProperty("DB_USERNAME");
	private static final String DB_PASSWORD = ConfigManager.getProperty("DB_PASSWORD");
	private volatile static Connection con;
	// Any update that happens to con will be notified to all the threads, All the
	// threads will be aware of the con variable value

	// Since this is singleton class, object creation outside class is
	// restricted.
	private DatabaseManager() {

	}

	public static void createConnection() throws SQLException {

		if (con == null) { // First check - All Parallel threads will enter
			synchronized (DatabaseManager.class) {
				if (con == null) {
					// This will execute only and once for the first time connection request
					con = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
					System.out.println(con);
				}
			}
		}
	}

}
