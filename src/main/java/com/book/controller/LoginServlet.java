package com.book.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.dao.CustomerDaoImpl;
import com.book.dao.LoginDaoImpl;
import com.book.pojo.Customer;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	LoginDaoImpl limpl = new LoginDaoImpl();
	String login, msg, errorMsg;
	Boolean flag;
	HttpSession session;
	RequestDispatcher rd;
	
	
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		session=request.getSession();
		errorMsg="You've logged out !!!!!";
		session.invalidate(); // session object is deleted. Hence all info about the login is deleted.
		request.setAttribute("errorMsg", errorMsg);
		rd=request.getRequestDispatcher("Index.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		session=request.getSession();
		
		flag=limpl.checkAdmin(username, password);
		if(flag) {
			login="admin";
			msg="You've logged as Admin";
			session.setAttribute("login", login);
			session.setAttribute("username", username);
			request.setAttribute("msg", msg);
			rd=request.getRequestDispatcher("Index.jsp");
			rd.forward(request, response);
		}
		else {
		flag=limpl.checkCustomer(username, password);
		if(flag) {
			login="customer";
			Customer c = new CustomerDaoImpl().searchCustomerByEmail(username);
			msg="Welcom to our website ' "+ c.getCname()+" '";
			session.setAttribute("login", login);
			session.setAttribute("username", username);
			request.setAttribute("msg", msg);
			rd=request.getRequestDispatcher("Index.jsp");
			rd.forward(request, response);
		}
		else{
			login=null;
			errorMsg="Invalid credentials.. Please give valid one";
			session.setAttribute("login", login);
			request.setAttribute("errorMsg", errorMsg);
			rd=request.getRequestDispatcher("Login.jsp");
			rd.forward(request, response);
		}
		}
	}

}
