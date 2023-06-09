package com.book.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.dao.CustomerDaoImpl;
import com.book.dao.BookDaoImpl;
import com.book.pojo.Customer;


@WebServlet("/CustomerServlet")
public class CustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Customer c;
	List<Customer> clist;
	CustomerDaoImpl cimpl=new CustomerDaoImpl();
	HttpSession session;
	RequestDispatcher rd;
	Boolean flag;
	String login, msg, errorMsg;

	private Integer cId;
	private String cname;
	private String email;
	private String password;
	private Long contactNo;
	private Integer age;
	private String gender;

	public CustomerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession();
		String process=request.getParameter("process");
		if(process!=null && process.equals("myProfile")) {
			email=(String) session.getAttribute("username");
			c=new CustomerDaoImpl().searchCustomerByEmail(email);
			session.setAttribute("custObj", c);
			response.sendRedirect("Profile.jsp");
		}
		else if(process!=null && process.equals("deleteProfile")) {
			cId=Integer.parseInt(request.getParameter("cId"));
			flag=cimpl.deleteCustomer(cId);
			if(flag) {
				session.invalidate();
				request.setAttribute("msg", "Your profile has been deleted.");
				rd=request.getRequestDispatcher("Index.jsp");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("errorMsg", "Error while deleting your profile.Please  try again");
				rd=request.getRequestDispatcher("Index.jsp");
				rd.forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		session=request.getSession();
		String process=request.getParameter("process");

		if(process!=null && process.equals("addCustomer")) {
			//cId=Integer.parseInt(request.getParameter("cId"));
			cname=request.getParameter("cname");
			email=request.getParameter("email");
			password=request.getParameter("password");
			contactNo=Long.parseLong(request.getParameter("contactNo"));
			age=Integer.parseInt(request.getParameter("age"));
			gender=request.getParameter("gender");

			c=new Customer(cname, email, password, contactNo, age, gender);
			flag=cimpl.addCustomer(c);
			if(flag) {
				msg="You've been successefully registered. please login";
				request.setAttribute("msg", msg);
				rd=request.getRequestDispatcher("Index.jsp");
				rd.forward(request, response);
			}
			else {
				errorMsg="Error while registering your details. please try again";
				request.setAttribute("errorMsg", errorMsg);
				rd=request.getRequestDispatcher("Index.jsp");
				rd.forward(request, response);
			}
		}
		else if(process!=null && process.equals("editCustomer")) {		
			cId=Integer.parseInt(request.getParameter("cId"));
			cname=request.getParameter("cname");
			email=request.getParameter("email");
			password=request.getParameter("password");
			contactNo=Long.parseLong(request.getParameter("contactNo"));
			age=Integer.parseInt(request.getParameter("age"));
			gender=request.getParameter("gender");

			c=new Customer(cId,cname,email,password,contactNo,age,gender);
			flag=cimpl.updateCustomer(c);

			if(flag) {				
				request.setAttribute("msg", "Your profile has been updated sucessfully!!");
				rd=request.getRequestDispatcher("Index.jsp");
				rd.forward(request, response);
			}
			else {
				request.setAttribute("errorMsg", "Error while updating your details.Please try again");
				rd=request.getRequestDispatcher("Index.jsp");
				rd.forward(request, response);
			}
		}
	}
}
