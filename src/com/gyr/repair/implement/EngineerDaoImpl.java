package com.gyr.repair.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gyr.repair.dao.EngineerDao;
import com.gyr.repair.db.DBManager;

public class EngineerDaoImpl implements EngineerDao {

	static PreparedStatement preparedStatement = null;
	static ResultSet resultSet = null;
	static int updateRowCnt = 0;
	static String FAILED = "FAILED";
	static String SUCCEEDED = "SUCCEEDED";

	private String engineerlistresult;
	private String applyresult;
	private String rateresult;
	private String modifyresult;

	@Override
	public String GetEngineerlist() {
		engineerlistresult = FAILED;
		String sql = "select * from repair.engineer";
		try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql);
			try {
				resultSet = preparedStatement.executeQuery();
				engineerlistresult = JsonEncap.resultSetToJson(resultSet);
				preparedStatement.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return engineerlistresult;
	}

	@Override
	public String ApplyEngineer(String mobile, String password, String name,
			String expert, String city, String district, String address,
			String latitude, String longitude) {
		applyresult = FAILED;
		updateRowCnt = 0;
		String sql = "insert into repair.engineer(`idengineer`, `mobile`, `password`, `name`, "
				+ "`expert`, `city`, `district`, `address`, `latitude`, `longitude`, `score`, `ordernumber`) values ('e"
				+ mobile
				+ "', '"
				+ mobile
				+ "', '"
				+ password
				+ "', '"
				+ name
				+ "', '"
				+ expert
				+ "', '"
				+ city
				+ "', '"
				+ district
				+ "', '"
				+ address
				+ "', '"
				+ latitude
				+ "', '"
				+ longitude
				+ "', '"
				+ 0
				+ "', '" + 0 + "')";
		try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql);
			try {
				updateRowCnt = preparedStatement.executeUpdate();
				if (updateRowCnt != 0) {
					applyresult = SUCCEEDED;
				}
				preparedStatement.close();
				con.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("apply service result:" + applyresult);
		return applyresult;
	}

	@Override
	public String RateEngineer(String idengineer, String score) {
		boolean flag = false;
		rateresult = FAILED;
		updateRowCnt = 0;
		String sql1 = "update repair.engineer set ordernumber = ordernumber + 1 where idengineer = '"
				+ idengineer + "'";
		String sql2 = "update repair.engineer set score = ( score * ( ordernumber - 1 ) + "
				+ score
				+ " ) / ordernumber where idengineer = '"
				+ idengineer
				+ "'";
		try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql1);
			updateRowCnt = preparedStatement.executeUpdate();
			if (updateRowCnt != 0) {
				flag = true;
				updateRowCnt = 0;
			}
			if (flag) {
				preparedStatement = con.prepareStatement(sql2);
				updateRowCnt = preparedStatement.executeUpdate();
				if (updateRowCnt != 0) {
					rateresult = SUCCEEDED;
				}
			}
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("rateengineer service result:" + rateresult);
		return rateresult;
	}

	@Override
	public String ModifyEngineer(String idengineer, String expert, String city,
			String district, String address, String latitude, String longitude) {
		modifyresult = FAILED;
		updateRowCnt = 0;
		String sql = "update repair.engineer set expert = '" + expert
				+ "', city = '" + city + "', district = '" + district
				+ "', address = '" + address + "', latitude = '" + latitude
				+ "', longitude = '" + longitude + "' where idengineer = '" + idengineer + "'";
		try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql);
			updateRowCnt = preparedStatement.executeUpdate();
			if (updateRowCnt != 0) {
				modifyresult = SUCCEEDED;
			}
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("modifyengineer service result:" + modifyresult);
		return modifyresult;
	}

}
