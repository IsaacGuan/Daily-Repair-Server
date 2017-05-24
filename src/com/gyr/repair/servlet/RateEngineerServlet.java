package com.gyr.repair.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyr.repair.dao.EngineerDao;
import com.gyr.repair.implement.EngineerDaoImpl;

public class RateEngineerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RateEngineerServlet() {
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
		String idengineer = req.getParameter("idengineer");
		String score = req.getParameter("score");
		PrintWriter out = resp.getWriter();
		
		EngineerDao engineerDao = new EngineerDaoImpl();
		responseMsg = engineerDao.RateEngineer(idengineer, score);
        out.print(responseMsg);
		System.out.println(responseMsg);
	}

}
