package com.gyr.repair.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gyr.repair.dao.UserDao;
import com.gyr.repair.implement.UserDaoImpl;

public class RegisterServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
         
    public RegisterServlet() {  
        super();  
    }  
  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {  
        doPost(request, response);
        response.getWriter().append("Served at: ").append(request.getContextPath());  
    }  
      
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
            throws ServletException, IOException {
    	String responseMsg="FAILED";
        request.setCharacterEncoding("utf-8"); 
        response.setCharacterEncoding("utf-8");
        String mobile = request.getParameter("mobile");  
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        System.out.println("register servlet name:" + name);
        PrintWriter out = response.getWriter();
        
        UserDao userdao = new UserDaoImpl();
        
        responseMsg = userdao.register(mobile, password, name);
        System.out.println("register servlet responseMsg:" + responseMsg);
        out.print(responseMsg);
    }  
}
