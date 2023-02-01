package org.soft.base.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExecuteDB extends Utils {
	private static Connection con = null;
	private static PreparedStatement pstmt = null;

	/**
	 * 
	 * Object[] objects = {humanId�? , humanEmail的�?�}
	 * 
	 * @param sql     SQL语句 例如�? select * from human where humanId = ? and humanEmail
	 *                = ?
	 * @param objects 和SQL语句中问号一�?对应的�?�，这些值会按照顺序存储在数组中 Object[] objects = {humanId�? ,
	 *                humanEmail的�?�} 数组有几个�?�，SQL语句就有几个问号
	 * @return
	 */
	public boolean executeInsertUpdateDelete(String sql, Object[] objects) {
		boolean b = false;
		con = getConnection();
		pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					// 给问号赋�?
					pstmt.setObject(i + 1, objects[i]);
				}
			}
			int i = pstmt.executeUpdate();
			if (i > 0) {
				b = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(null, pstmt, null);
		}
		return b;
	}

	/**
	 * 封装查询
	 * 
	 * @param sql
	 * @param objects
	 * @return
	 */
	public ResultSet executeSelect(String sql, Object[] objects) {
		ResultSet rs = null;
		con = getConnection();
		pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					// 给问号赋�?
					pstmt.setObject(i + 1, objects[i]);
				}
			}
			rs = pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void closeQueryConnection(ResultSet rs) {
		closeDB(rs, pstmt, null);
	}
}
