package com.Bank;

import java.util.Date;

public class SalaryAccount extends Account {
	private static double annual_interest = 0.04;
	final String Account_type = "Salary Account";

////////////////////////////////////////constructor//////////////////////////////////////////////////////	
	public SalaryAccount(String accountHolder, String accountNumber, Date openingDate, double balance) {
		super(accountHolder, accountNumber, balance);
		// TODO Auto-generated constructor stub
	}
	
	public static double getAnnual_interest() {
		return annual_interest;
	}

	public static void setAnnual_interest(double annual_interest) {
		SalaryAccount.annual_interest = annual_interest;
	}

////////////////////////////////////withdrawal///////////////////////////////////////////////////////////
	@Override
	public void withdrawal(Account account, double amount, String accNumber) {
		if(!Freeze(account, accNumber)) {
			if(account != null) {
				if(account.getClosingDate() == null) {			
					account.setBalance(account.getBalance() - amount);
					System.out.println("\n\t\t  Withdrawal successful! New balance: " + account.getBalance());
					System.out.println("\t\t---------------------------------------------\n\n");
					account.setLastTransaction(new Date());//to store last-transaction date
					
					//Add withdrawal transaction to transaction list
					withdrawaltransaction(accNumber, new Date(),amount);
				}
				else {
					System.out.println("Account with Account Number " + accNumber + " is closed. Withdrawal failed.");
				}
			}
			else {
				System.out.println("Sorry!! Account Not Found in database.");
			}
		}
		else {
			System.out.println("Account is frozen. Withdrawal not allowed.");
		}
	}
///////////////////////////////////Interest_Calculation/////////////////////////////////////////////////
	@Override
	public void Interest_Calculation(Account account) {
		// TODO Auto-generated method stub
		double interest = account.getBalance() * SalaryAccount.getAnnual_interest();
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

////////////////////////////////////////////Display//////////////////////////////////////////////////////
	
	public void display() {
		super.display();
		System.out.println("\t\t\tAccount Type:\t\t\t"+this.Account_type);
	}

}

/*
 * A Salary account is exactly similar to a savings account except that it has a
 * time limit in which at least one transaction should have taken place. If no
 * transactions happen on a salary account for two months, the account is frozen
 * and the account holder is notified.
 */
