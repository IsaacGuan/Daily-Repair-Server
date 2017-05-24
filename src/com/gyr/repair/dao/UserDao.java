package com.gyr.repair.dao;

public interface UserDao {
	
	public String login(String mobile, String password);
	public String register(String mobile, String password, String name);
	
	public String ModifyPassword(String mobile, String password);

}
