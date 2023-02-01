package org.soft.base.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Utils {
	private String url = "jdbc:mysql://localhost:3307/a5db?useSSL=false&serverTimezone=UTC";
	private String user = "root";
	private String password = "root";
	private static Connection con = null;  //保证连接对象唯一，利于进行事务管理
	
	
	//静�?�块中的代码无论实例化多少次，都只执行一�? 
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public Utils() {
		
	}
	
	/**
	 * 返回连接成功的数据库连接
	 * @return
	 */
	public Connection getConnection() {
		
		try {
			if ( con == null ) {
				//如果con为null，创建连接，否则不创建
				con = DriverManager.getConnection(url, user, password);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	/**
	 * 关闭数据库相关连接，先判断是否为null，不为null则关闭相关连接�??
	 * @param rs
	 * @param pstmt
	 * @param con
	 */
	public void closeDB(ResultSet rs , PreparedStatement pstmt , Connection con) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
