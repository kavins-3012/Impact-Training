package com.aspire;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class FoodHut  {
	 private static List<User> users = new ArrayList<>();
	    private static List<MenuItem> menu = new ArrayList<>();
	    
	protected static void intialization()
	{
		try {
            initializeUsers();  
            initializeMenu();

           User user = loginOrSignup();
            if (user == null) {
            	
                System.out.println("you cannot login ");
                return;
            }

        //    Order order = new Order(user);
            showMenu();
            String address = null;
            //paymentOption = null;
            
            Order order = takeOrder(user, address);
             address = getAddress();
            order.generateReceipt();
            logout();
          //  takeOrder(order);
//             paymentOption = getPaymentOption();
        } catch (Exception e)
        {
            System.out.println(" " + e.getMessage());
        }
	}

    protected static void initializeUsers() {
    	
		users.add(new User("kavin", "pass123"));
        users.add(new User("rahul", "pass456"));
    }

    protected static void initializeMenu() {
        menu.add(new MenuItem("Burger", 100));
        menu.add(new MenuItem("Pizza", 300));
        menu.add(new MenuItem("Soft Drink", 30));
    }

    protected static User loginOrSignup() throws Exception 
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("LOGIN / SIGNUP");
        System.out.println("1. Login");
        System.out.println("2. Signup");
        System.out.println("3. view prodcuts");
        System.out.print("Select an option: ");

        int option;
        try 
        {
            option = scanner.nextInt();
        } catch (InputMismatchException e) {
            throw new Exception("Invalid input.");
        }
       
        switch (option)
{
            case 1: 
                System.out.print("Username: ");
                String username = scanner.next();

                System.out.print("Password: ");
                String password = scanner.next();

                User loginUser = getUserByUsername(username);
                if (loginUser != null && loginUser.getPassword().equals(password))
                {
                    System.out.println("Login successful " + username );
                    return loginUser;
                } else 
                {
                    throw new Exception("Invalid username or password.");
                }
            case 2: 
               do 
               {
            	   System.out.println("First letter should not be a number");
            	   System.out.print("Username: ");
                username = scanner.next();
               }
               while(!isValidUsername(username));
               do 
               {
            	System.out.println("Password should contain minumum 8 length,atleast one upper case & one lower case");
                System.out.print("Password: ");
                 password = scanner.next();
               }while(!isValidPassword(password));
               {
                User newUser = new User( username, password);
                users.add(newUser);
                System.out.println("Signup successful " + username );
                return newUser;
               }
            case 3:
            	showMenu();
            	break;
            default:
                throw new Exception("Invalid option.");
        }
        return null;
}
	private static boolean isValidUsername(String username) 
	{
    	   if (username.isEmpty()) 
    	   {
               System.out.println("Username cannot be empty. Please try again.");
               return false;
           }
    	   if (!username.matches(".*[a-z].*")) 
	        {
	            System.out.println("username must contain only alphabets");
	            return false;
	        }
           for (User user : users) 
           {
               if (user.getUsername().equals(username)) 
               {
                   System.out.println("Username already exists. Please try again.");
                   return false;
               }
           }

           return true;
	}
	  private static boolean isValidPassword(String password) 
	  {
		  if (password.length() < 8) 
		  {
	            System.out.println("Password must have a minimum length of 8 characters.");
	            return false;
	        }
	        if (!password.matches(".*[A-Z].*"))
	        {
	            System.out.println("Password must contain at least one uppercase letter.");
	            return false;
	        }
	        if (!password.matches(".*[a-z].*")) 
	        {
	            System.out.println("Password must contain at least one lowercase letter.");
	            return false;
	        }
	       return true;
	    }


	protected static User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    protected static void showMenu()
    {
        System.out.println("************ MENU ************");
        for (int i = 0; i < menu.size(); i++)
        {
            MenuItem item = menu.get(i);
            System.out.println((i + 1) +" " + item.getName() + " " + item.getPrice());
        }
        
        System.out.println("******************************");
        
    }

    public static String getAddress() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("************ DELIVERY ADDRESS ************");
        System.out.print("Enter delivery address: ");
        return scanner.nextLine();
    }

    public static String getPaymentOption() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("************ PAYMENT OPTION ************");
        System.out.println("1. Credit Card");
        System.out.println("2. Cash");

        System.out.print("Enter your choice (1-2): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                return "Credit Card";
            case 2:
                return "Cash";
            default:
                System.out.println("Invalid choice.");
                return null;
        }
    }

    protected static Order takeOrder(User user,String address) throws Exception
    {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order(user,address);

        System.out.print("Enter the item number to order 0 to finish ");
        int itemNumber;
        try 
        {
            itemNumber = scanner.nextInt();
        } catch (InputMismatchException e) 
        {
            throw new Exception("Invalid input.");
        }

        while (itemNumber != 0) 
        {
            if (itemNumber > 0 && itemNumber <= menu.size()) 
            {
                MenuItem item = menu.get(itemNumber - 1);

                System.out.print("Enter the quantity: ");
                int quantity;
                try 
                {
                    quantity = scanner.nextInt();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Quantity set to 0.");
                    quantity = 0;
             }
             //   Order order = new Order(user,address, paymentOption);
                order.addItem(item, quantity);
            } else 
            {
                throw new Exception("Please try again.");
            }
            System.out.print("Enter the item number to order (0 to finish): ");
            try 
            {
                itemNumber = scanner.nextInt();
            } catch (InputMismatchException e) {
                throw new Exception("Invalid input.");
            }
        }
		return order;
    }
public static void logout()
{
	System.out.println("logout successfully");
	System.exit(0);
}
}