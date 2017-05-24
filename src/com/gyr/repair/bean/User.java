package com.gyr.repair.bean;

public class User {
	
	private String iduser;
	private String mobile;
	private String password;
	private String name;
	
	public User(String iduser, String mobile, String password, String name) {
		super();
		this.iduser = iduser;
		this.mobile = mobile;
		this.password = password;
		this.name = name;
	}

	public String getIduser() {
		return iduser;
	}

	public void setIduser(String iduser) {
		this.iduser = iduser;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
