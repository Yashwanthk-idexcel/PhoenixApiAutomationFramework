package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseManager;
import databasemodel.CustomerProductDBModel;

public class CustomerProductTableDao {

	private static final String CUSTOMER_DETAILS_QUERY = """
			select *
			from tr_customer_product 
			where tr_customer_id = (select id from tr_customer where tr_customer.id = ?);
			""";
	
	private CustomerProductTableDao() {
		
	}
	
	public static CustomerProductDBModel getCustomerProductInfo(int customerID) {
		Connection con;
		PreparedStatement prepareStatement;
		ResultSet resultSet;
		CustomerProductDBModel customerProductDbModel = new CustomerProductDBModel();
		
		try {
			con = DatabaseManager.getConnection();
			prepareStatement = con.prepareStatement(CUSTOMER_DETAILS_QUERY);
			prepareStatement.setInt(1, customerID);
			resultSet = prepareStatement.executeQuery();
			
			while(resultSet.next()) {
				
				customerProductDbModel.setId(resultSet.getInt("id"));
				customerProductDbModel.setTr_customer_id(resultSet.getInt("tr_customer_id"));
				customerProductDbModel.setMst_model_id(resultSet.getInt("mst_model_id"));
				customerProductDbModel.setDop(resultSet.getString("dop"));
				customerProductDbModel.setPopurl(resultSet.getString("popurl"));
				customerProductDbModel.setImei1(resultSet.getString("imei1"));
				customerProductDbModel.setImei2(resultSet.getString("imei2"));
				customerProductDbModel.setSerial_number(resultSet.getString("serial_number"));	
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customerProductDbModel;
	}
}
