package com.lllr.db;

import java.sql.*;
public class DBUtil {

	private static final String USER = "root";
	private static final String PASS="abc89895051";
	private static final String URL="jdbc:mysql://localhost/fortest";
	
	private static Connection conn = null;
	static
	{
		//1.注册驱动程序
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//2.连接数据库
		try {
			conn = DriverManager.getConnection(URL,USER,PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//提供getter方法让外部获取数据库连接
	public static Connection getConnection() {
		return conn;
	}
}
