package com.book.utility;
import java.sql.*;
public class DBConnection 
{
	public static Connection makeConnection()
	{
		Connection con=null;
		
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/BOOKSTORE", "root" , "1234");
			
			if(con!=null)
			{
				return con;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
}
