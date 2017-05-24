package com.gyr.repair.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyr.repair.dao.OrderDao;
import com.gyr.repair.implement.OrderDaoImpl;

public class GetOrderlistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetOrderlistServlet() {
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
		String status = req.getParameter("status");
		PrintWriter out = resp.getWriter();
		
		OrderDao orderDao = new OrderDaoImpl();
		responseMsg = orderDao.GetOrderlist(status);
		out.print(responseMsg);
		System.out.println(responseMsg);
	}

}
