package com.book.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.book.dao.BookDaoImpl;
import com.book.dao.CartDaoImpl;
import com.book.pojo.Book;
import com.book.pojo.Cart;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession session;
	RequestDispatcher rd;
	Boolean flag;
	Cart c;
	List<Cart> clist;
	CartDaoImpl cimpl=new CartDaoImpl();
	String login, msg, errorMsg;
	
	private Integer cartId;
	private Integer bookId;
	private Book b;
	private Integer quantity;
	private Double subtotal;
	private String email;
	private Double price;
	
	private String bookName;
	private String author;
	private String lang;
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String process=request.getParameter("process");
		session=request.getSession();
		if(process!=null && process.equals("addToCart")) {
			bookId=Integer.parseInt(request.getParameter("bookId"));
			quantity=1;
			BookDaoImpl bimpl=new BookDaoImpl();
			b=bimpl.searchBookById(bookId);
			price=b.getPrice();
			subtotal=quantity*price;
			email=(String)session.getAttribute("email");
			c=new Cart();
			c.setEmail(email);
			c.setBookId(bookId);
			c.setPrice(price);
			c.setQuantity(quantity);
			c.setSubtotal(subtotal);
			
			boolean present=cimpl.checkCartItem(bookId, email);
			if(present) {
				request.setAttribute("msg", "This book was already present; "
						+ "so one more book added");
				}
			else {
			flag=cimpl.addToCart(c);
			if(flag) 
				request.setAttribute("msg", "Your item has been added to cart!!");
			else 
				request.setAttribute("errorMsg", "Error while adding this item to cart");
				
			}
			
			List<Book> blist=new BookDaoImpl().fetchallBooks();
			session.setAttribute("blist", blist);
			
			rd=request.getRequestDispatcher("AllBooks.jsp");
			rd.forward(request, response);
			
		}
		else if(process!=null && process.equals("myCart")) {
			email=(String)session.getAttribute("email");
			clist=cimpl.showAllCart();
			
			if(clist!=null && !clist.isEmpty()) {
				session.setAttribute("clist", clist);
				response.sendRedirect("MyCart.jsp");
			}
			else {
				request.setAttribute("errorMsg", "Your cart is empty. Please add food items to the cart first!!");
				List<Book> blist=new BookDaoImpl().fetchallBooks();
				session.setAttribute("blist", blist);
				
				rd=request.getRequestDispatcher("AllBooks.jsp");
				rd.forward(request, response);
			}
			
		}
		else if(process!=null && process.equals("deleteCartItem"))
		{
			cartId=Integer.parseInt(request.getParameter("cartId"));
			flag=cimpl.deleteCartItem(cartId);
			if(flag) {
				request.setAttribute("msg", "Item deleted successfully.");
				email=(String)session.getAttribute("email");
				clist=cimpl.showMyCart(email);
				
				if(clist!=null && !clist.isEmpty()) {
					session.setAttribute("clist", clist);
					response.sendRedirect("MyCart.jsp");
				}
				else {
					request.setAttribute("errorMsg", "Your cart is empty. Please add Books to the cart first!!");
					List<Book> blist=new BookDaoImpl().fetchallBooks();
					session.setAttribute("blist", blist);
					
					rd=request.getRequestDispatcher("AllBooks.jsp");
					rd.forward(request, response);
				}
			}
			else
			{
				request.setAttribute("errorMsg", "error while deleting");
				rd=request.getRequestDispatcher("MyCart.jsp");
				rd.forward(request, response);
			}
		}
		else if(process!=null && process.equals("allCart")) {
			email=(String)session.getAttribute("email");
			clist=cimpl.showAllCart();
			
			if(clist!=null && !clist.isEmpty()) {
				session.setAttribute("clist", clist);
				response.sendRedirect("MyCart.jsp");
			}
			else {
				request.setAttribute("errorMsg", "Your cart is empty. Please add food items to the cart first!!");
				List<Book> blist=new BookDaoImpl().fetchallBooks();
				session.setAttribute("blist", blist);
				
				rd=request.getRequestDispatcher("AllBooks.jsp");
				rd.forward(request, response);
			}
			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
