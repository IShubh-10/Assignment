package com.book.pojo;

import java.time.LocalDate;

public class Book 
{
		private Integer bookId;
		private String bookName;
		private String author;
		private Double price;
		private String lang;
		
		
		public Book() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public Book(String bookName, String author, Double price, String lang) {
			super();
			this.bookName = bookName;
			this.author = author;
			this.price = price;
			this.lang = lang;
		}

		public Book(Integer bookId, String bookName, String author, Double price, String lang) {
			super();
			this.bookId = bookId;
			this.bookName = bookName;
			this.author = author;
			this.price = price;
			this.lang = lang;
		}
		public Integer getBookId() {
			return bookId;
		}
		public void setBookId(Integer bookId) {
			this.bookId = bookId;
		}
		public String getBookName() {
			return bookName;
		}
		public void setBookName(String bookName) {
			this.bookName = bookName;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		public Double getPrice() {
			return price;
		}
		public void setPrice(Double price) {
			this.price = price;
		}
		public String getLang() {
			return lang;
		}
		public void setLang(String lang) {
			this.lang = lang;
		}
		
		
		@Override
		public String toString() {
			return "Book Details \nbookId = " + bookId + "\nbookName = " + bookName + "\nauthor = " + author + "\nprice = " + price
					+ "\nlanguage = " + lang;
		}
		
}
