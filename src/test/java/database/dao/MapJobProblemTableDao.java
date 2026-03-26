package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.DatabaseManager;
import databasemodel.MapJobProblemModel;

public class MapJobProblemTableDao {
	
	private static final Logger LOGGER = LogManager.getLogger(MapJobProblemTableDao.class);

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
			LOGGER.info("Getting the Connection from the DatabaseManager");
			con = DatabaseManager.getConnection();
			preparedStatement = con.prepareStatement(PROBLEM_DETAILS_QUERY);
			preparedStatement.setInt(1, tr_job_head_id);
			
			LOGGER.info("Executing the SQL Query - {}", PROBLEM_DETAILS_QUERY);
			result = preparedStatement.executeQuery();

			while (result.next()) {
				System.out.println("Testing");
				
				problemDetailsDb.setId(result.getInt("id"));
				problemDetailsDb.setMst_problem_id(result.getInt("mst_problem_id"));
				problemDetailsDb.setRemark(result.getString("remark"));
				problemDetailsDb.setTr_job_head_id(result.getInt("tr_job_head_id"));				
			}

		} catch (SQLException e) {
			LOGGER.error("Can't convert the Result set to problemDetailsDb bean", e);
			e.printStackTrace();
		}
		
		return problemDetailsDb;
	}

}
