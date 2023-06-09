package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.book.pojo.Customer;
import com.book.utility.DBConnection;

public class LoginDaoImpl implements LoginDao{

	Connection con=null;
	PreparedStatement ps=null;
	String sql=null;
	ResultSet rs=null;
	
	@Override
	public boolean checkCustomer(String username, String password) {

		con=DBConnection.makeConnection();
		sql="select * from customer where email=? && password=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			rs=ps.executeQuery();
			// rs.next() will scan the first row and will return true if there is any data available
			if(rs.next()) 
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean checkAdmin(String username, String password) {

		con=DBConnection.makeConnection();
		sql="select * from admin01 where adminEmail=? && adminPassword=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			rs=ps.executeQuery();
			if(rs.next()) 
			{
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean changeCustomerpasssword(String username, String oldPassword, String newPassword) {

		con=DBConnection.makeConnection();
		sql="";
		return false;
	}

	@Override
	public boolean changeAdminpasssword(String username, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		return false;
	}

}
