package com.book.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.Scanner;

import com.book.dao.BookDaoImpl;
import com.book.dao.CartDaoImpl;
import com.book.dao.LoginDaoImpl;
import com.book.pojo.Book;
import com.book.pojo.Cart;
import com.book.pojo.Customer;
import com.book.utility.LoginInvalidException;

public class CartTest {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Boolean flag;
		LoginDaoImpl limpl = new LoginDaoImpl();
		CartDaoImpl cimpl = new CartDaoImpl();
		Cart c= null;
		List<Cart> clist =null;
		String login=null;
		Book b=null;
		BookDaoImpl bimpl=new BookDaoImpl();
		List<Book> blist= null;
		int option;

		Integer cartId;
		Integer bookId;
		Integer quantity;
		Double subtotal=null;
		String email=null;
		Double price=null;
		

		while(true)
		{
			System.out.println("**** Welcome to cart menu ****");

			System.out.println("Enter usser name: Your email id");
			
			String username=sc.nextLine();
			email=username;

			System.out.println("Enter password");
			String password=sc.nextLine();
			System.out.println("_________________________________________");

			flag =limpl.checkAdmin(username, password);

			if(flag)
			{
				System.out.println("You have login as admin");
				login="admin";
			}
			else {
				flag =limpl.checkCustomer(username, password);
				if(flag)
				{
					System.out.println("You have login as customer");
					login="customer";
				}else {
					throw new LoginInvalidException("The credentials given are wrong");
				}
			}
			if(login.equals("admin"))
			{                                     //dhhdhdhdhd
				clist=cimpl.showAllCart();
				if(clist!=null && !clist.isEmpty())
				{
					Iterator<Cart> it=clist.iterator();
					while(it.hasNext())
					{
						System.out.println(it.next());
					
					}
					
					break;
				}

				else

					System.out.println("No book items added to cart till now.....");
				break;

			}
			else if(login.equals("customer"))
			{
				boolean exit=false;
				do {
					System.out.println("*****Cart Menu*****");
					System.out.println("Enter 1 ----> Add to cart");
					System.out.println("Enter 2 ----> Show my cart");
					System.out.println("Enter 3 ----> Clear my cart");
					System.out.println("Enter 4 ----> Logout");

					option=sc.nextInt();
					sc.nextLine();

					switch(option)
					{
					case 1:
						blist=bimpl.fetchallBooks();
						if(blist!=null && !blist.isEmpty())
						{
							for(Book b1: blist)
							{
								System.out.println("Food id : " +b1.getBookId());
								System.out.println("Name : " +b1.getBookName());
								System.out.println("Price : " +b1.getPrice());
								System.out.println("Language : " +b1.getLang());
								System.out.println("____________________________-/n");
							}
							System.out.println("Enter the id of book to be added to cart: ");
							bookId=sc.nextInt();
							sc.nextLine();

							System.out.println("How many of this item you like to add");
							quantity=sc.nextInt();
							sc.nextLine();

							Map<Double, Double> m=cimpl.fetchPriceSubtotal(bookId, quantity);
							

							if(m!=null)
							{
								for(Map.Entry<Double, Double> me: m.entrySet())
								{
									if(me!=null)
									{
										price=me.getKey();
										subtotal=me.getValue();
									}
								}
							}
								else
								{
									System.out.println("Error");
									break;
								}
								c=new Cart();
								c.setBookId(bookId);
								c.setEmail(email);
								c.setPrice(price);
								c.setSubtotal(subtotal);
								c.setQuantity(quantity);
								
								flag=cimpl.addToCart(c);
								if(flag)
								     System.out.println("Your item has been added to cart successfully");

								else
								{
									System.out.println("No data available currently");
								}
							}
								break;
					case 2:
						email=username;
						clist=cimpl.showMyCart(email);
						double total=0;
						boolean exit2=false;
						if(clist!=null && !clist.isEmpty())
						{
							System.out.println("******Your Cart********");
							Iterator<Cart> it=clist.iterator();
							while(it.hasNext())
							{
								c=it.next();
								System.out.println("Cart id: "+c.getCartId());
								System.out.println("Name: "+ c.getB().getBookName());
								System.out.println("Quantity: "+c.getQuantity());
								System.out.println("Price per item: "+c.getPrice());
								System.out.println("Subtotal: "+c.getSubtotal());
								total+=c.getSubtotal();
								System.out.println("_______________________________");
							}
							System.out.println("Total bill payment Rs: "+total);
							while(exit2==false)
							{
								System.out.println("Enter 1----> Update quantity of an item");
								System.out.println("Enter 2----> Delete an item from cart");
								System.out.println("Enter 3 ---> Go back to main menu");
								
								int choice=sc.nextInt();
								sc.nextLine();
								
								if(choice==1) {
									System.out.println("Enter the cart id whose quantity needs to updated");
									cartId=sc.nextInt();
									sc.nextLine();
									
									System.out.println("Enter the new quantity");
									quantity=sc.nextInt();
									sc.nextLine();
									
									flag=cimpl.uopdateQuantity(cartId, quantity);
									
									if(flag)
									{
										System.out.println("Quantity has been changed");
										System.out.println("Do you want to continue? Answer yes or no ");
										if(sc.nextLine().equalsIgnoreCase("yes")) {
											System.out.println("Thank You for input");
										}
										else {
											exit2=true;
										}
									}
								}
								else if(choice==2)
								{
									System.out.println("Enter the cart id whose quantity needs to be updated: ");
									cartId=sc.nextInt();
									sc.nextLine();
									
									flag=cimpl.deleteCartItem(cartId);
									if(flag)
									{
										System.out.println(" This Item has been deleted successfully");
										System.out.println("Do you want to continue? Answer yes or no ");
										
										if(sc.nextLine().equalsIgnoreCase("yes")) {
											System.out.println("Thank You for input");
										}
										
									}
									else {
										exit2=true;
									}
								}
									else if(choice==3) {
										exit2=true;
									}
									
									
								
								
								else
									System.out.println("Enter valid inputs only !!!");
							}
							
						}
						else
						{
							System.out.println("No Book item added to your cart till now");
						}
						break;
						
					case 3:
						email=username;
						flag=cimpl.clearMyCart(email);
						
						if(flag)
						{
							System.out.println("Ypur Cart is Empty now");
						}
						else
							System.out.println("Error while removing items from your cart");
						
						break;
						
					case 4:
						System.out.println("You have clicked logout. Do you want to continue ?");
						System.out.println("Answer in yes or no");
						String choice=sc.next();
						sc.nextLine();
						
						if(choice.equalsIgnoreCase("yes")) {
							System.out.println("Thank you visit us soon !!!");
							exit=true;
						}
						else if(choice.equalsIgnoreCase("no"))
							System.out.println("Thank You !! Contionue Browsing");
						else
							System.out.println("Invalid input. Please answer in yes or no only");
						
						break;
						
					default:
						System.out.println("Plese enter given option only");
							
						}
					}while(exit==false);
			}
					else
					{
						System.out.println("Please login to continue...");
					}
				}


			}
		}










