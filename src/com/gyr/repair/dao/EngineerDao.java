package com.gyr.repair.dao;

public interface EngineerDao {

	public String GetEngineerlist();

	public String ApplyEngineer(String mobile, String password, String name,
			String expert, String city, String district, String address,
			String latitude, String longitude);
	
	public String ModifyEngineer(String idengineer, String expert, String city, String district, String address,
			String latitude, String longitude);

	public String RateEngineer(String idengineer, String score);
	
}
