package com.book.pojo;

public class Cart {
	
	private Integer cartId;
	private Integer bookId;
	private Book b;
	private Integer quantity;
	private Double subtotal;
	private String email;
	private Double price;
	
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cart(Integer cartId, Integer bookId, Book b, Integer quantity, Double subtotal, String email, Double price) {
		super();
		this.cartId = cartId;
		this.bookId = bookId;
		this.b = b;
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.email = email;
		this.price = price;
	}

	public Cart(Integer bookId, Integer quantity, Double subtotal, String email, Double price) {
		super();
		this.bookId = bookId;
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.email = email;
		this.price = price;
	}

	public Integer getCartId() {
		return cartId;
	}
	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}
	public Integer getBookId() {
		return bookId;
	}
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public Book getB() {
		return b;
	}
	public void setB(Book b) {
		this.b = b;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Double getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Cart [cartId=" + cartId + ", bookId=" + bookId + ", quantity=" + quantity + ", subtotal="
				+ subtotal + ", email=" + email + ", price=" + price + "]";
	}

}
