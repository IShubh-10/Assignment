package com.book.dao;

import java.util.List;

import com.book.pojo.Customer;

public interface CustomerDao {

	public boolean addCustomer (Customer c);
	public boolean updateCustomer (Customer c);
	public boolean deleteCustomer(Integer cId);
	
	public Customer searchCustomerById(Integer cId);
	public List<Customer> fetchAllCustomer();
	public List<Customer> searchCustomerByName(String cname);
	public Customer searchCustomerByEmail(String email);
	
	public boolean uniquePassword(String password);
}
