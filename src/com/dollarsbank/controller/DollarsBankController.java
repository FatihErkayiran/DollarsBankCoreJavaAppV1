package com.dollarsbank.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import com.dollarsbank.connection.BetterConnectionManager;
import com.dollarsbank.model.Customer;







public class DollarsBankController {
	
	
	Connection connection=BetterConnectionManager.getConnection();
	
	
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
			
			
			
			
	//		if (cuss!=null) {
	//			return cuss;
	//		}
			
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
			// TODO: handle exception
		}
		return false;
}
	
	public int depositAmount(int money,Customer customer) {
		
		LocalDate localDate=LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(localDate );
		int balance=customer.getBalance();
		try {
			PreparedStatement pstmt=connection.prepareStatement("insert into transactions values(?,?,?,?)");
		
			balance+=money;
			
			pstmt.setInt(1, balance);
			pstmt.setInt(2, money);
			pstmt.setDate(3,sqlDate );
			pstmt.setInt(4, customer.getUserId());
					
			int query=pstmt.executeUpdate();
			
			if (query>0 ) {
				
			  
				System.out.println("your available balance " + balance);
			}	
//			else {
//				System.out.println("ne olacak simdi ");
//		     }
			
//			PreparedStatement pstmt2=connection.prepareStatement("update customers set balance = ? where userId = ?");
//			
//			pstmt2.setInt(1, balance);
//			pstmt2.setInt(2, customer.getUserId());
//			
//			ResultSet rs=pstmt2.executeQuery();
//			
//			if (rs.next()) {
//				System.out.println(" you got it ");
//			}
			
			pstmt.close();
		//	pstmt2.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return balance;
		
		
		
	}
	
	public void updateBalance(int balance,Customer customer) {
		try {
			
			PreparedStatement pstmt2=connection.prepareStatement("update customers set balance = ? where userId = ?");
			
			pstmt2.setInt(1, balance);
			pstmt2.setInt(2, customer.getUserId());
			
			int count=pstmt2.executeUpdate();
//			
//			if (rs.next()) {
//				System.out.println(" you got it ");
//			}
			System.out.println("updated balance of " + count + " succesfully ");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
	
	
	
	public void withdrawAmount(int money,Customer customer) {
		LocalDate localDate=LocalDate.now();
		java.sql.Date sqlDate = java.sql.Date.valueOf(localDate );
		try(PreparedStatement pstmt=connection.prepareStatement("insert into transactions values(?,?,?,?)")) {
			int balance=customer.getBalance();
			balance-=money;
			
			pstmt.setInt(1, balance);
			pstmt.setInt(2, money);
			pstmt.setDate(3,sqlDate );
			pstmt.setInt(4, customer.getUserId());
			
			int query=pstmt.executeUpdate();
			
			if (query>0) {
				
				System.out.println("your available balance " + balance);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}

}
