package com.capgemini.bank.service;

public interface ITransactionService {
	public void withDraw(Integer accountNumber ,Integer balance);
	public void deposit(Integer accountNumber ,Integer balance);
	public Integer transferFunds(Integer accountNumber ,Integer balance ,Integer accountNumber1);
	public Integer showBalance(Integer accountNumber ,Integer balance);
	

}
