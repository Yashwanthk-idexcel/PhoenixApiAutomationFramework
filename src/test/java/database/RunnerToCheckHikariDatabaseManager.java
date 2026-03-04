package database;

import java.sql.Connection;
import java.sql.SQLException;

public class RunnerToCheckHikariDatabaseManager {

	public static void main(String[] args) throws SQLException {
		
		Connection conn = DatabaseManagerHikari.getConnection();
		System.out.println(conn);

	}

}
