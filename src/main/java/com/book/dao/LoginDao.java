package com.book.dao;

public interface LoginDao {

	public boolean checkCustomer(String username, String password);
	public boolean checkAdmin(String username, String password);
	
	public boolean changeCustomerpasssword(String username, String oldPassword, String newPassword);
	public boolean changeAdminpasssword(String username, String oldPassword, String newPassword);


}
