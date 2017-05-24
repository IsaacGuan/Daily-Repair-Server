package com.gyr.repair.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyr.repair.dao.UserDao;
import com.gyr.repair.implement.UserDaoImpl;

public class ModifyPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyPasswordServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String responseMsg="FAILED";
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String mobile = req.getParameter("mobile");
		String password = req.getParameter("password");
		PrintWriter out = resp.getWriter();
		
		UserDao userDao = new UserDaoImpl();
		responseMsg = userDao.ModifyPassword(mobile, password);
		out.print(responseMsg);
		System.out.println(responseMsg);
		
	}

}
