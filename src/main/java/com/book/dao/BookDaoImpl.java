package com.book.dao;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import com.book.pojo.Book;
import com.book.utility.DBConnection;

public class BookDaoImpl implements BookDao {

	Connection con=null;
	PreparedStatement ps=null;
	String sql=null;
	ResultSet rs=null;
	Book b=null;
	List<Book> blist=null;

	@Override
	public boolean addBook(Book b) 
	{
		con=DBConnection.makeConnection();
		sql="insert into ADDBOOK(bookName, author, price, lang)"+"values (?, ?, ?, ?)";
		try 
		{
			ps=con.prepareStatement(sql);
			ps.setString(1, b.getBookName());
			ps.setString(2, b.getAuthor());
			ps.setDouble(3, b.getPrice());
			ps.setString(4, b.getLang());
			
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
	public boolean updateBook(Book b) 
	{
		con=DBConnection.makeConnection();
		sql="update ADDBOOK set bookName=?, author=?, price=?, lang=? where bookId=?";
		try 
		{
			ps=con.prepareStatement(sql);
			ps.setString(1, b.getBookName());
			ps.setString(2, b.getAuthor());
			ps.setDouble(3, b.getPrice());
			ps.setString(4, b.getLang());
			ps.setInt(5, b.getBookId());

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
	public boolean deleteBook(Integer bookId) 
	{	
			con=DBConnection.makeConnection();
			sql="delete from ADDBOOK where bookId=?";
			try 
			{
				ps=con.prepareStatement(sql);
				ps.setInt(1, bookId);
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
	public Book searchBookById(Integer bookId) 
	{
		con=DBConnection.makeConnection();
		sql="select * from ADDBOOK where bookId=?";
		try 
		{
			ps=con.prepareStatement(sql);
			ps.setInt(1, bookId);

			rs=ps.executeQuery();
			if(rs.next()) 
			{
				b=new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setLang(rs.getString("lang"));
				b.setPrice(rs.getDouble("price"));

				return b;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> searchBookByAuthor(String author) 
	{
		con=DBConnection.makeConnection();
		sql="select * from ADDBOOK where author like ?";
		try 
		{
			ps=con.prepareStatement(sql);
			ps.setString(1, "%"+author+"%");
			rs=ps.executeQuery();
			blist=new ArrayList<>();
			while(rs.next())
			{
				b=new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setLang(rs.getString("lang"));
				b.setPrice(rs.getDouble("price"));

				blist.add(b);
			}
			return blist;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Book> searchBookByLanguage(String lang) 
	{
		con=DBConnection.makeConnection();
		sql="select*from ADDBOOK where lang=?";
		try 
		{
			ps=con.prepareStatement(sql);
			ps.setString(1, lang);
			rs=ps.executeQuery();
			blist=new ArrayList<>();
			while(rs.next())
			{
				b=new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setLang(rs.getString("lang"));
				b.setPrice(rs.getDouble("price"));

				blist.add(b);
			}
			return blist;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Book> fetchallBooks() 
	{
		con=DBConnection.makeConnection();
		sql="select*from ADDBOOK";
		try 
		{
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			blist=new ArrayList<>();
			while(rs.next())
			{
				b=new Book();
				b.setBookId(rs.getInt("bookId"));
				b.setBookName(rs.getString("bookName"));
				b.setAuthor(rs.getString("author"));
				b.setLang(rs.getString("lang"));
				b.setPrice(rs.getDouble("price"));

				blist.add(b);
			}
			return blist;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}


		return null;
	}

}
