package com.dollarsbank.application;

import java.util.List;
import java.util.Scanner;

import com.dollarsbank.controller.DollarsBankController;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;
import com.mysql.cj.exceptions.WrongArgumentException;


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
						System.out.println(" Welcome " + cuss.getCustomerName());
						boolean state2=true;
						while(state2) {
						
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
							controller.updateEverytime(userId);
							System.out.println("    ");
							System.out.println("Do you want to continue y/n ? ");
							String answer=scanner.next();
							if (answer.equalsIgnoreCase("n".trim())) {
								System.out.println("see you soon");
								state2=false;
								state=false;	
							}
							else {
								System.out.println("what else do you want ");
							}
						}
						else if (accountChoice==2) {
							System.out.println("How much you want to withdraw ");
							int mony=scanner.nextInt();
							scanner.nextLine();
							int balnce=controller.withdrawAmount(mony, cuss);
							controller.updateBalance(balnce, cuss);
							controller.updateEverytime(userId);
							System.out.println("    ");
							System.out.println("Do you want to continue y/n ? ");
							String answer=scanner.next();
							if (answer.equalsIgnoreCase("n".trim())) {
								System.out.println("see you soon");
								state2=false;
								state=false;	
							}
							else {
								System.out.println("what else do you want ");
							}
						}
						else if (accountChoice==3) {
							System.out.println("To which user id do you want to transfer your money");
							int usrId=scanner.nextInt();
							scanner.nextLine();
							System.out.println("how much money do you want to transfer");
							int fund=scanner.nextInt();
							scanner.nextLine();
							int balance=controller.withdrawAmount(fund, cuss);
							controller.updateBalance(balance, cuss);
							Customer cstmr=controller.fundTransfer(usrId, fund);
							int blnc=controller.depositAmount(fund, cstmr);
							controller.updateBalance(blnc, cstmr);
							controller.updateEverytime(userId);
							System.out.println("    ");
							System.out.println("Do you want to continue y/n ? ");
							String answer=scanner.next();
							if (answer.equalsIgnoreCase("n".trim())) {
								System.out.println("see you soon");
								state2=false;
								state=false;	
							}
							if (answer.equalsIgnoreCase("y".trim())) {
								System.out.println("what else do you want ");
							}
							
							
						}
						else if (accountChoice==4) {
							List<Account>accounts=controller.recentTransactions(userId);
							System.out.println(accounts + "\n");
							System.out.println("Do you want to continue y/n ? ");
							String answer=scanner.next();
							if (answer.equalsIgnoreCase("n".trim())) {
								System.out.println("see you soon");
								state2=false;
								state=false;	
							}
							else {
								System.out.println("what else do you want ");
							}
							
							
						}
						else if (accountChoice==5) {
							Customer cstmrr=controller.loginCustomer(userId, password);
							System.out.println(cstmrr);
							System.out.println("Do you want to continue y/n ? ");
							String answer=scanner.next();
							if (answer.equalsIgnoreCase("n".trim())) {
								System.out.println("see you soon");
								state2=false;
								state=false;	
							}
							else {
								System.out.println("what else do you want ");
							}
						}
						else if (accountChoice==6) {
							System.out.println("see you soon");
							state2=false;
							state=false;
						}
						else {
							System.out.println("wrong choice please choose between 1-6");
						}
						
						}
					}
			else  {
				System.out.println("Invalid Credentials,Try again");
				System.out.println(" Enter Login Details");
				System.out.println(" User ID");
				userId=scanner.nextInt();	
				scanner.nextLine();
				System.out.println(" Password ");
				password=scanner.nextLine();
				//scanner.nextLine();
				//controller.loginCustomer(userId, password);
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
