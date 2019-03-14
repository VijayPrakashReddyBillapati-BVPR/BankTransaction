package com.capgemini.bank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.capgemini.bank.bean.Registration;
import com.capgemini.bank.ui.BankUI;
import com.capgemini.bank.utility.DataBaseConnection;

public class RegistrationDaoImpl implements IRegistrationDao{
	 DataBaseConnection  database= new  DataBaseConnection();
	 Connection connect=null;
	 Scanner scan = new Scanner(System.in);
		BankUI mainui= new BankUI();
	public Integer register(Registration registration) {
		
		Integer accountNumber=0;
		
		try {
//(Account_no number(16) , first_name varchar2(30) , last_name varchar2(30) , email varchar2(30) , password varchar2(10) , pancard_no number(16) , aadhar_no number(12) , address varchar2(30) , mobile_no number(10) , balance number			
			connect=database.dBConnection();
			PreparedStatement  PreparedStatement=connect.prepareStatement("insert into CustomerDetails values (?,?,?,?,?,?,?,?,?)");
			PreparedStatement.setString(1, registration.getFirstName());
			PreparedStatement.setString(2, registration.getLastName());
			PreparedStatement.setString(3, registration.getEmail());
			PreparedStatement.setInt(4, registration.getPassword());
			PreparedStatement.setInt(5, registration.getPanCard());
			PreparedStatement.setLong(6, registration.getAadharNumber());
			PreparedStatement.setString(7, registration.getAddress());
			PreparedStatement.setInt(8, registration.getMobileNumber());
			PreparedStatement.setInt(9, registration.getBalance());
			PreparedStatement.executeUpdate();
			PreparedStatement  PreparedStatement1=connect.prepareStatement("select Account_no from CustomerDetails where aadhar_no=? ");
			PreparedStatement1.setLong(1, registration.getAadharNumber());
			ResultSet result= PreparedStatement1.executeQuery();
			
			
			while(result.next())
			{
				accountNumber=result.getInt(1);
			}
			
			mainui.firstCalled();
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		return accountNumber;
	}

	public void login() {
		try {
			
			connect=database.dBConnection();
			System.out.println("enter account number");
			Integer accno=scan.nextInt();
			System.out.println("enter password");
			Integer pass= scan.nextInt();
			Integer password =0 ;
			PreparedStatement  PreparedStatement=connect.prepareStatement("select  password from CustomerDetails where Account_no=? ");
			PreparedStatement.setInt(1, accno);
			ResultSet result =PreparedStatement.executeQuery();
			while(result.next()) {
				password=result.getInt("password");
			}
			if(password==pass) {
				
				mainui.selectTransaction(accno);
			}else {
				login();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
