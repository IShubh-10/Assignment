package com.book.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.book.pojo.Customer;
import com.book.utility.DBConnection;
import com.book.pojo.Book;

public class CustomerDaoImpl implements CustomerDao{

	Connection con=null;
	PreparedStatement ps=null;
	String sql=null;
	ResultSet rs=null;
	Customer c=null;
	List<Customer> clist=null;

	@Override
	public boolean addCustomer(Customer c) {

		con=DBConnection.makeConnection();
		sql="insert into customer(cname, email, password, contactNo, age, gender)"+"values (?, ?, ?, ?, ?, ?)";
		try 
		{
			ps=con.prepareStatement(sql);
			ps.setString(1, c.getCname());
			ps.setString(2, c.getEmail());
			ps.setString(3, c.getPassword());
			ps.setLong(4, c.getContactNo());
			ps.setInt(5, c.getAge());
			ps.setString(6, c.getGender());
			
			int i=ps.executeUpdate();
			if(i>0)
			{
				return true;
			}
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCustomer(Customer c) {

		con=DBConnection.makeConnection();
		sql="update customer set cname=?, email=?, password=?, contactNo=?, age=?, gender=? where cId=?";
		try 
		{
			ps=con.prepareStatement(sql);
			ps.setString(1, c.getCname());
			ps.setString(2, c.getEmail());
			ps.setString(3, c.getPassword());
			ps.setLong(4, c.getContactNo());
			ps.setInt(5, c.getAge());
			ps.setString(6, c.getGender());
			ps.setInt(7, c.getcId());
			
			int i=ps.executeUpdate();
			if(i>0)
			{
				return true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(Integer cId) {

		con=DBConnection.makeConnection();
		sql="delete from customer where cId=?";
		try 
		{
			ps=con.prepareStatement(sql);
			ps.setInt(1, cId);
			int i=ps.executeUpdate();
			if(i>0)
			{
				return true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Customer searchCustomerById(Integer cId) {

		con=DBConnection.makeConnection();
		sql="select * from customer where cId=?";
		try 
		{
			ps=con.prepareStatement(sql);
			ps.setInt(1, cId);
			
			rs=ps.executeQuery();
			if(rs.next()) 
			{
				Customer c=new Customer();
				c.setcId(rs.getInt("cId"));
				c.setCname(rs.getString("cname"));
				c.setEmail(rs.getString("email"));
				c.setPassword(rs.getString("password"));
				c.setContactNo(rs.getLong("contactNo"));
				c.setAge(rs.getInt("age"));
				c.setGender(rs.getString("gender"));
				
				return c;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Customer> fetchAllCustomer() {

		con=DBConnection.makeConnection();
		sql="select*from customer";
		try 
		{
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			clist=new ArrayList<>();
			while(rs.next())
			{
				c=new Customer();
				c.setcId(rs.getInt("cId"));
				c.setCname(rs.getString("cname"));
				c.setEmail(rs.getString("email"));
				c.setPassword(rs.getString("password"));
				c.setContactNo(rs.getLong("contactNo"));
				c.setAge(rs.getInt("age"));
				c.setGender(rs.getString("gender"));
				
				clist.add(c);
			}
			return clist;
		}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public List<Customer> searchCustomerByName(String cname) {

		con=DBConnection.makeConnection();
		sql="select * from customer where cname like ?";
		try 
		{
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+cname+"%");
			rs=ps.executeQuery();
			clist=new ArrayList<>();
			while(rs.next())
			{
				c=new Customer();
				c.setcId(rs.getInt("cId"));
				c.setCname(rs.getString("cname"));
				c.setEmail(rs.getString("email"));
				c.setPassword(rs.getString("password"));
				c.setContactNo(rs.getLong("contactNo"));
				c.setAge(rs.getInt("age"));
				c.setGender(rs.getString("gender"));
				
				clist.add(c);
			}
			return clist;
		}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		return null;
	}

	@Override
	public Customer searchCustomerByEmail(String email) {

		con=DBConnection.makeConnection();
		sql="select * from customer where email=?";
		try 
		{
			ps=con.prepareStatement(sql);
			ps.setString(1, email);
			
			rs=ps.executeQuery();
			if(rs.next()) 
			{
				Customer c=new Customer();
				c.setcId(rs.getInt("cId"));
				c.setCname(rs.getString("cname"));
				c.setEmail(rs.getString("email"));
				c.setPassword(rs.getString("password"));
				c.setContactNo(rs.getLong("contactNo"));
				c.setAge(rs.getInt("age"));
				c.setGender(rs.getString("gender"));
				
				return c;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean uniquePassword(String password) {
		con = DBConnection.makeConnection();
		sql ="SELECT * FROM Customer WHERE password=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, password);
			rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	
}
