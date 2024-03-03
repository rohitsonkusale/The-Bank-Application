package com.Bank;

import java.util.Date;
import java.util.Scanner;

public class SavingAccount extends Account{
	
	private static double minimum_balance = 10000;
	private static double annual_interest = 0.04;
	final String Account_type = "Saving Account";

	Scanner sc = new Scanner(System.in);
	
/////////////////////////Constructor////////////////////////////////////////////////////////////////////////////
	public SavingAccount(String accountHolder, String accountNumber, Date openingDate, double balance) {
		super(accountHolder, accountNumber, balance);
		// TODO Auto-generated constructor stub
	}

/////////////////////////Setters and Getter//////////////////////////////////////////////////////////////////////
	public static double getAnnual_interest() {
		return annual_interest;
	}

	public static void setAnnual_interest(double annual_interest) {
		SavingAccount.annual_interest = annual_interest;
	}
//////////////////////////Withdrawal Function////////////////////////////////////////////////////////////////////
	@Override
	public void withdrawal(Account account, double amount, String accNumber) {
		if(account != null) {
			if(account.getClosingDate() == null) {			
				if((account.getBalance() - amount) >= minimum_balance) {
					account.setBalance(account.getBalance() - amount);
					System.out.println("\n\t\tWithdrawal successful! New balance: " + account.getBalance());
					System.out.println("\t\t---------------------------------------------\n\n");
					
					//Add withdrawal transaction to transaction list
					withdrawaltransaction(accNumber,new Date(),amount);
				}
				else {
	                System.out.println("\n\n\t\tInsufficient funds. Minimum balance of Rs. 10,000 must be maintained.");
	                System.out.println("\t\t-------------------------------------------------------------------------\n\n");
	            }
			}
			else {
				System.out.println("\t\tAccount with Account Number " + accNumber + " is closed. Withdrawal failed.");
				System.out.println("\t\t----------------------------------------------------------------------------\n\n");
			}
		}
		else {
			System.out.println("Sorry!! Account Not Found in database.");
		}
	}
	
//////////////////////////Interest_Calculation Function////////////////////////////////////////////////////////////////////
	@Override
	public void Interest_Calculation(Account account) {
		// TODO Auto-generated method stub
		double interest = account.getBalance() * SavingAccount.getAnnual_interest();
		double daily_int = (account.getBalance() + interest) / 365;
		double Monthly_int = (account.getBalance() + interest) / 12;
		double Annual_int = account.getBalance() + interest;
		
		System.out.println("\n\n\t\t  --------------------------------------------  ");
		System.out.println("\t\t  |  \t   Interest Calculation\t    \t     |  ");
		System.out.println("\t\t  -------------------------------------------- ");
		System.out.println("\t\t  |                    |                     |            ");
		System.out.println("\t\t  |  Daily Interest    |  "+daily_int+" |  ");
		System.out.println("\t\t  |  Monthly Interest  |  "+Monthly_int+"\t     |  ");
		System.out.println("\t\t  |  Annual Interest   |  "+Annual_int+" \t     |  ");
		System.out.println("\t\t  --------------------------------------------  \n\n");
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void display() {
		super.display();
		System.out.println("\t\t\tAccount Type:\t\t\t"+this.Account_type);
	}
}
