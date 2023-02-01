package org.soft.base.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.soft.base.dao.AdminDao;
import org.soft.base.model.Admin;
import org.soft.base.util.ExecuteDB;

public class AdminDaoImpl extends ExecuteDB  implements AdminDao {

	@Override
	public Admin loginAdmin(Admin admin) {
		Admin adm = null ;
		String sql = "select * from admin where adminNo=? and adminPass=?" ;
		Object[] objects = {admin.getAdminNo(),admin.getAdminPass()} ;
		ResultSet rs = executeSelect(sql,objects) ;
		try {
			if ( rs.next() ) {
				int adminId = rs.getInt("adminId") ;
				String adminNo = rs.getString("adminNo") ;
				String adminPass = rs.getString("adminPass") ;
				String adminName = rs.getString("adminName") ;
				String adminPhone = rs.getString("adminPhone") ;
				String adminEmail = rs.getString("adminEmail" ) ;
				adm = new Admin(adminId,adminNo,adminPass,adminName,adminPhone,adminEmail) ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeDB(rs,null,null) ;
		}
		return adm;
	}

}
