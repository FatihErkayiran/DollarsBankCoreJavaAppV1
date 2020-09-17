package com.dollarsbank.application;

import java.util.Scanner;

import com.dollarsbank.controller.DollarsBankController;
import com.dollarsbank.model.Customer;


public class View {
	
	
	
	
	public static void main(String[] args) {
		
		
	DollarsBankController controller=new DollarsBankController();
	boolean state=true;
	int accountChoice=9;
	while(state) {	
		System.out.println("DOLLARSBANK Welcomes You ");
		Scanner scanner =new Scanner(System.in);
		System.out.println("1.Create New Account\n2.Login\n3.Exit.");
		int choice=scanner.nextInt();
		scanner.nextLine();
		
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
			scanner.nextLine();
			System.out.println("Password : 8 characters with Lower,Upper and Special");
			String password=scanner.nextLine();
			System.out.println("Initial Deposit Amount");
			int deposit=scanner.nextInt();
			scanner.nextLine();
			int balance=deposit;
			
			
			Customer newCustomer=new Customer(customerName, address, contactNumber, userId, password, deposit,balance);
			
		    boolean signedUp=controller.createAccount(newCustomer);
			
//		System.out.println("DOLLARSBANK Welcomes You ");
//	    scanner =new Scanner(System.in);
//		System.out.println("1.Create New Account\n2.Login\n3.Exit.");
//		choice=scanner.nextInt();
		
		}
		else if (choice==2) {
			System.out.println(" Enter Login Details");
			System.out.println(" User ID");
			int userId=scanner.nextInt();
			scanner.nextLine();
			System.out.println(" Password ");
			//scanner.nextLine();
			String password=scanner.nextLine();
		  Customer cuss= controller.loginCustomer(userId, password);
		  System.out.println(cuss);
		//  System.out.println(userId + "  "+ password);
		  
			//System.out.println(login);
			if (cuss!=null) {
				System.out.println(" Welcome customer ");
				System.out.println("1.Deposit Amount\n2.Withdraw Amount\n3.Funds Transfer"
						+ "\n4.View Five recent transactions\n5.Display Customer Information\n6.Sign Out");
				accountChoice=scanner.nextInt();
				scanner.nextLine();
				
				if(accountChoice==1) {
					System.out.println("How much money you want to deposit ");
					int money=scanner.nextInt();
					scanner.nextLine();
					//System.out.println("money " + money);
					int balance=controller.depositAmount(money, cuss);
					controller.updateBalance(balance, cuss);
				}
				else if (accountChoice==2) {
					System.out.println("How much you want to withdraw ");
					int money=scanner.nextInt();
					scanner.nextLine();
					controller.withdrawAmount(money, cuss);
					
				}
				else if (accountChoice==3) {
					
				}
				else if (accountChoice==4) {
					
				}
				else if (accountChoice==5) {
					
				}
				else if (accountChoice==6) {
					
				}
				else {
					
				}
				state=false;
			}
			else  {
				System.out.println("Invalid Credentials,Try again");
				System.out.println(" Enter Login Details");
				System.out.println(" User ID");
				userId=scanner.nextInt();	
				scanner.nextLine();
				System.out.println(" Password ");
				password=scanner.nextLine();
				scanner.nextLine();
				controller.loginCustomer(userId, password);
			}
			
			
		}
		 else if (choice==3) {
			break;
		}
		else {
			System.out.println("wrong choice pick again");
		}
		
		
		
		
	}
	
	
		
	}

}
