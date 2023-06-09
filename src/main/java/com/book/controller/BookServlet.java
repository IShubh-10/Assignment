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

import com.book.dao.BookDaoImpl;
import com.book.pojo.Book;


@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession session;
	RequestDispatcher rd;
	Boolean flag;
	Book b;
	List<Book> blist;
	BookDaoImpl bimpl=new BookDaoImpl();
	String login, msg, errorMsg;
	
	private Integer bookId;
	private String bookName;
	private String author;
	private Double price;
	private String lang;
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		session=request.getSession();
		String process=request.getParameter("process");
		if(process!=null && process.equals("allBooks")) {
			blist=bimpl.fetchallBooks();

			if(blist!=null && blist.isEmpty()!=true) {
				session.setAttribute("blist", blist);
				response.sendRedirect("AllBooks.jsp");
			}
			else {
				errorMsg="Sorry no movies to display currently!! please try again";
				request.setAttribute("errorMsg", errorMsg);
				rd=request.getRequestDispatcher("Index.jsp");
				rd.forward(request, response);
			}
		}
		else if(process!=null && process.equals("updateBook")) {
			bookId=Integer.parseInt(request.getParameter("bookId"));
			b=bimpl.searchBookById(bookId);
			request.setAttribute("bookObj", b);
			rd=request.getRequestDispatcher("UpdateBook.jsp");
			rd.forward(request, response);
		}
		else if(process!=null && process.equals("deleteBook")) {
			bookId=Integer.parseInt(request.getParameter("bookId"));
			flag=bimpl.deleteBook(bookId);
			if(flag) {
				msg="Book deleted successfully...";
				request.setAttribute("msg", msg);

				blist=bimpl.fetchallBooks();
				if(blist!=null && blist.isEmpty()!=true) {
					session.setAttribute("blist", blist);
					rd=request.getRequestDispatcher("AllBooks.jsp");
					rd.forward(request, response);
				}
				else {
					errorMsg="Sorry no movies to display currently!! please try again";
					request.setAttribute("errorMsg", errorMsg);
					rd=request.getRequestDispatcher("Index.jsp");
					rd.forward(request, response);
				}
			}
			else {
				errorMsg="Error while deleting the book...";
				request.setAttribute("errorMsg", errorMsg);

				blist=bimpl.fetchallBooks();
				if(blist!=null && blist.isEmpty()!=true) {
					session.setAttribute("blist", blist);
					rd=request.getRequestDispatcher("AllBooks.jsp");
					rd.forward(request, response);
				}
				else {
					errorMsg="Sorry no movies to display currently!! please try again";
					request.setAttribute("errorMsg", errorMsg);
					rd=request.getRequestDispatcher("Index.jsp");
					rd.forward(request, response);
				}
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		session=request.getSession();
		String process=request.getParameter("process");

		if(process!=null && process.equals("editBook")) {
			bookId=Integer.parseInt(request.getParameter("bookId"));
			bookName=request.getParameter("bookName");
			author=request.getParameter("author");
			price=Double.parseDouble(request.getParameter("price"));
			lang=request.getParameter("lang");

			b=new Book(bookId, bookName, author, price, lang);
			flag=bimpl.updateBook(b);
			if(flag) {
				msg="Book updated successfully...";
				request.setAttribute("msg", msg);

				blist=bimpl.fetchallBooks();
				if(blist!=null && blist.isEmpty()!=true) {
					session.setAttribute("blist", blist);
					rd=request.getRequestDispatcher("AllBooks.jsp");
					rd.forward(request, response);
				}
				else {
					errorMsg="Sorry no movies to display currently, please try again later...";
					request.setAttribute("errorMsg", errorMsg);
					rd=request.getRequestDispatcher("Index.jsp");
					rd.forward(request, response);
				}
			}
			else {
				errorMsg="Error while updating the Book details...";
				request.setAttribute("errorMsg", errorMsg);

				blist=bimpl.fetchallBooks();
				if(blist!=null && blist.isEmpty()!=true) {
					session.setAttribute("blist", blist);
					rd=request.getRequestDispatcher("AllBooks.jsp");
					rd.forward(request, response);
				}
				else {
					errorMsg="Sorry no movies to display currently, please try again later...";
					request.setAttribute("errorMsg", errorMsg);
					rd=request.getRequestDispatcher("Index.jsp");
					rd.forward(request, response);
				}
			}
		}
		else if(process!=null && process.equals("addBook")) {
			//bookId=Integer.parseInt(request.getParameter("bookId"));
			bookName=request.getParameter("bookName");
			author=request.getParameter("author");
			price=Double.parseDouble(request.getParameter("price"));
			lang=request.getParameter("lang");

			b=new Book(bookName, author, price, lang);
			flag=bimpl.addBook(b);
			if(flag) {
				msg="Book added successfully...";
				request.setAttribute("msg", msg);

				blist=bimpl.fetchallBooks();
				if(blist!=null && blist.isEmpty()!=true) {
					session.setAttribute("blist", blist);
					rd=request.getRequestDispatcher("AllBooks.jsp");
					rd.forward(request, response);
				}
				else {
					errorMsg="Sorry no Books to display currently, please try again later...";
					request.setAttribute("errorMsg", errorMsg);
					rd=request.getRequestDispatcher("Index.jsp");
					rd.forward(request, response);
				}
			}
			else {
				errorMsg="Error while adding the Book details...";
				request.setAttribute("errorMsg", errorMsg);

				blist=bimpl.fetchallBooks();
				if(blist!=null && blist.isEmpty()!=true) {
					session.setAttribute("blist", blist);
					rd=request.getRequestDispatcher("AllBooks.jsp");
					rd.forward(request, response);
				}
				else {
					errorMsg="Sorry no movies to display currently, please try again later...";
					request.setAttribute("errorMsg", errorMsg);
					rd=request.getRequestDispatcher("Index.jsp");
					rd.forward(request, response);
				}
			}
		}
		else if(process!=null && process.equals("searchBook")) {
			author=request.getParameter("author");
			blist=bimpl.searchBookByAuthor(author);
			if(blist!=null) {
				msg="Result based on your search";
				request.setAttribute("msg", msg);
				blist=bimpl.fetchallBooks();
				if(blist!=null && blist.isEmpty()!=true) {
					session.setAttribute("blist", blist);
					rd=request.getRequestDispatcher("AllBooks.jsp");
					rd.forward(request, response);
				}
			}
			else {
				errorMsg="Error while fetching the Book details...";
				request.setAttribute("errorMsg", errorMsg);

				blist=bimpl.fetchallBooks();
				if(blist!=null && blist.isEmpty()!=true) {
					session.setAttribute("blist", blist);
					rd=request.getRequestDispatcher("AllBooks.jsp");
					rd.forward(request, response);
				}
				else {
					errorMsg="Sorry no books to display currently, please try again later...";
					request.setAttribute("errorMsg", errorMsg);
					rd=request.getRequestDispatcher("Index.jsp");
					rd.forward(request, response);
				}
			}
		}
	}

}
