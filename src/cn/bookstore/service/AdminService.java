package cn.bookstore.service;

import cn.bookstore.dao.AdminDao;
import cn.bookstore.pojo.Admin;

import java.sql.SQLException;



public class AdminService {
	private AdminDao adminDao = new AdminDao();
	
	/**
	 * 登录功能  返回登录结果
	 */
	public Admin login(Admin admin) {
		try {
			return adminDao.Login(admin.getAdminname(), admin.getAdminpwd());
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
