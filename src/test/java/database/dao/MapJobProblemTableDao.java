package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.DatabaseManager;
import databasemodel.CustomerAddressDBModel;
import databasemodel.MapJobProblemModel;

public class MapJobProblemTableDao {

	private static final String PROBLEM_DETAILS_QUERY = """
			select *
			from map_job_problem
			where tr_job_head_id = ?;
						""";
	
	private MapJobProblemTableDao() {
		
	}
	
	public static MapJobProblemModel getProblemDetails(int tr_job_head_id) {
		
		ResultSet result = null;
		PreparedStatement preparedStatement = null;
		Connection con = null;
		MapJobProblemModel problemDetailsDb = new MapJobProblemModel();

		try {

			con = DatabaseManager.getConnection();
			preparedStatement = con.prepareStatement(PROBLEM_DETAILS_QUERY);
			preparedStatement.setInt(1, tr_job_head_id);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				System.out.println("Testing");
				
				problemDetailsDb.setId(result.getInt("id"));
				problemDetailsDb.setMst_problem_id(result.getInt("mst_problem_id"));
				problemDetailsDb.setRemark(result.getString("remark"));
				problemDetailsDb.setTr_job_head_id(result.getInt("tr_job_head_id"));				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return problemDetailsDb;
	}

}
