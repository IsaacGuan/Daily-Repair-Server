package com.gyr.repair.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyr.repair.dao.OrderDao;
import com.gyr.repair.implement.OrderDaoImpl;

public class SendOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SendOrderServlet() {
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
        String title = req.getParameter("title");
        String nameuser = req.getParameter("nameuser");
        String mobileuser = req.getParameter("mobileuser");
        String nameengineer = req.getParameter("nameengineer");
        String mobileengineer = req.getParameter("mobileengineer");
        String budget = req.getParameter("budget");
        String date = req.getParameter("date");
        String city = req.getParameter("city");
        String district = req.getParameter("district");
        String address = req.getParameter("address");
        String latitude = req.getParameter("latitude");
        String longitude = req.getParameter("longitude");
		String detail = req.getParameter("detail");
		String status = req.getParameter("status");
		PrintWriter out = resp.getWriter();
		
		OrderDao orderDao = new OrderDaoImpl();
		responseMsg = orderDao.SendOrder(title, nameuser, mobileuser, nameengineer, mobileengineer, 
				budget, date, city, district, address, latitude, longitude, detail, status);
		out.print(responseMsg);
		System.out.println(responseMsg);
	}

}
