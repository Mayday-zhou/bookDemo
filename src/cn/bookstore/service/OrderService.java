package cn.bookstore.service;

import java.sql.Connection;
import java.sql.SQLException;

import cn.bookstore.dao.OrderDao;
import cn.bookstore.dbpool.BaseDao;
import cn.bookstore.pojo.Order;

import cn.pager.PageBean;
import com.alibaba.druid.util.JdbcUtils;
import com.db.durid.DBUtils;


public class OrderService {


	private OrderDao orderDao = new OrderDao();

	/**
	 * 修改订单状态
	 * @param oid
	 * @param status
	 */
	public void updateStatus(String oid, int status) {
		try {
			orderDao.updateStatus(oid, status);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 查询订单状态
	 * @param oid
	 * @return
	 */
	public int findStatus(String oid) {
		try {
			return orderDao.findStatus(oid);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 加载订单
	 * @param oid
	 * @return
	 */
	public Order load(String oid) throws SQLException {


			Order order = orderDao.load(oid);
			return order;

	}
	
	/**
	 * 生成订单
	 * @param order
	 */
	public void createOrder(Order order) {
		try {
	/*		JdbcUtils.beginTransaction();*/
			DBUtils.getDataSource();

			orderDao.add(order);
		//	JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			   /*回滚*/
			//	JdbcUtils.rollbackTransaction();

	}
	
	/**
	 * 我的订单
	 * @param uid
	 * @param pc
	 * @return
	 */
	public PageBean<Order> myOrders(String uid, int pc) throws Exception {

			//	JdbcUtils.beginTransaction();
			PageBean<Order> pb = orderDao.findByUser(uid, pc);
			//	JdbcUtils.commitTransaction();
			return pb;


			//	JdbcUtils.rollbackTransaction();


	}
		/**
		 * 按状态查询
		 * @param status
		 * @param pc
		 * @return
		 */
		public PageBean<Order> findByStatus( int status, int pc) throws Exception {

				//	JdbcUtils.beginTransaction();
				PageBean<Order> pb = orderDao.findByStatus(status,pc);
				//	JdbcUtils.commitTransaction();
				return pb;
				//	JdbcUtils.rollbackTransaction();


		}

		/**
		 * 查询所有
		 * @param pc
		 * @return
		 */
		public PageBean<Order> findAll ( int pc) throws Exception {
				//JdbcUtils.beginTransaction();
				PageBean<Order> pb = orderDao.findAll(pc);
				//	JdbcUtils.commitTransaction();
				return pb;
				//	JdbcUtils.rollbackTransaction();
		}

}