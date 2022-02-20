package cn.bookstore.dao;

import cn.bookstore.dbpool.BaseDao;
import cn.bookstore.pojo.Admin;
import com.alibaba.druid.stat.JdbcResultSetStat;
import com.db.durid.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;


public class AdminDao {
	private QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
	/**
	 * 通过管理员登录名和登录密码查询
	 */
	public Admin Login(String adminname, String adminpwd) throws SQLException {
		String sql = "select * from t_admin where adminname=? and adminpwd=?";
		return qr.query(sql, new BeanHandler<Admin>(Admin.class), adminname, adminpwd);
	}
}
