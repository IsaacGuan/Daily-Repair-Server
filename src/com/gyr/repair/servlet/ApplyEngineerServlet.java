package com.gyr.repair.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyr.repair.dao.EngineerDao;
import com.gyr.repair.implement.EngineerDaoImpl;

public class ApplyEngineerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ApplyEngineerServlet() {
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
        String name = req.getParameter("name");
        String expert = req.getParameter("expert");  
        String city = req.getParameter("city");
        String district = req.getParameter("district");
        String address = req.getParameter("address");
        String latitude = req.getParameter("latitude");
        String longitude = req.getParameter("longitude");
        System.out.println("ApplyEngineer servlet name:" + name);
        PrintWriter out = resp.getWriter();
        
        EngineerDao engineerDao = new EngineerDaoImpl();
        
        responseMsg = engineerDao.ApplyEngineer(mobile, password, name, expert, city, district, address, latitude, longitude);
        System.out.println("ApplyEngineer servlet responseMsg:" + responseMsg);
        out.print(responseMsg);
	}

}
