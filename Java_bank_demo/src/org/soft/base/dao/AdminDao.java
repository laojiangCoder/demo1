package org.soft.base.dao;

import org.soft.base.model.Admin;

public interface AdminDao {
	
	/**
	 * 管理员登录
	 * @param admin 包括管理员号和管理员密码
	 * @return 登录成功，返回管理员对象，否则返回null
	 */
	public Admin loginAdmin(Admin admin) ;

}
