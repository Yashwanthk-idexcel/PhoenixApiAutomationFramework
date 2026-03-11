package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ApiUtils.CreateJobBeanMapper;
import DataProvidersApiBeans.CreateJobBean;
import RequestModel.CreateJobPayload;
import database.dao.CreateJobPayloadDataDao;

public class RunnerToCheckHikariDatabaseManager {

	public static void main(String[] args) throws SQLException {
//		
//		Connection conn = DatabaseManagerHikari.getConnection();
//		System.out.println(conn);
		
	    List<CreateJobBean> beanPayload = CreateJobPayloadDataDao.getCreateJobPayloadData();
	    List<CreateJobPayload> payloadList = new ArrayList<CreateJobPayload>();
	    
	    for (CreateJobBean bean : beanPayload) {
	    	CreateJobPayload payload = CreateJobBeanMapper.mapper(bean);
	    	payloadList.add(payload);
		}
	    
	    System.out.println("------------------------------------------");
	    
	    for (CreateJobPayload payload : payloadList) {
			System.out.println(payload);
		}

	}

}
