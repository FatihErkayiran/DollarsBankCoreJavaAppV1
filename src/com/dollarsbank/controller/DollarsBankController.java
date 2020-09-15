package com.dollarsbank.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.dollarsbank.model.Customer;


public class DollarsBankController {
	
	
	public boolean loginCustomer(int userId,String password) {
		String path=userId + ".data";
		try(ObjectInputStream reader=new ObjectInputStream(new FileInputStream(path))) {
			Customer customer=(Customer)reader.readObject();
			System.out.println("Customer is read " + customer);
			if (customer==null) {
				return false;
			}
				
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return true;

		
		
		
	}
	
	public void createAccount(String name, String address, String contactNumber, int userId, String password,int deposit) {
		
		Customer newCustomer=new Customer(name, address, contactNumber, userId, password,deposit);
		
		File file=new File("resources/" + userId+".data");
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try(ObjectOutputStream writer=new ObjectOutputStream(new FileOutputStream(file))){
			
			writer.writeObject(newCustomer);
			
			
		} catch (FileNotFoundException e) {
			
			
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
