package com.book.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.book.dao.CustomerDaoImpl;
import com.book.dao.BookDaoImpl;
import com.book.pojo.Customer;

public class CustomerTest {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		Customer c=null;
		CustomerDaoImpl cimpl=new CustomerDaoImpl();
		List<Customer> clist;

		Integer cId = null;
		String cname;
		String email;
		String password;
		Long contactNo;
		Integer age;
		String gender;

		System.out.println("\n\n--------- WELCOME TO OUR CUSTOMER BOOKING PORTAL ---------");
		while(true)
		{
			System.out.println("ENTER THE NUMBER GIVEN IN OPTIONS....");
			System.out.println("\nEnter 1---> Add Customer");
			System.out.println("Enter 2---> Update Customer");
			System.out.println("Enter 3---> Delete Customer");
			System.out.println("Enter 4---> search Customer By Id");
			System.out.println("Enter 5---> All Cutomer");
			System.out.println("Enter 6---> search Customer By Name");
			System.out.println("Enter 7---> search Customer By Email");
			System.out.println("Enter 8---> E X I T");

			int option=sc.nextInt();
			sc.nextLine();
			boolean flag;
			switch(option) {
			case 1:
				System.out.println("Enter Customer Name : ");
				cname = sc.nextLine();

				System.out.println("Enter Customer Email : ");
				email = sc.nextLine();

				System.out.println("Enter Customer Password : ");
				password = sc.nextLine();
				flag=cimpl.uniquePassword(password);
				while(flag) {
					System.out.println("This password is already being used by another customer");
					System.out.println("------------------------------------------------------------");
					System.out.println("Please give another input....");
					password = sc.nextLine();
					flag=cimpl.uniquePassword(password);
				}

				System.out.println("Enter Customer Contact Info : ");
				contactNo = sc.nextLong();
				sc.nextLine();

				System.out.println("Enter Customer Age : ");
				age = sc.nextInt();
				sc.nextLine();

				System.out.println("Enter Customer Gender : ");
				gender = sc.nextLine();

				c=new Customer(cId, cname, email, password, contactNo, age, gender);
				flag=cimpl.addCustomer(c);

				if(flag)
				{
					System.out.print("Customer added successfully!!! ");
				}
				else 
				{
					System.out.print("Error while adding Customer details! ");
				}
				break;

			case 2:
				System.out.println("Enter Customer Id");
				cId = sc.nextInt();
				sc.nextLine();

				c=cimpl.searchCustomerById(cId);
				if(c!=null)
				{
					System.out.println(c);
					System.out.println("DO you want to continue updating? Answer yes or no");
					String choice=sc.next().toLowerCase();
					sc.nextLine();

					if(choice.equals("yes")) 
					{
						System.out.print("Enter name of customer:");
						cname=sc.nextLine();

						System.out.print("Enter Email:");
						email=sc.nextLine();

						System.out.print("Enter password:");
						password=sc.nextLine();

						System.out.print("Enter contact Info:");
						contactNo=sc.nextLong();
						sc.nextLine();

						System.out.print("Enter Age");
						age=sc.nextInt();
						sc.nextLine();

						System.out.print("Enter Gender");
						gender=sc.nextLine();

						c.setCname(cname);
						c.setEmail(email);
						c.setPassword(password);
						c.setContactNo(contactNo);
						c.setAge(age);
						c.setGender(gender);

						flag=cimpl.updateCustomer(c);
						if(flag)	
						{
							System.out.println("Customer updated Successfully!!");
						}
						else
						{
							System.out.println("Error while updating!");
						}
					}
					else if(choice.equals("no")) 
					{
						System.out.println("Thank-you for your response. Continue Browsing..");
					}
					else 
					{
						System.out.println("Invalid input. Please try again.");
					}
				}
				else
				{
					System.out.println("No such Customer found!");
				}
				break;

			case 3:
				System.out.println("Enter Customer id: ");
				cId=sc.nextInt();
				sc.nextLine();

				c=cimpl.searchCustomerById(cId);

				if(c!=null)
				{
					System.out.println(c);
					System.out.println("DO you want to continue Deleting? Answer yes or no");
					String choice=sc.next().toLowerCase();
					sc.nextLine();

					if(choice.equals("yes")) 
					{
						flag=cimpl.deleteCustomer(cId);
						if(flag)
						{
							System.out.println("Customer Deleted Successfully!");
						}
						else
						{
							System.out.println("Error while deleting Customer Details");
						}
					}
					else if(choice.equals("no")) 
					{
						System.out.println("Thank-you for your response. Continue Browsing..");
					}
					else 
					{
						System.out.println("Invalid input. Please try again.");
					}
				}
				else
				{
					System.out.println("No such Customer found!");
				}
				break;

			case 4:
				System.out.println("Enter Customer Id: ");
				cId=sc.nextInt();
				c=cimpl.searchCustomerById(cId);
				if(c!=null)
				{
					System.out.println(c);
					System.out.println("------------------------------------------------------------");
				}
				break;

			case 5:
				clist=cimpl.fetchAllCustomer();

				if(clist!=null && !clist.isEmpty())
				{

					clist.forEach(x->{
						if(x!=null)
						{
							System.out.println(x);
							System.out.println("------------------------------------");
						}
					});
				}
				break;

			case 6:
				System.out.println("Enter Customer Name: ");
				cname=sc.nextLine();
				clist=cimpl.searchCustomerByName(cname);
				if(clist!=null && clist.isEmpty()!=true)
				{
					for(Customer c1:clist)
					{
						System.out.println(c1);
						System.out.println("------------------------------------------------------------");
					}
				}
				break;

			case 7:
				System.out.println("Enter Customer Email: ");
				email=sc.nextLine();
				c=cimpl.searchCustomerByEmail(email);
				if(c!=null)
				{
					System.out.println(c);
					System.out.println("------------------------------------------------------------");
				}
				else {
					System.out.println("Give valid Input...");
					email=sc.nextLine();
					c=cimpl.searchCustomerByEmail(email);
					if(c!=null)
					{
						System.out.println(c);
						System.out.println("------------------------------------------------------------");
					}
					
				}
				break;

			case 8:
				System.out.println("\n\n------ THANK YOU FOR VISITING , COME AGAIN SOON ------");
				System.exit(0);
				default:System.out.println("Please give valid Input!!!");
			}
		}
	}

}
