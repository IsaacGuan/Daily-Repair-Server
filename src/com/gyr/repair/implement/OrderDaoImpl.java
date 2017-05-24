package com.gyr.repair.implement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.gyr.repair.dao.OrderDao;
import com.gyr.repair.db.DBManager;

public class OrderDaoImpl implements OrderDao {
	
	static PreparedStatement preparedStatement = null;
	static ResultSet resultSet = null;
	static int updateRowCnt = 0;
	static String FAILED = "FAILED";
	static String SUCCEEDED = "SUCCEEDED";
	
	private String orderlistresult;
	private String sendorderresult;
	private String takeorderresult;
	private String mysendedordersresult;
	private String myreceivedordersresult;
	private String cancelorderresult;
	private String completeorderresult;
	private String deleteorderresult;
	private String expireorderresult;

	@Override
	public String GetOrderlist(String status) {
		orderlistresult = FAILED;
		String sql = "select * from repair.order where status = '" + status +"' order by date";
		try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql);
			try {
				resultSet = preparedStatement.executeQuery();
				orderlistresult = JsonEncap.resultSetToJson(resultSet);
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
		System.out.println("GetOrderlist service result:" + orderlistresult);
		return orderlistresult;
	}

	@Override
	public String SendOrder(String title, String nameuser, String mobileuser,
			String nameengineer, String mobileengineer, String budget,
			String date, String city, String district, String address,
			String latitude, String longitude, String detail, String status) {
		sendorderresult = FAILED;
		updateRowCnt = 0;
		Calendar calendar = Calendar.getInstance();
		String idorder = String.valueOf(calendar.get(Calendar.YEAR)) + String.valueOf(calendar.get(Calendar.MONTH)+1)
				+ String.valueOf(calendar.get(Calendar.DATE)) + String.valueOf(calendar.get(Calendar.MINUTE))
				+ String.valueOf(calendar.get(Calendar.SECOND)) + mobileuser;
        String sql = "insert into repair.order(`idorder`, `title`, `nameuser`, `mobileuser`, `nameengineer`, "
        		+ "`mobileengineer`, `budget`, `date`, `city`, `district`, `address`, `latitude`, `longitude`, `detail`, `status`) values ('"  
        		+ idorder + "', '" + title + "', '" + nameuser + "', '" + mobileuser + "', '" + nameengineer + "', '"
        		+ mobileengineer + "', '" + budget + "', '" + date + "', '" + city + "', '" + district + "', '" 
        		+ address + "', '" + latitude + "', '" + longitude + "', '" + detail + "', '" + status + "')";
        try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql);
			try {
				updateRowCnt = preparedStatement.executeUpdate();
				if(updateRowCnt != 0){
					sendorderresult = SUCCEEDED;
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
        System.out.println("sendorder service result:" + sendorderresult);
		return sendorderresult;
	}

	@Override
	public String TakeOrder(String idorder, String nameengineer,
			String mobileengineer) {
		takeorderresult = FAILED;
		updateRowCnt = 0;
		String sql1 = "select * from repair.order where idorder = '" + idorder + "' and status = 'unreceived'";
		String sql2 = "update repair.order set nameengineer = '" + nameengineer 
				+ "', mobileengineer = '" + mobileengineer + "', status = 'received' "
				+ "where idorder='" + idorder + "';";
		try {       
            Connection con = DBManager.getConnection();    
            preparedStatement = con.prepareStatement(sql1);    
            try{    
                resultSet = preparedStatement.executeQuery();
                if(resultSet.next()){
                	resultSet.beforeFirst();
                	takeorderresult = JsonEncap.resultSetToJson(resultSet);
                }    
                preparedStatement.close();
                con.close();
            }catch(Exception e){    
                e.printStackTrace();    
            }    
        }catch(Exception e){    
            e.printStackTrace();    
        }
		if (!takeorderresult.equals(FAILED)) {
			try {
				Connection con = DBManager.getConnection();
				preparedStatement = con.prepareStatement(sql2);
				try {
					updateRowCnt = preparedStatement.executeUpdate();
					if (updateRowCnt != 0) {
						takeorderresult = SUCCEEDED;
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
		}
		System.out.println("takeorder service result:" + takeorderresult);
		return takeorderresult;
	}

	@Override
	public String GetMySendedOrders(String mobileuser) {
		mysendedordersresult = FAILED;
		String sql = "select * from repair.order where mobileuser = '" + mobileuser +"' order by date";
		try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql);
			try {
				resultSet = preparedStatement.executeQuery();
				mysendedordersresult = JsonEncap.resultSetToJson(resultSet);
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
		System.out.println("GetMySendedOrders service result:" + mysendedordersresult);
		return mysendedordersresult;
	}

	@Override
	public String GetMyReceivedOrders(String mobileengineer) {
		myreceivedordersresult = FAILED;
		String sql = "select * from repair.order where mobileengineer = '" + mobileengineer +"' order by date";
		try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql);
			try {
				resultSet = preparedStatement.executeQuery();
				myreceivedordersresult = JsonEncap.resultSetToJson(resultSet);
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
		System.out.println("GetMySendedOrders service result:" + myreceivedordersresult);
		return myreceivedordersresult;
	}

	@Override
	public String CancelOrder(String idorder) {
		cancelorderresult = FAILED;
		updateRowCnt = 0;
		String sql = "update repair.order set nameengineer = '', mobileengineer = '', status = 'unreceived' "
				+ "where idorder='" + idorder + "';";
		try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql);
			try {
				updateRowCnt = preparedStatement.executeUpdate();
				if(updateRowCnt != 0){
					cancelorderresult = SUCCEEDED;
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
		System.out.println("takeorder service result:" + cancelorderresult);
		return cancelorderresult;
	}

	@Override
	public String CompleteOrder(String idorder) {
		completeorderresult = FAILED;
		updateRowCnt = 0;
		String sql = "update repair.order set status = 'completed' "
				+ "where idorder='" + idorder + "';";
		try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql);
			try {
				updateRowCnt = preparedStatement.executeUpdate();
				if(updateRowCnt != 0){
					completeorderresult = SUCCEEDED;
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
		System.out.println("takeorder service result:" + completeorderresult);
		return completeorderresult;
	}

	@Override
	public String DeleteOrder(String idorder) {
		deleteorderresult = FAILED;
		updateRowCnt = 0;
		String sql = "delete from repair.order where idorder = '" + idorder + "'";
		try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql);
			updateRowCnt = preparedStatement.executeUpdate();
			if(updateRowCnt != 0){
				deleteorderresult = SUCCEEDED;
			}
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("deleteorder service result:" + deleteorderresult);
		return deleteorderresult;
	}

	@Override
	public String ExpireOrder(String date) {
		expireorderresult = FAILED;
		updateRowCnt = 0;
		String sql = "update repair.order set status = 'expired' where Date(date) < '" + date + "' and status = 'unreceived'";
		try {
			Connection con = DBManager.getConnection();
			preparedStatement = con.prepareStatement(sql);
			updateRowCnt = preparedStatement.executeUpdate();
			expireorderresult = SUCCEEDED;
			preparedStatement.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("expireorder service result:" + expireorderresult);
		return expireorderresult;
	}
	
}
