package com.lllr.UserDao;

import com.lllr.db.DBUtil;
import com.lllr.model.User;
import java.sql.*;
import java.util.*;
public class UserDao {

	//增
	public void addUser(User usr) throws SQLException {
		
		//1.获得数据库连接
		//System.out.println("Connecting to database...");
	   Connection conn = DBUtil.getConnection();
	   
	   String sql = "insert into user" + "(username,mobile,create_date,create_user)" + " values(?,?,current_date(),?)";
	   
	   //2.执行数据库操作
	   PreparedStatement ptmt = conn.prepareStatement(sql);
	   
	   ptmt.setString(1,usr.getUsername());
	   ptmt.setString(2, usr.getMobile());
	   ptmt.setString(3,usr.getCreate_user());
	   
	   ptmt.execute();
	   
	   System.out.println("Successfully add to database!");
	}
	
	//删
	public void delUser(Integer id) throws SQLException {

				System.out.println("Connecting to database...");
				Connection conn = DBUtil.getConnection();
				
				String sql = "delete from user"+" where id=?";
				
				PreparedStatement ptmt = conn.prepareStatement(sql);
				ptmt.setInt(1, id);
				
				ptmt.execute();
				//System.out.println("Successfully delete from database!");
	}
	
	//改
	public void updateUser(User usr) throws SQLException {
	//	System.out.println("Connecting to database...");
		Connection conn = DBUtil.getConnection();
		
		String sql = "update user"+" set username=?,mobile=?"+   " where id=?";
		
	
		PreparedStatement ptmt = conn.prepareStatement(sql);
		ptmt.setString(1, usr.getUsername());
		ptmt.setString(2, usr.getMobile());
		ptmt.setInt(3, usr.getId());
		ptmt.execute();
	//	System.out.println("Successfully update table !");
	}
	
	//查1
	public List<User> getAllUser() throws SQLException {
		
		//System.out.println("Connecting to database...");
		Connection conn = DBUtil.getConnection();
		String sql = "select * from user";
		List<User> usrList = new ArrayList<User>();
		User usr = null;
	
		PreparedStatement ptmt = conn.prepareStatement(sql);
        ResultSet rs = ptmt.executeQuery();
        while(rs.next())
        {
        	usr = new User();
        	usr.setId(rs.getInt("id"));
        	usr.setUsername(rs.getString("username"));
        	usr.setMobile(rs.getString("mobile"));
        	usrList.add(usr);
        }
        
       // System.out.println("Successfully get all users from database !");
        return usrList;
		
	}
	
	//查2
public User queryUser(List<Map<String,Object>> params) throws SQLException {
		
		//System.out.println("Connecting to database...");
		Connection conn = DBUtil.getConnection();
        StringBuilder sb = new StringBuilder();
        sb.append("select * from user where 1=1 ");
        
        
        if(params != null && params.size()>0)
        {
        	for(int i=0;i<params.size();i++)
        	{
        		Map<String,Object>  param = params.get(i);
        		sb.append(" and " + param.get("name")+" " +param.get("rela") + " " + param.get("value"));
        	}
        }
		User usr = null;
	
		PreparedStatement ptmt = conn.prepareStatement(sb.toString());
        ResultSet rs = ptmt.executeQuery();
        while(rs.next())
        {
        	usr = new User();
        	usr.setId(rs.getInt("id"));
        	usr.setUsername(rs.getString("username"));
        	usr.setMobile(rs.getString("mobile"));
        }
        
        //System.out.println("Successfully get the users from database !");
        return usr;
		
	}
}
