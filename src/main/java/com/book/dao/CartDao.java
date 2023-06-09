package com.book.dao;

import java.util.List;
import com.book.pojo.Cart;


public interface CartDao {

	public boolean addToCart(Cart c);
	public boolean deleteCartItem(Integer cartId);
	public boolean uopdateQuantity(Integer cartId, Integer quantity);
	public boolean clearMyCart(String email);
	
	public List<Cart> showMyCart(String email);
	public List<Cart> showAllCart();
}
