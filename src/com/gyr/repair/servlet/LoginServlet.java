package com.gyr.repair.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyr.repair.dao.UserDao;
import com.gyr.repair.implement.UserDaoImpl;

public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	static int LOGIN_FAILED = 0;  
    static int LOGIN_SUCCEEDED = 1;  
         
    public LoginServlet() {  
        super();  
    }  
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {  
        doPost(request, response);    
        response.getWriter().append("Served at: ").append(request.getContextPath());  
    }  
      
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {
    	String responseMsg = "FAILED";    	
    	request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String mobile = request.getParameter("mobile");
        String password = request.getParameter("password");
    	PrintWriter out = response.getWriter();
    	
    	UserDao userdao = new UserDaoImpl();   
        responseMsg = userdao.login(mobile, password);
        System.out.println("login servlet responseMsg:" + responseMsg);    
        out.print(responseMsg);
    }  
    
}
