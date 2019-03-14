package com.capgemini.bank.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import com.capgemini.bank.Exception.InvalidBalanceException;
import com.capgemini.bank.utility.DataBaseConnection;

public class TransactionDaoImpl implements ITransactionDao {
	Connection connect=null;
	DataBaseConnection database=new DataBaseConnection();
	
	public Integer receiveBalance(Integer accountNumber)
	{
		Integer balance=0;
		try {
			connect=database.dBConnection();
			PreparedStatement  PreparedStatement=connect.prepareStatement("select balance from CustomerDetails where Account_no=?");
			PreparedStatement.setInt(1, accountNumber);
			ResultSet result = PreparedStatement.executeQuery();
			
			
			while(result.next())
			{
				balance=result.getInt("balance");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return balance;
		
	}

	

	@Override
	public void withDraw(Integer accountNumber ,Integer balance) {
		Integer amount=receiveBalance(accountNumber);
		
		if(amount>balance) {
			amount=amount-balance;
			try {
				connect=database.dBConnection();
				PreparedStatement  PreparedStatement=connect.prepareStatement("alter table  CustomerDetails set balance=? where Account_no=?");
				PreparedStatement.setInt(1, amount);
				
				PreparedStatement.setInt(2, accountNumber);
				PreparedStatement.executeUpdate();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else {
			try {
				throw new InvalidBalanceException("enter correct  balance ");
			} catch (InvalidBalanceException e) {
				
				e.printStackTrace();
			}
		}
		
		
		
	}

	@Override
	public void deposit(Integer accountNumber ,Integer balance) {
		Integer amount=receiveBalance(accountNumber);
		amount=amount+balance;
		try {
			connect=database.dBConnection();
			PreparedStatement  PreparedStatement=connect.prepareStatement("alter table  CustomerDetails set balance=? where Account_no=?");
			PreparedStatement.setInt(1, amount);
			
			PreparedStatement.setInt(2, accountNumber);
			PreparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public Integer transferFunds(Integer accountNumber ,Integer balance ,Integer accountNumber1) {
		Integer amount=receiveBalance(accountNumber);
		if(amount>balance) {
			amount=amount-balance;
			try {
				connect=database.dBConnection();
				PreparedStatement  PreparedStatement=connect.prepareStatement("alter table  CustomerDetails set balance=? where Account_no=?");
				PreparedStatement.setInt(1, amount);
				PreparedStatement.setInt(2, accountNumber);
				PreparedStatement.executeUpdate();
				
				Integer bal=receiveBalance(accountNumber1);
				bal+=balance;
				connect=database.dBConnection();
				PreparedStatement  PreparedStatement1=connect.prepareStatement("alter table  CustomerDetails set balance=? where Account_no=?");
				PreparedStatement1.setInt(1, bal);
				PreparedStatement1.setInt(2, accountNumber1);
				PreparedStatement1.executeUpdate();
				
				Random random= new Random();
				connect=database.dBConnection();
				PreparedStatement  PreparedStatement2=connect.prepareStatement("Insert into transaction_Details values(?,?,?,? )");
				PreparedStatement2.setInt(1, random.nextInt(999999999));
				PreparedStatement2.setInt(2, accountNumber);
				PreparedStatement2.setInt(3, accountNumber1);
				PreparedStatement2.setInt(4,balance );
				PreparedStatement2.executeUpdate();
				
				
				
				
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return amount;
			
		}
		else {
			try {
				throw new InvalidBalanceException("enter correct  balance ");
			} catch (InvalidBalanceException e) {
				
				e.printStackTrace();
			}
		}
		return 0;
		
		
	}

	@Override
	public Integer showBalance(Integer accountNumber ,Integer balance) {
		Integer amount=receiveBalance(accountNumber);
		
		return amount;
	}

}
