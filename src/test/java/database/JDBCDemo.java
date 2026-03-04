package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo {

	public static void main(String[] args) throws SQLException {

		Connection conn = DriverManager.getConnection("jdbc:mysql://64.227.160.186:3306/SR_DEV", "srdev_ro_automation",
				"Srdev@123");
		
		Statement statement = conn.createStatement();
		
		ResultSet resultSet = statement.executeQuery("select first_name,last_name,mobile_number from tr_customer;");
		
		while(resultSet.next()) {
			String name = resultSet.getString("first_name");
			String name1 = resultSet.getString("last_name");
			String mob = resultSet.getString("mobile_number");

			
			System.out.println(name + " " + name1 + " " + mob);
		}
	}

}
