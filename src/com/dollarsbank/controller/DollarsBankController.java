package com.dollarsbank.controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.dollarsbank.connection.BetterConnectionManager;
import com.dollarsbank.model.Account;
import com.dollarsbank.model.Customer;



public class DollarsBankController {
	
	
	Connection connection=BetterConnectionManager.getConnection();
	
	public int getBalance(int customerId) {
		
		int balance=-1;
		
		try(PreparedStatement pstmt=connection.prepareStatement("select * from customers where userId = ? ")) {
			
			pstmt.setInt(1, customerId);
			
			ResultSet rs=pstmt.executeQuery();
			
			if (rs.next()) {
				balance=rs.getInt("balance");
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		return balance;
	
	}
	
	
	public Customer loginCustomer(int userId,String password) {
		
		Customer cuss=null;
		
		try(PreparedStatement pstmt=connection.prepareStatement("select * from customers where userId = ? and customerPassword = ?")) {
			
			pstmt.setInt(1, userId);
			pstmt.setString(2, password);
			
			ResultSet rs=pstmt.executeQuery();
			
			if (rs.next()) {
				   String cName=rs.getString(1);
				   String addres=rs.getString(2);
				   String cNumber=rs.getString(3);
				   int id =rs.getInt(4);
				   String pass=rs.getString(5);
				   int depo=rs.getInt(6);
				   int balnce=rs.getInt(7);
				   
				   cuss=new Customer(cName, addres, cNumber, id, pass, depo,balnce);
			
			
			
			

			
			}
			
		} catch (Exception e) {
		
		}
		return cuss;	
	}
	
	public boolean createAccount(Customer customer) {
		
		try {
			PreparedStatement pstmt=connection.prepareStatement("insert into customers values (?,?,?,?,?,?,?)");
		
			pstmt.setString(1, customer.getCustomerName());
			pstmt.setString(2, customer.getAddress());
			pstmt.setString(3, customer.getContactNumber());
			pstmt.setInt(4, customer.getUserId());
			pstmt.setString(5, customer.getPassword());
			pstmt.setInt(6, customer.getDeposit());
			pstmt.setInt(7, customer.getBalance());
			
			int result=pstmt.executeUpdate();
			if (result>0) {
				return true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;
}
	
	public int depositAmount(int money,int customerId) {
		
		LocalDate localDate=LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(localDate );
		int balance=getBalance(customerId);
		try {
			PreparedStatement pstmt=connection.prepareStatement("insert into transactions values(?,?,?,?)");
		
			balance+=money;
			
			pstmt.setInt(1, balance);
			pstmt.setInt(2, money);
			pstmt.setDate(3,sqlDate );
			pstmt.setInt(4, customerId);
					
			int query=pstmt.executeUpdate();
			
			if (query>0 ) {
				
			  
				System.out.println("your available balance " + balance);
			}	

			
			pstmt.close();
		
			
		} catch (Exception e) {
			
		}
		return balance;
		
		
		
	}
	
	public void updateBalance(int balance,Customer customer) {
		try {
			
			PreparedStatement pstmt2=connection.prepareStatement("update customers set balance = ? where userId = ?");
			
			pstmt2.setInt(1, balance);
			pstmt2.setInt(2, customer.getUserId());
			
			int count=pstmt2.executeUpdate();

			System.out.println("updated balance of " + count + " account succesfully ");
			pstmt2.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
	
	
	
	public int withdrawAmount(int money,int customerId) {
		LocalDate localDate=LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(localDate );
		int balance=getBalance(customerId);
		try(PreparedStatement pstmt=connection.prepareStatement("insert into transactions values(?,?,?,?)")) {
			
			balance-=money;
			
			pstmt.setInt(1, balance);
			pstmt.setInt(2, money);
			pstmt.setDate(3,sqlDate );
			pstmt.setInt(4, customerId);
			
			int query=pstmt.executeUpdate();
			
			if (query>0) {
				
				System.out.println("your available balance " + balance);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return balance;
	}
	public Customer fundTransfer(int usrId,int fund) {
		Customer cuss=null;
		try(PreparedStatement pstmt=connection.prepareStatement("select * from customers where userId = ?")) {
			
			pstmt.setInt(1, usrId);
			
			ResultSet rs=pstmt.executeQuery();
			
			if (rs.next()) {
				   String cName=rs.getString(1);
				   String addres=rs.getString(2);
				   String cNumber=rs.getString(3);
				   int id =rs.getInt(4);
				   String pass=rs.getString(5);
				   int depo=rs.getInt(6);
				   int balnce=rs.getInt(7);
				   
				   cuss=new Customer(cName, addres, cNumber, id, pass, depo,balnce);
			 }
		} catch (Exception e) {
			// TODO: handle exception
		}
		return cuss;
	}
	
	public List<Account> recentTransactions(int userId) {
		List<Account>accounts=new ArrayList<>();
		Account account=null;
		try(PreparedStatement pstmt=connection.prepareStatement("select * from transactions where user_Id = ? limit 5")) {
			
			pstmt.setInt(1, userId);
			
			ResultSet rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int balance=rs.getInt(1);
				int moneyTransaction=rs.getInt(2);
				Date dateOfTransaction=rs.getDate(3);
				int user_Id=rs.getInt(4);
				
				account=new Account(balance, moneyTransaction, dateOfTransaction, user_Id);
				accounts.add(account);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return accounts;
	}
	



}
