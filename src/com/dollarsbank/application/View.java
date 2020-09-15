package com.dollarsbank.application;

import java.util.Scanner;

import com.dollarsbank.controller.DollarsBankController;


public class View {
	
	
	
	
	public static void main(String[] args) {
		
		DollarsBankController controller=new DollarsBankController();
	while(true) {	
		System.out.println("DOLLARSBANK Welcomes You ");
		Scanner scanner =new Scanner(System.in);
		System.out.println("1.Create New Account\n2.Login\n3.Exit.");
		int choice=scanner.nextInt();
		if (choice==1) {
			System.out.println("Enter Details for the Account");
			System.out.println("Customer Name");
			scanner.nextLine();
			String customerName=scanner.nextLine();
			System.out.println("Customer Address");
			String address=scanner.nextLine();
			System.out.println("Customer Contant Number");
			String contactNumber=scanner.nextLine();
			System.out.println("User ID");
			int userId=scanner.nextInt();		
			System.out.println("Password : 8 characters with Lower,Upper and Special");
			String password=scanner.nextLine();
			scanner.nextLine();
			System.out.println("Initial Deposit Amount");
			int deposit=scanner.nextInt();
			
		controller.createAccount(customerName, address, contactNumber, userId, password, deposit);
			
		System.out.println("DOLLARSBANK Welcomes You ");
	    scanner =new Scanner(System.in);
		System.out.println("1.Create New Account\n2.Login\n3.Exit.");
		choice=scanner.nextInt();
		
		}
		 if (choice==2) {
			System.out.println(" Enter Login Details");
			System.out.println(" User ID");
			int userId=scanner.nextInt();	
			System.out.println(" Password ");
			scanner.nextLine();
			String password=scanner.nextLine();
			boolean login=controller.loginCustomer(userId, password);
			while (login==false) {
				System.out.println("Invalid Credentials,Try again");
				System.out.println(" Enter Login Details");
				System.out.println(" User ID");
				userId=scanner.nextInt();	
				System.out.println(" Password ");
				password=scanner.nextLine();
				login=controller.loginCustomer(userId, password);
			}
			if (login) {
				System.out.println(" Welcome customer ");
				System.out.println("1.Deposit Amount\n2.Withdraw Amount\n3.Funds Transfer"
						+ "\n4.View 5 recent transactions\n6.Sign Out");
				
			}
			
		}
		 if (choice==3) {
			break;
		}
		else {
			System.out.println("wrong choice pick again");
		}
		
		
		
		
	}
		
		
	}

}
