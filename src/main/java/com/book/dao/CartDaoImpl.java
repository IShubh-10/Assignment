package com.book.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.book.pojo.Book;
import com.book.pojo.Cart;
import com.book.utility.DBConnection;

public class CartDaoImpl implements CartDao{

	Connection con=null;
	PreparedStatement ps=null;
	String sql=null;
	ResultSet rs=null;
	Cart c=null;
	List<Cart> clist=null;

	@Override
	public boolean addToCart(Cart c) {
		con=DBConnection.makeConnection();
		sql="insert into CART(bookId, quantity, price, subtotal, email) values " +"(?,?,?,?,?)";
		
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, c.getBookId());
			ps.setInt(2, c.getQuantity());
			ps.setDouble(3, c.getPrice());
			ps.setDouble(4, c.getSubtotal());
			ps.setString(5, c.getEmail());
			
			
			
			int i=ps.executeUpdate();
			if(i>0)
				return true;
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean deleteCartItem(Integer cartId) {
		con=DBConnection.makeConnection();
		sql="delete from CART where cartId=?";
		try
		{
			ps=con.prepareStatement(sql);
			ps.setInt(1, cartId);
			
			
			int i=ps.executeUpdate();
			if(i>0)
			{
				return true;
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean uopdateQuantity(Integer cartId, Integer quantity) {
		con=DBConnection.makeConnection();
		sql="update CART set quantity=? where cartId=?";
		try
		{
			ps=con.prepareStatement(sql);
			ps.setInt(1, quantity);
			ps.setInt(2, cartId);
			
			int i=ps.executeUpdate();
			if(i>0)
			{
				sql="update CART set subtotal=(price*quantity) where cartId=?";
				ps=con.prepareStatement(sql);
				ps.setInt(1, cartId);
				
				i=ps.executeUpdate();
				if(i>0)
				return true;
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean clearMyCart(String email) {
		con=DBConnection.makeConnection();
		sql="delete from CART where email=?";
		try
		{
			ps=con.prepareStatement(sql);
			ps.setString(1, email);
			
			
			int i=ps.executeUpdate();
			if(i>0)
			{
				return true;
			}
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public List<Cart> showMyCart(String email) {
		con=DBConnection.makeConnection();
		sql="select * from CART where email=?";

		try {
			ps=con.prepareStatement(sql);
			
			ps.setString(1, email);
            rs=ps.executeQuery();
			
			clist=new ArrayList<>();
			
			
			while(rs.next()) {
				c=new Cart();
				c.setCartId(rs.getInt("cartId"));
				c.setEmail(rs.getString("email"));
				c.setPrice(rs.getDouble("price"));
				
				int bookId= rs.getInt("bookId");
				Book b= new BookDaoImpl().searchBookById(bookId);
				
				c.setB(b);
				c.setBookId(bookId);
				c.setQuantity(rs.getInt("quantity"));
				c.setSubtotal(rs.getDouble("subtotal"));
				
				clist.add(c);
				
			}
			return clist;
				
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public List<Cart> showAllCart() {
		con=DBConnection.makeConnection();
		sql="select * from CART";
		
		try {
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			
			clist=new ArrayList<>();
			while(rs.next()) {
				c=new Cart();
				c.setCartId(rs.getInt("cartId"));
				c.setQuantity(rs.getInt("quantity"));
				c.setSubtotal(rs.getDouble("subTotal"));
				c.setEmail(rs.getString("email"));
				c.setPrice(rs.getDouble("price"));
				
				
				int bookId= rs.getInt("bookId");
				Book b= new BookDaoImpl().searchBookById(bookId);
				clist.add(c);
			}
			return clist;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Map <Double, Double> fetchPriceSubtotal(Integer bookId, Integer quantity)
	{
		con=DBConnection.makeConnection();
		sql="select price from ADDBOOK where bookId=?";
		try {
			ps=con.prepareStatement(sql);
			ps.setInt(1, bookId);
			rs=ps.executeQuery();
			Map<Double, Double> m= new HashMap<>();
			
			if(rs.next())
			{
				double price= rs.getDouble(1);
				double subtotal=price*quantity;
				m.put(price, subtotal);
				
				return m;
			}
		
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
				con.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean checkCartItem(Integer bookId, String email) {
		con=DBConnection.makeConnection();
		sql="select * from CART where bookId=? && email=?";
		try {
		ps=con.prepareStatement(sql);
		ps.setInt(1, bookId);
		ps.setString(2, email);
		
		rs=ps.executeQuery();
		if(rs.next())
		{
			int cartId=rs.getInt("cartId");
			int quantity=rs.getInt("quantity");
			quantity+=1;
			
			return uopdateQuantity(cartId, quantity);
		}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return false;
	}	

}
