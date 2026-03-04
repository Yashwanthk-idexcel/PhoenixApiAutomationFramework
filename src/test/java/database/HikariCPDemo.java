package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import ApiUtils.ConfigManager;

public class HikariCPDemo {

	public static void main(String[] args) throws SQLException {

		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setJdbcUrl(ConfigManager.getProperty("DB_URL"));
		hikariConfig.setUsername(ConfigManager.getProperty("DB_USERNAME"));
		hikariConfig.setPassword(ConfigManager.getProperty("DB_PASSWORD"));
		hikariConfig.setMaximumPoolSize(10);
		hikariConfig.setMinimumIdle(2);
		hikariConfig.setConnectionTimeout(10000);
		hikariConfig.setIdleTimeout(1000);
		hikariConfig.setMaxLifetime(1800000); // 30min
		hikariConfig.setPoolName("Phoenix Test Automation Framework Pool");

		HikariDataSource hikari = new HikariDataSource(hikariConfig);
		Connection conn = hikari.getConnection();
		System.out.println(conn);

		Statement statement = conn.createStatement();
		ResultSet resultSet = statement.executeQuery("select first_name,last_name,mobile_number from tr_customer;");

		while (resultSet.next()) {
			String name = resultSet.getString("first_name");
			String name1 = resultSet.getString("last_name");
			String mob = resultSet.getString("mobile_number");

			System.out.println(name + " " + name1 + " " + mob);
		}
	}

}
