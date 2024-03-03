package com.Bank;

import java.util.Date;
import java.util.Scanner;

public class CurrentAccount extends Account{

	final String Account_type = "Current Account";
	private static double Over_draft_limit = 20000;
	Scanner sc = new Scanner(System.in);
	
////////////////////////////////////Constructor///////////////////////////////////////////////////////////
	public CurrentAccount(String accountHolder, String accountNumber, Date openingDate, double balance) {
		super(accountHolder, accountNumber, balance);
		// TODO Auto-generated constructor stub
	}
	
////////////////////////////Setters and getters////////////////////////////////////////////////////////////
	public static double getOver_draft_limit() {
		return Over_draft_limit;
	}


	public static void setOver_draft_limit(double over_draft_limit) {
		CurrentAccount.Over_draft_limit = over_draft_limit;
	}

/////////////////////////////////Withdrawal////////////////////////////////////////////////////////////////
	@Override
	public void withdrawal(Account account, double amount, String accNumber) {
		// TODO Auto-generated method stub
		if(account != null) {
			if(account.getClosingDate() == null) {
				if(account.getBalance() >= amount) {
					account.setBalance(account.getBalance() - amount);
					System.out.println("\n\t\t  Withdrawal successful! New balance: " + account.getBalance());
					System.out.println("\t\t---------------------------------------------\n\n");
					//Add withdrawal transaction to transaction list
					withdrawaltransaction(accNumber, new Date(),amount);
				}
				else if(account.getBalance() < amount) {
					overDraft(account , amount);
				}
				else {
					System.out.println("\n\t\t  Insufficient funds.");
					System.out.println("\t\t--------------------------\n");
				}
			}
			else {
				System.out.println("Account with Account Number " + accNumber + " is closed. Withdrawal failed.");
			}
		}
		else {
			System.out.println("Account with Account Number " + accNumber + " not found. Withdrawal failed.");
		}
		
	}
//////////////////////////////////////Over-draft function////////////////////////////////////////////////////

	private void overDraft(Account account, double amount) {
		int choice;
		System.out.println("Do you Want to take Over-Draft Facitily");
		System.out.println("Press 1 for 'YES'");
		System.out.println("Press 0 for 'NO'");
		System.out.println("Enter Your Choice:   ");
		choice = sc.nextInt();
		
		if(choice == 1) {
			System.out.println("Your Over-Draft Limit is:  "+CurrentAccount.Over_draft_limit);
			account.setBalance(account.getBalance() + CurrentAccount.Over_draft_limit);
			if(account.getBalance() >= amount) {
				account.setBalance(account.getBalance() - amount);
				System.out.println("Withdrawal successful! New balance: " + account.getBalance());
			}
			else {
				System.out.println("Insufficient funds.");
			}
		}
		else if(choice == 0) {
			System.out.println("Insufficient Balance Withdrawal Transction Failed.");
		}
	}
//////////////////////////////////////Interest-Calculation////////////////////////////////////////////////////
	@Override
	public void Interest_Calculation(Account account) {
		// TODO Auto-generated method stub
		System.out.println("\nSorry!! Bank not provide any Interest on Current Account\n");
	}
	
///////////////////////////////////////////Display/////////////////////////////////////////////////////////

	public void display() {
		super.display();
		System.out.println("\t\t\tAccount Type:\t\t\t"+this.Account_type);
	}

	
}
