package com.book.dao;

import java.util.List;

import com.book.pojo.Book;


/*
 * Dao:-Data Access Object
 * */
public interface BookDao 
{
	public boolean addBook(Book b);
	public boolean updateBook(Book b);
	public boolean deleteBook(Integer bookId);
	
	public Book searchBookById(Integer bookId);
	public List<Book> searchBookByAuthor(String author);
	public List<Book> searchBookByLanguage(String lang);
	public List<Book> fetchallBooks();


}
