package com.book.test;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import com.book.dao.BookDaoImpl;
import com.book.pojo.Book;

public class BookTest {

	public static void main(String[] args) 
	{
		Scanner sc=new Scanner(System.in);
		Book b=null;
		BookDaoImpl bimpl=new BookDaoImpl();
		List<Book> blist;

		Integer bookId;
		String bookName;
		String author;
		Double price;
		String lang;
		boolean flag;

		System.out.println("*********Welcome to our Book Store Portal*********");
		while(true)
		{
			System.out.println("Enter the number given in options...");
			System.out.println();
			System.out.println("Enter 1---> Add Book");
			System.out.println("Enter 2---> All Books");
			System.out.println("Enter 3---> Update Book details");
			System.out.println("Enter 4---> Delete Book");
			System.out.println("Enter 5---> Search Book by Author");
			System.out.println("Enter 6---> E X I T");


			int option=sc.nextInt();
			sc.nextLine();

			switch(option)
			{
			case 1:
				System.out.print("Enter name of Book:");
				bookName=sc.nextLine();

				System.out.print("Enter Author:");
				author=sc.nextLine();

				System.out.print("Enter Price:");
				price=sc.nextDouble();
				sc.nextLine();

				System.out.print("Enter Language of Book:");
				lang=sc.nextLine();


				b=new Book(bookName, author, price, lang);
				flag=bimpl.addBook(b);

				if(flag)
				{
					System.out.print("Book added successfully!!! ");
				}
				else 
				{
					System.out.print("Error while adding Book details! ");
				}
				break;
				
			case 2:
				blist=bimpl.fetchallBooks();

				if(blist!=null && !blist.isEmpty())
				{
					blist.forEach(x->{
						if(x!=null)
						{
							System.out.println(x);
							System.out.println("------------------------------------");
						}
					});
				}

				break;
				
			case 3:
				System.out.println("Enter book id: ");
				bookId=sc.nextInt();
				sc.nextLine();

				b=bimpl.searchBookById(bookId);

				if(b!=null)
				{
					System.out.println(b);
					System.out.println("DO you want to continue updating? Answer yes or no");
					String choice=sc.next().toLowerCase();
					sc.nextLine();

					if(choice.equals("yes")) 
					{
						System.out.print("Enter name of Book:");
						bookName=sc.nextLine();

						System.out.print("Enter Author:");
						author=sc.nextLine();

						System.out.print("Enter Price:");
						price=sc.nextDouble();
						sc.nextLine();

						System.out.print("Enter Language of Book:");
						lang=sc.nextLine();

						b.setBookName(bookName);
						b.setAuthor(author);
						b.setPrice(price);
						b.setBookId(bookId);
						b.setLang(lang);

						flag=bimpl.updateBook(b);
						if(flag)	
						{
							System.out.println("Book updated Successfully!!");
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
					System.out.println("No such Book found!");
				}

				break;

			case 4:

				System.out.println("Enter Book id: ");
				bookId=sc.nextInt();
				sc.nextLine();

				b=bimpl.searchBookById(bookId);

				if(b!=null)
				{
					System.out.println(b);
					System.out.println("DO you want to continue Deleting? Answer yes or no");
					String choice=sc.next().toLowerCase();
					sc.nextLine();

					if(choice.equals("yes")) 
					{
						flag=bimpl.deleteBook(bookId);
						if(flag)
						{
							System.out.println("Book Deleted Successfully!");
						}
						else
						{
							System.out.println("Error while deleting Book");
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
					System.out.println("No such movie found!");
				}

				break;

			case 5:
				System.out.println("Enter Author Name: ");
				author=sc.nextLine();
				blist=bimpl.searchBookByAuthor(author);
				if(blist!=null && blist.isEmpty()!=true)
				{
					for(Book b1 : blist)
					{
						System.out.println(b1);
						System.out.println("------------------------------------------------------------");
					}
				}

				break;

			case 6 :
				System.out.println("\n\n------ Thank you for visiting , come again soon ------");
				System.exit(0);
			default:System.out.println("Please give valid Input!!!");
			}

		}
	}

}
