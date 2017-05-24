package com.gyr.repair.dao;

public interface OrderDao {

	public String GetOrderlist(String status);

	public String SendOrder(String title, String nameuser, String mobileuser,
			String nameengineer, String mobileengineer, String budget,
			String date, String city, String district, String address,
			String latitude, String longitude, String detail, String status);

	public String TakeOrder(String idorder, String nameengineer,
			String mobileengineer);

	public String GetMySendedOrders(String mobileuser);

	public String GetMyReceivedOrders(String mobileengineer);

	public String CancelOrder(String idorder);

	public String CompleteOrder(String idorder);
	
	public String DeleteOrder(String idorder);
	
	public String ExpireOrder(String date);

}
