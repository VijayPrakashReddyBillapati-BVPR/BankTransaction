package com.capgemini.bank.service;

import com.capgemini.bank.Dao.ITransactionDao;
import com.capgemini.bank.Dao.TransactionDaoImpl;

public class TransactionServiceImpl implements ITransactionService {
	
	ITransactionDao transactionDao= new TransactionDaoImpl();

	

	@Override
	public void withDraw(Integer accountNumber, Integer balance) {
		// TODO Auto-generated method stub
		transactionDao.withDraw(accountNumber,  balance);
	}

	@Override
	public void deposit(Integer accountNumber, Integer balance) {
		transactionDao.deposit(accountNumber,  balance);
	}

	@Override
	public Integer transferFunds(Integer accountNumber, Integer balance, Integer accountNumber1) {
		return transactionDao.transferFunds(accountNumber,balance,  accountNumber1);
		
	}

	@Override
	public Integer showBalance(Integer accountNumber, Integer balance) {
		return transactionDao.showBalance(accountNumber,  balance);
		
	}

}
