package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.DatabaseManager;
import databasemodel.JobHeadDBModel;

public class JobHeadTableDao {
	
	private static final Logger LOGGER = LogManager.getLogger(JobHeadTableDao.class);

	private static final String JOB_HEAD_QUERY = """
			select *
			from tr_job_head
			where tr_customer_id = ?;
				""";

	private JobHeadTableDao() {

	}

	public static JobHeadDBModel getJobHeadInfo(int customerID) {
		Connection con;
		PreparedStatement prepareStatement;
		ResultSet resultSet;
		JobHeadDBModel jobHeadDbModel = new JobHeadDBModel();

		try {
			LOGGER.info("Getting the Connection from the DatabaseManager");

			con = DatabaseManager.getConnection();
			prepareStatement = con.prepareStatement(JOB_HEAD_QUERY);
			prepareStatement.setInt(1, customerID);
			
			LOGGER.info("Executing the SQL Query - {}", JOB_HEAD_QUERY);
			resultSet = prepareStatement.executeQuery();

			while (resultSet.next()) {
				jobHeadDbModel.setId(resultSet.getInt("id"));
				jobHeadDbModel.setJob_number(resultSet.getString("job_number"));
				jobHeadDbModel.setTr_customer_id(resultSet.getInt("tr_customer_id"));
				jobHeadDbModel.setTr_customer_product_id(resultSet.getInt("tr_customer_product_id"));
				jobHeadDbModel.setMst_service_location_id(resultSet.getInt("mst_service_location_id"));
				jobHeadDbModel.setMst_platform_id(resultSet.getInt("mst_platform_id"));
				jobHeadDbModel.setMst_warrenty_status_id(resultSet.getInt("mst_warrenty_status_id"));
				jobHeadDbModel.setMst_oem_id(resultSet.getInt("mst_oem_id"));
			}

		} catch (SQLException e) {
			LOGGER.error("Can't convert the Result set to jobHeadDbModel bean", e);
			e.printStackTrace();
		}

		return jobHeadDbModel;
	}
}
