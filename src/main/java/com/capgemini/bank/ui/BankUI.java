/**
 * 
 */
package com.capgemini.bank.ui;

import java.util.Scanner;

import com.capgemini.bank.bean.Registration;
import com.capgemini.bank.service.IRegistrationService;
import com.capgemini.bank.service.ITransactionService;
import com.capgemini.bank.service.RegistrationServiceImpl;
import com.capgemini.bank.service.TransactionServiceImpl;

/**
 * @author VJIT
 *
 */
public class BankUI {

	/**
	 * @param args
	 */
	Scanner scan = new Scanner(System.in);
	ITransactionService TransactionService= new TransactionServiceImpl();
	static IRegistrationService RegistrationService= new RegistrationServiceImpl();
	public void selectTransaction(Integer accountNumber) {
		System.out.println("select transaction"+"\n"+"1.WithDraw"+"\n"+"2.Deposit"+"\n"+"transferFunds"+"\n"+"Balance");
		Scanner scan = new Scanner(System.in);
		Integer option = scan.nextInt();
		switch(option) {
		
		case 1: System.out.println("enter the amount to proceed transaction");
				Integer balance=scan.nextInt();
		
				TransactionService.withDraw(accountNumber , balance);
				break;
		case 2:System.out.println("enter the amount to proceed transaction");
				Integer balance1=scan.nextInt(); 
				TransactionService.deposit(accountNumber ,balance1 ); 
				break;
		case 3: System.out.println("enter the amount to proceed transaction");
				Integer balance2=scan.nextInt();
				System.out.println("enter the account to proceed transaction");
				Integer account1=scan.nextInt();
				Integer transfered=TransactionService.transferFunds(accountNumber ,balance2 ,account1); 
				System.out.println("Rs"+balance2+" debited from "+accountNumber+" and "+transfered+" credited into account "+account1);
				break;
		case 4:System.out.println("enter the amount to proceed transaction");
				Integer balance3=scan.nextInt(); 
				TransactionService.showBalance(accountNumber ,balance3); break;
		case 5: System.exit(0);
		}
		
	}
	public Registration RegisterAccount()
	{
		Registration registration= new Registration();
		
		System.out.println("enter accountnumber");
		Integer accountNumber=scan.nextInt();
		registration.setAccountNumber(accountNumber);
		
		System.out.println("enter firstName");
		String firstName=scan.next();
		registration.setFirstName(firstName);
		
		System.out.println("enter lastName");
		String lastName=scan.next();
		registration.setLastName(lastName);
		
		System.out.println("enter password");
		Integer password=scan.nextInt();
		registration.setPassword(password);
		
		System.out.println("enter email");
		String email=scan.next();
		registration.setEmail(email);
		
		System.out.println("enter panCard");
		Integer panCard=scan.nextInt();
		registration.setPanCard(panCard);
		
		System.out.println("enter aadharNumber");
		long aadharNumber=scan.nextLong();
		registration.setAadharNumber(aadharNumber);
		
		System.out.println("enter address");
		String address=scan.next();
		registration.setAddress(address);
		
		System.out.println("enter mobileNumber");
		Integer mobileNumber=scan.nextInt();
		registration.setMobileNumber(mobileNumber);
		
		System.out.println("enter balance");
		Integer balance=scan.nextInt();
		registration.setBalance(balance);
		
		
		return registration;
		
	}
	public void firstCalled()
	{
		Scanner scan = new Scanner(System.in);
		BankUI BankUI= new BankUI();
		System.out.println("select one operation"+"\n"+"1.REGISTER"+"\n"+"2.LOGIN");
		Integer option = scan.nextInt();
		switch(option) {
		case 1: Integer accNo=RegistrationService.register(BankUI.RegisterAccount() );
			System.out.println("your Account Number is "+accNo);
				break;
		case 2:RegistrationService.login();
				break;
		case 3:System.exit(0);
		}
	}
	
	public static void main(String[] args) {
		BankUI BankUI= new BankUI();
		BankUI.firstCalled();

	}

}
