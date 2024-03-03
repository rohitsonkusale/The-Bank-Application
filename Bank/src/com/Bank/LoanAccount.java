package com.Bank;

import java.util.Date;
import java.util.Scanner;

public class LoanAccount extends Account {
	
	private static double annual_interest = 0.11;
	final String Account_type = "Loan Account";

////////////////////////////////////constructor///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public LoanAccount(String accountHolder, String accountNumber, Date openingDate, double balance) {
		super(accountHolder, accountNumber, 0);
		// TODO Auto-generated constructor stub
	}
/////////////////////////////////////////Setters and Getters//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static double getAnnual_interest() {
		return annual_interest;
	}

	public static void setAnnual_interest(double annual_interest) {
		LoanAccount.annual_interest = annual_interest;
	}
	
/////////////////////////////////////////////Loan-Sanction Amount/////////////////////////////////////////////////////////////////////////////////////////////////////\
	
	@SuppressWarnings("resource")
	private static int AmountSanction(Account account, double salary) {
		Scanner sc = new Scanner(System.in);
		if(salary < 30000) {
			System.out.println("\n\t\tSorry!! You are not eligible for loan");
			return -1;
		}
		if(salary > 30000 && salary <= 80000) {
			System.out.println("\n\t\tBased on your Salary, you are eligible to take loan of '10,00,000'.");
			
			int choice;
			System.out.println("\n\t\t--Press 1 to take Loan");
			System.out.println("\t\t--Press 0 for Main Menu");
			choice = sc.nextInt();
			
			if(choice == 1) {
				account.setBalance(account.getBalance() + 1000000);
				System.out.println("\n\t\t   --Congratulations-- Your Loan Ammount Is Credited To Your Account.");
				System.out.println("\t\t-------------------------------------------------------------------------\n\n");
				System.out.println("\n Available balance:   "+account.getBalance());
			}
			return 0;
		}
		if(salary > 80000 && salary <= 150000) {
			System.out.println("\n\t\tBased on your Salary, you are eligible to take loan of '25,00,000'.");
			
			int choice;
			System.out.println("\n\t\t--Press 1 to take Loan");
			System.out.println("\t\t--Press 0 for Main Menu");
			choice = sc.nextInt();
			
			if(choice == 1) {
				account.setBalance(account.getBalance() + 2500000);
				System.out.println("\n\t\t   --Congratulations-- Your Loan Ammount Is Credited To Your Account.");
				System.out.println("\t\t-------------------------------------------------------------------------\n\n");
				System.out.println("\n Available balance:   "+account.getBalance());
			}
			return 0;
		}
		if(salary > 150000 && salary <= 250000) {
			System.out.println("\n\t\tBased on your Salary, you are eligible to take loan of '50,00,000'.");
			
			int choice;
			System.out.println("\n\t\t--Press 1 to take Loan");
			System.out.println("\t\t--Press 0 for Main Menu");
			choice = sc.nextInt();
			
			if(choice == 1) {
				account.setBalance(account.getBalance() + 5000000);
				System.out.println("\n\t\t   --Congratulations-- Your Loan Ammount Is Credited To Your Account.");
				System.out.println("\t\t-------------------------------------------------------------------------\n\n");
				System.out.println("\n Available balance:   "+account.getBalance());
			}
			return 0;
		}
		return -1;
	}

//////////////////////////////////////////Calculate EMI Function///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	private static int calculateEMI(double loanAmount, int tenureInYears) {
	    // Convert tenure from years to months
	    int tenureInMonths = tenureInYears * 12;

	    // Monthly interest rate
	    double monthlyRate = annual_interest / 12 / 100;

	    // EMI formula: EMI = [P * r * (1+r)^n] / [(1+r)^n - 1]
	    int emi = (int) ((loanAmount * monthlyRate * Math.pow(1 + monthlyRate, tenureInMonths))
	            / (Math.pow(1 + monthlyRate, tenureInMonths) - 1));

	    return emi;
	}
////////////////////////////////////Loan-RePayment Function//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void makeLoanRepayment(Account account, String accNumber) {
	    Scanner sc = new Scanner(System.in);

	    account.deposit(account, annual_interest, accNumber);
	    // Variables for loan details (replace these placeholders with actual values)
//	    double loanAmountSanctioned = /* get the loan amount sanctioned based on salary */;
	    int tenureInYears = 1;/* specify the tenure in years */;
//
//	    // Check if the loan amount was sanctioned using AmountSanction
//	    double salary = /* get the salary for the account */;
	    int sanctionResult = AmountSanction(account, salary);

	    if (sanctionResult == 0) {
	        // Calculate EMI
	        int emi = calculateEMI(loanAmountSanctioned, tenureInYears);

	        // Display EMI details
	        System.out.println("\n\t\tEMI Details:");
	        System.out.println("\t\tLoan Amount: " + loanAmountSanctioned);
	        System.out.println("\t\tEMI: " + emi);

	        // Repayment
	        double amount;
	        System.out.print("\n\t\tEnter Amount for Repayment: ");
	        amount = sc.nextDouble();

	        if (amount <= 0) {
	            System.out.println("\n\t\tInvalid repayment amount. Please enter a positive amount.");
	        } else if (amount > account.getBalance()) {
	            System.out.println("\n\t\tInvalid repayment amount. Repayment amount exceeds the remaining loan balance.");
	        } else {
	            // Decrease the loan balance (repayment)
	            account.setBalance(account.getBalance() - amount);

	            // Update last transaction date
	            setLastTransaction(new Date());

	            // Add withdrawal transaction to transaction list
	          //  withdrawaltransaction(accNumber, new Date(), amount);

	            // Display remaining loan and EMI
	            double remainingLoan = loanAmountSanctioned - account.getBalance();
	            System.out.println("\n\t\tLoan repayment successful!");
	            System.out.println("\t\tRemaining Loan: " + remainingLoan);
	            System.out.println("\t\tRemaining EMI: " + (tenureInYears * 12 - 1)); // Assuming one EMI is paid with each repayment
	        }
	    } else {
	        System.out.println("\n\t\tLoan sanction failed. Cannot proceed with repayment.");
	    }
	}


////////////////////////////////////withdrawal//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void withdrawal(Account account, double amount, String accNumber) {
		// TODO Auto-generated method stub
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		int choice;
		System.out.println("\n\t\t--Press 1 to Apply For Loan");
		System.out.println("\t\t--Press 0 For Main Menu\n");
		System.out.print("\n\t\tEnter Your  Choice:   ");
		choice = sc.nextInt();
		
		if(choice == 1) {
			double salary;
			System.out.print("\n\t\tEnter Your Salary:   ");
			salary = sc.nextDouble();
			
			int a = LoanAccount.AmountSanction(account, salary);
			if(a == 0) {
				if(account.getBalance() > amount) {
					account.setBalance(account.getBalance() - amount);
					System.out.println("\n\t\t  Withdrawal successful! New balance: " + account.getBalance());
					System.out.println("\t\t---------------------------------------------\n\n");
					account.setLastTransaction(new Date());//to store last-transaction date
					withdrawaltransaction(accNumber, new Date(),amount);
				}
			}
		}
	}
	
////////////////////////////////////Interest_Calculation/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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

////////////////////////////////////Display//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void display() {
		super.display();
		System.out.println("\t\t\tAccount Type:\t\t\t"+this.Account_type);
	}
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	
	
	
	
	
	
	
	
	
	
	
	
	