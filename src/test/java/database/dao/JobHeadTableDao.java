package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseManager;
import databasemodel.CustomerProductDBModel;
import databasemodel.JobHeadDBModel;

public class JobHeadTableDao {

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
			con = DatabaseManager.getConnection();
			prepareStatement = con.prepareStatement(JOB_HEAD_QUERY);
			prepareStatement.setInt(1, customerID);
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
			e.printStackTrace();
		}

		return jobHeadDbModel;
	}
}
