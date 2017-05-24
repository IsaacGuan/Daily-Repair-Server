package com.gyr.repair.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gyr.repair.dao.UserDao;
import com.gyr.repair.db.DBManager;

public class UserDaoImpl implements UserDao {
	
	static PreparedStatement preparedStatement = null;
    static ResultSet resultSet = null;
    static int updateRowCnt = 0;
    static String FAILED = "FAILED";
    static String SUCCEEDED = "SUCCEEDED";
    
    private String loginresult;
    private String registerresult;
    private String passwordresult;

	@Override
	public String login(String mobile, String password) {
		loginresult = FAILED;
        String sql1 = "select * from repair.user where mobile = '" + mobile +"' and password = '" + password + "'";
        String sql2 = "select * from repair.engineer where mobile = '" + mobile +"' and password = '" + password + "'";
        try {       
            Connection con = DBManager.getConnection();    
            preparedStatement = con.prepareStatement(sql2);    
            try{    
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                	resultSet.beforeFirst();
                	loginresult = JsonEncap.resultSetToJson(resultSet);
                }    
                preparedStatement.close();
                con.close();
            }catch(Exception e){    
                e.printStackTrace();    
            }    
        }catch(Exception e){    
            e.printStackTrace();    
        }
        if (loginresult.equals(FAILED)) {
        	try {
                Connection con = DBManager.getConnection();    
                preparedStatement = con.prepareStatement(sql1);    
                try {
                    resultSet = preparedStatement.executeQuery();
                    if(resultSet.next()){
                    	resultSet.beforeFirst();
                    	loginresult = JsonEncap.resultSetToJson(resultSet);
                    }    
                    preparedStatement.close();    
                    con.close();    
                }catch(Exception e){    
                    e.printStackTrace();    
                }    
            }catch(Exception e){
                e.printStackTrace();    
            }
        }
        return loginresult;
	}

	@Override
	public String register(String mobile, String password, String name) {
		registerresult = FAILED;
        updateRowCnt = 0;  
        String sql = "insert into repair.user(`iduser`, `mobile`, `password`, `name`) values ('u"  
                + mobile + "', '" + mobile + "', '" + password + "', '" + name + "')";  
        try {       
            Connection con = DBManager.getConnection();    
            preparedStatement = con.prepareStatement(sql);
            try{    
                updateRowCnt = preparedStatement.executeUpdate();
                if(updateRowCnt != 0){
                	registerresult = SUCCEEDED;
                	//System.out.println(" name:" + resultSet.getString("name") + " --register");
                }    
                preparedStatement.close();
                con.close();
            }catch(Exception e){    
                e.printStackTrace();    
            }    
        }catch(Exception e){    
            e.printStackTrace();    
        }    
        System.out.println("register service result:" + registerresult);
        return registerresult;   
	}

	@Override
	public String ModifyPassword(String mobile, String password) {
		passwordresult = FAILED;
		updateRowCnt = 0;
		String sql1 = "update repair.user set password = '" + password + "' where mobile = '" + mobile + "'";
		String sql2 = "update repair.engineer set password = '" + password + "' where mobile = '" + mobile + "'";
		
		try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql2);
			updateRowCnt = preparedStatement.executeUpdate();
			if (updateRowCnt != 0) {
				passwordresult = SUCCEEDED;
			}
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql1);
			updateRowCnt = preparedStatement.executeUpdate();
			if (updateRowCnt != 0) {
				passwordresult = SUCCEEDED;
			}
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("modifypassword service result:" + passwordresult);
		return passwordresult;
	}

}
