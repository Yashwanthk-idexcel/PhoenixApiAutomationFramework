package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.DatabaseManager;
import databasemodel.CustomerAddressDBModel;

public class CustomerAddressTableDao {
	
	private static final Logger LOGGER = LogManager.getLogger(CustomerAddressTableDao.class);

	private static final String CUSTOMER_ADDRESS_QUERY = """
			select apartment_name, flat_number, id, street_name, landmark, area, pincode, country, state
			from tr_customer_address
			where id = ( select tr_customer_address_id
						from tr_customer
						where tr_customer.id = ? );
						                             """;
	
	private CustomerAddressTableDao() {
		
	}

	public static CustomerAddressDBModel getCustomerAddressInfo(int customerID) {

		ResultSet result = null;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		
		CustomerAddressDBModel customerAddressDb = new CustomerAddressDBModel();

		try {
			LOGGER.info("Getting the Connection from the DatabaseManager");
			con = DatabaseManager.getConnection();
			preparedStatement = con.prepareStatement(CUSTOMER_ADDRESS_QUERY);
			preparedStatement.setInt(1, customerID);
			
			LOGGER.info("Executing the SQL Query - {}", CUSTOMER_ADDRESS_QUERY);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				System.out.println("Testing");
				
				customerAddressDb.setId(result.getInt("id"));
				customerAddressDb.setApartment_name(result.getString("apartment_name"));
				customerAddressDb.setFlat_number(result.getString("flat_number"));
				customerAddressDb.setStreet_name(result.getString("street_name"));
				customerAddressDb.setLandmark(result.getString("landmark"));
				customerAddressDb.setArea(result.getString("area"));
				customerAddressDb.setPincode(result.getString("pincode"));
				customerAddressDb.setCountry(result.getString("country"));
				customerAddressDb.setState(result.getString("state"));
				
			}

		} catch (SQLException e) {
			LOGGER.error("Can't convert the Result set to customerAddressDb bean", e);
			e.printStackTrace();
		}
		
		return customerAddressDb;
		
	}

}
