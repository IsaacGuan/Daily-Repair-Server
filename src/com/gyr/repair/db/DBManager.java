package com.gyr.repair.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	// ���ص�����    
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    // ���ݿ�URL    
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/repair?useUnicode=true&characterEncoding=utf8";
    // ���ݿ������û���    
    private static final String DATABASE_USERNAME = "root";
    // ���ݿ���������    
    private static final String DATEBASE_PASSWORD = "LIUchuang1115";    //LIUchuang1115
    
    public static Connection getConnection() throws SQLException {    
        try {    
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {    
            e.printStackTrace();    
        }    
        Connection con = DriverManager.getConnection(DATABASE_URL,DATABASE_USERNAME, DATEBASE_PASSWORD);    
        return con;    
    }
}
