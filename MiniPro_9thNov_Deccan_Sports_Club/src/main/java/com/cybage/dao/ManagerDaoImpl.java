package com.cybage.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.cybage.pojos.AllBatchInfo;
import com.cybage.pojos.Batch;
import com.cybage.pojos.Sports;
import com.cybage.pojos.Users;

public class ManagerDaoImpl implements ManagerDaoI{
	

	public ManagerDaoImpl()
	{}



	
	public String getRole(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	//////////////////////Batch tables CRUD operations

	
	public List<AllBatchInfo> getAllBatches() throws Exception {
		String sql = "select b.batchId,b.startDate,b.endDate,b.batchSize,s.sportName, s.sportId from batch b inner join Sports s on  s.sportId=b.sportId";
		Connection con = DbUtil.getConnection();		
		PreparedStatement ps = con.prepareStatement(sql);
		List<AllBatchInfo>list=new ArrayList<AllBatchInfo>();
		ResultSet rs= ps.executeQuery();
		while(rs.next()){
			
			//System.out.println(rs.getInt("batchId")+" "+rs.getDate("startDate")+" "+rs.getDate("endDate")+" "+rs.getInt("batchSize")+" "+rs.getString("sportName"));
			list.add(new AllBatchInfo(rs.getInt(1),rs.getDate(2),rs.getDate(3),rs.getInt(4),rs.getString(5),rs.getInt(6)));
			
		}
			return list;	
	}


	public int addBatch(Batch batch) throws Exception {
		String sql = "insert into batch(startDate,endDate ,batchSize,sportId) values(?,?,?,?)";
		Connection con = DbUtil.getConnection();		
		PreparedStatement ps = con.prepareStatement(sql);
			
		ps.setDate(1, batch.getStartDate());
		ps.setDate(2, batch.getEndDate());
		ps.setInt(3, batch.getBatchSize());
		ps.setInt(4, batch.getSportId());
		//ps.setInt(5, batch.getBatchId());
		int rowAffected= ps.executeUpdate();
		if(rowAffected==1)
		{
			return rowAffected;
		}
		
		else {
			
			throw new Exception("Could not insert batch record");
		}
	}
	
	//

	public Batch getBatch(int batchId) throws Exception{
		String sql = "select * from batch where batchId = ?";
		Connection connection = DbUtil.getConnection();
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, batchId);
		ResultSet rs = ps.executeQuery();
		Batch  batch = null;
		if(rs.next()) {
			batch = new Batch(rs.getInt(1), rs.getDate(2),rs.getDate(3), rs.getInt(4),rs.getInt(5));
		}
		return batch;
	}

	//to delete a record from Batch table
	
	public int removeBatch(int batchId) throws Exception {
		String sql = "delete from Batch where batchId= ?";
		Connection con = DbUtil.getConnection();		
		PreparedStatement ps = con.prepareStatement(sql);
			
		
		ps.setInt(1, batchId);		
		
		int rowAffected= ps.executeUpdate();
		if(rowAffected==1)
		{
			return rowAffected;
		}
		
		else {
			
			throw new Exception("Could not delete batch record");
		}
	}
	
	
	///update record in Batch table
	public int updateBatch(Batch batch) throws Exception {
		String sql = "update Batch SET startDate=? ,endDate=?,batchSize=?,sportId=?  where batchId=? ";
		Connection con = DbUtil.getConnection();		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setDate(1, batch.getStartDate());
		ps.setDate(2,batch.getEndDate() );
		ps.setInt(3, batch.getBatchSize());
		ps.setInt(4,batch.getSportId());
		
		ps.setInt(5,batch.getBatchId());
		
		int rowAffected= ps.executeUpdate();
		if(rowAffected==1)
		{
			return rowAffected;
		}
		
		else {
			
			throw new Exception("Could not update Batch tables record");
		}
	}
	
////////////////////Sports table CRUD operations
	
	public int addSports(String sportName) throws Exception {
		String sql = "insert into Sports(sportName) values(?)";
		Connection con = DbUtil.getConnection();		
		PreparedStatement ps = con.prepareStatement(sql);
	
		ps.setString(1, sportName);
		int rowAffected= ps.executeUpdate();
		if(rowAffected==1)
		{
			return rowAffected;
		}
		
		else {
			
			throw new Exception("Could not insert Sports record");
		}
	}


	public int removeSports(String sportName) throws Exception {
		String sql = "delete from Sports where sportName=?";
		Connection con = DbUtil.getConnection();		
		PreparedStatement ps = con.prepareStatement(sql);
		String sportName1=sportName.toUpperCase(); 
		ps.setString(1, sportName1);
		int rowAffected= ps.executeUpdate();
		if(rowAffected==1)
		{
			return rowAffected;
		}
		
		else {
			
			throw new Exception("Could not delete Sports record");
		}
	}

	

}
