package cn.bookstore.admin.controller;

import cn.bookstore.dbpool.BaseServlet;
import cn.bookstore.dbpool.Utils.CommonUtils;
import cn.bookstore.pojo.Admin;
import cn.bookstore.service.AdminService;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminServlet extends BaseServlet {
	private AdminService adminService = new AdminService();
	/**
	 * 登录功能
	 */
	public String login(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//将map-->转换成实体类对象（Dto）

		/*
		 * 1. 封装表单数据到Admin
		 * 实现自动将Map转换成对象，借助第三方工具commons-beanutils-1.8.0.jar+commons-logging.jar实现，
		 * 需要遵守的规则创建的对象的属性名必须与map的key相同。
		 */
		Admin form = CommonUtils.toBean(req.getParameterMap(), Admin.class);
		Admin admin = adminService.login(form);
		if(admin == null) {
			req.setAttribute("msg", "用户名或密码错误！");
			return "/adminjsps/login.jsp";
		}
		req.getSession().setAttribute("admin", admin);
		return "r:/adminjsps/admin/index.jsp";
	}
}
