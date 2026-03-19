package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.DatabaseManager;
import databasemodel.CustomerDBModel;

public class CustomerTableDao {

	// Executing query for tr_customer table! which will get the details of the
	// customer
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerTableDao.class);

	private static final String CUSTOMER_DETAILS_QUERY = """
			SELECT *
			FROM tr_customer
			WHERE tr_customer.id = ? ;
						""";
	
	private CustomerTableDao() {
		
	}

	public static CustomerDBModel getCustomerInfo(int customerID) {

		ResultSet result = null;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		CustomerDBModel customerDbModel = new CustomerDBModel();

		try {

			LOGGER.info("Getting the Connection from the DatabaseManager");
			con = DatabaseManager.getConnection();
			preparedStatement = con.prepareStatement(CUSTOMER_DETAILS_QUERY);
			preparedStatement.setInt(1, customerID);
			
			LOGGER.info("Executing the SQL Query - {}", CUSTOMER_DETAILS_QUERY);
			result = preparedStatement.executeQuery();

			while (result.next()) {				
				customerDbModel.setId(result.getInt("id"));
				customerDbModel.setFirst_name(result.getString("first_name"));
				customerDbModel.setLast_name(result.getString("last_name"));
				customerDbModel.setMobile_number(result.getString("mobile_number"));
				customerDbModel.setMobile_number_alt(result.getString("mobile_number_alt"));
				customerDbModel.setEmail_id(result.getString("email_id"));
				customerDbModel.setEmail_id_alt(result.getString("email_id_alt"));
				customerDbModel.setTr_customer_address_id(result.getInt("tr_customer_address_id"));
								
//				new CustomerDBModel(result.getString("first_name"), result.getString("last_name"),
//						result.getString("mobile_number"), result.getString("mobile_number_alt"),
//						result.getString("email_id"), result.getString("email_id_alt"));
			}

		} catch (SQLException e) {
			LOGGER.error("Can't convert the Result set to CustomerDBModel bean", e);
			e.printStackTrace();
		}
		
		return customerDbModel;

	}

}
