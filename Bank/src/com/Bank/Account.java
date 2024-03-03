package com.Bank;

import java.util.Date;
import java.util.Scanner;

public abstract class Account {

	String AccountHolder;
	String AccountNumber;
	final Date openingDate;
	Date closingDate = null;
	Date lastTransaction = null;
	boolean Frozen = false;
	double balance;

	Scanner sc = new Scanner(System.in);

////////////////////////////////Constructor//////////////////////////////////////////////////////////////////////////
	public Account(String accountHolder, String accountNumber, double balance) {
		super();
		AccountHolder = accountHolder;
		AccountNumber = accountNumber;
		this.openingDate = new Date();
		this.lastTransaction = openingDate;
		this.balance = balance;
	}

//////////////////////////////Setters And Getters/////////////////////////////////////////////////////////////////////////
	public String getAccountHolder() {
		return AccountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		AccountHolder = accountHolder;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}

	public Date getClosingDate() {
		return closingDate;
	}

	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getOpeningDate() {
		return openingDate;
	}

	public Date getLastTransaction() {
		return lastTransaction;
	}

	public void setLastTransaction(Date lastTransaction) {
		this.lastTransaction = lastTransaction;
	}

	public boolean isFrozen() {
		return Frozen;
	}

	public void setFrozen(boolean frozen) {
		Frozen = frozen;
	}

/////////////Transaction Array to store transactions////////////////////////////////////////////////////////////////////////////////////////////////

	public static Transaction[] transactions = new Transaction[1000];
	public static int transactionIndex = 0;

///////////////////////////////////dailyReport Function//////////////////////////////////////////////////////////////////////////////////////  

	public static void dailyReport() {
		Date currentDate = new Date();
		System.out.println("\n\n\t\t\tDaily Report for " + currentDate + "\n");
		System.out.println("\t\t--------------------------------------------------------------------------------------------------------------------------------");
		System.out.println("\t\t\tAccount Number          \tType              \t\tDate              \t\t\tAmount");
		System.out.println("\t\t--------------------------------------------------------------------------------------------------------------------------------");
		for (int i = 0; i < transactionIndex; i++) {
			Transaction transaction = transactions[i];
			System.out.println("\t\t\t"+transaction.getAccountNumber() + "\t\t\t" + transaction.getTransactionType() + "\t\t\t" + transaction.getTransactionDate() + "\t\t\t" + transaction.getAmount());
			System.out.println("\t\t--------------------------------------------------------------------------------------------------------------------------------");
		}
	}

///////////////////////////Deposit Function////////////////////////////////////////////////////////////////////////////////////////////////
	final public void deposit(Account account, double amount, String accNumber) {
		if (!Freeze(account, accNumber)) {
			if (account != null) {
				if (account.getClosingDate() == null) {
					if (amount <= 0) {
						System.out.println("\tInvalid!! Amount should be greater than 'Zero'");
					} else {
						account.setBalance(account.getBalance() + amount);
						System.out.println("\n\n\t\tDeposit successful! New balance: " + account.getBalance());
						System.out.println("\t\t---------------------------------------------\n\n");
						account.setLastTransaction(new Date());// to store last-transaction date

						// Add deposit transaction to transaction list
						deposittransaction(accNumber, new Date(), "   Deposit", amount);
						//Transaction depositTransaction = new Transaction(accNumber, new Date(), "Deposit", amount);
					}
				} else {
					System.out.println("\n\n\t\tAccount with Account Number " + accNumber + " is closed. Deposit failed.");
					System.out.println("\t\t----------------------------------------------------------------------------\n\n");
					
				}
			} else {
				System.out.println("\t\tAccount with Account Number " + accNumber + " not found. Deposit failed.");
				System.out.println("\t\t------------------------------------------------------------------------\n\n");
			}
		} else {
			System.out.println("\t\tAccount is frozen. Deposit not allowed.");
			System.out.println("\t\t---------------------------------------\n\n");
		}
	}
//////////////////////////////withdrawal Function//////////////////////////////////////////////////////////////////

	public abstract void withdrawal(Account account, double amount, String accNumber);

/////////////////////////////////Close_Account Function///////////////////////////////////////////////////////////////////////

	public static void Close_Account(Account account) {
		if (account != null) {
			account.setClosingDate(new Date());
			System.out.println("\n\n\t\tYour Account Has Sucessfully Closed!!");
			System.out.println("\t\t--------------------------------------\n\n");
		} else {
			System.out.println("Sorry!! Your Account Not Found in our Database.");
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////

	public abstract void Interest_Calculation(Account account);

////////////////////////////Account_Lifecycle Function////////////////////////////////////////////////////////////////////////////

	public static void Account_Lifecycle(Account account, String accNumber) {

		if (account != null) {
			if (account.getClosingDate() == null) {
				// Account is active
				System.out.println("\n\n\t\t----------------------------------------------------------------------------");
				System.out.println("\t\t\t\t    Account_Lifecycle               ");
				System.out.println("\t\t----------------------------------------------------------------------------");
				System.out.println("\t\t                                                   ");
				System.out.println("\t\t\tAccount is currently active.                ");
				System.out.println("\t\t\t-----------------------------               ");
				System.out.println("\t\t                                                   ");
				System.out.println("\t\t\tOpening Date:\t\t\t" + account.getOpeningDate()+"  ");
				System.out.println("\t\t\tLast Transaction Date:\t\t" + account.getLastTransaction()+"  ");
				account.display();
				System.out.println("\t\t-------------------------------------------------------------------------------\n");
			} else {
				// Account is not active
				System.out.println("\n\n\t\t----------------------------------------------------------------------------");
				System.out.println("\t\t\t\t    Account_Lifecycle               ");
				System.out.println("\t\t----------------------------------------------------------------------------");
				System.out.println("\t\t                                                   ");
				System.out.println("\t\t\tAccount is Closed.                ");
				System.out.println("\t\t\t-------------------               ");
				System.out.println("\t\t                                                   ");
				System.out.println("\t\t\tOpening Date:\t\t\t" + account.getOpeningDate()+"  ");
				System.out.println("\t\t\tClosing Date:\t\t\t" + account.getClosingDate());
				System.out.println("\t\t\tLast Transaction Date:\t\t" + account.getLastTransaction()+"  ");
				account.display();
				System.out.println("\t\t-------------------------------------------------------------------------------\n");
			}
		} else {
			System.out.println("Account with Account Number " + accNumber + " not found.");
		}
	}

//////////////////////////////////Fund-Transfer Function///////////////////////////////////////////////////////////////////////

	public static void Fund_Transfer(Account my_account,double amount, String myAccNum, String another_acc) {
		if (!Freeze(my_account, myAccNum)) {
			if (my_account != null) {
				if (my_account.getClosingDate() == null) {
					if (my_account.getBalance() >= amount) {
						
						my_account.setBalance(my_account.getBalance() - amount);
						System.out.println("\n\t\t  Funds Transfered Sucessfuly.");
						System.out.println("\t\t--------------------------------\n");
						System.out.println("\t\t   Available Balance:   " + my_account.getBalance());
						System.out.println("\t\t--------------------------------\n\n");
						my_account.setLastTransaction(new Date()); // to store last-transaction date

					} else {
						System.out.println("\n\t\tInsufficient Balance.");
					}
					
				} else {
					System.out.println("\n\t\tAccount with Account Number " + myAccNum + " is closed. Fund-Transfer failed.");
				}
					
			} else {
				System.out.println("\n\t\tSorry!! Your Account not found in our database.");
			}
		} else {
			System.out.println("\n\t\tAccount is frozen. Fund Transfer not allowed.");
		}
	}
	
////////////////////////////////////////////Last-Transaction Function//////////////////////////////////////////////////////

	public static boolean Freeze(Account account, String accNumber) {
		Date currentDate = new Date();

		// Check if the last transaction occurred more than 2 months ago
		long timeDifferenceMillis = currentDate.getTime() - account.getLastTransaction().getTime();
		long twoMonthsMillis = 2 * 30 * 24 * 60 * 60 * 1000; // Assuming a month is 30 days

		if (timeDifferenceMillis > twoMonthsMillis) {
			// Freeze the account or perform any other required actions
			System.out.println("Account with Account Number " + accNumber + " has been frozen due to inactivity.");
			account.setFrozen(true); // Assuming you have a 'frozen' attribute in the Account class
			return true;
		}
		return false;
	}

//////////////////////////////////Display Function///////////////////////////////////////////////////////////////////////

	public void display() {
		System.out.println("\t\t\tAccount Holder Name:\t\t" + this.getAccountHolder());
		System.out.println("\t\t\tAccount Number:\t\t\t" + this.getAccountNumber());
		System.out.println("\t\t\tAccount Curent Balance:\t\t" + this.getBalance());
		// System.out.println("Account Opening Date: "+this.getOpeningDate());
	}


/////////////////////////////Transaction Functions////////////////////////////////////////////////////////////////////////

//Deposit
	public void deposittransaction(String accNumber, Date date, String type, double amount) {
//Add Deposit transaction to transaction list
		Transaction depositTransaction = new Transaction();
		depositTransaction.setAccountNumber(accNumber);
		depositTransaction.setTransactionDate(date);
		depositTransaction.setTransactionType(type);
		depositTransaction.setAmount(amount);
		transactions[transactionIndex++] = depositTransaction;
	}

//Withdrawal
	public void withdrawaltransaction(String accNumber, Date date, double amount) {
//Add withdrawal transaction to transaction list
		Transaction withdrawTransaction = new Transaction();
		withdrawTransaction.setAccountNumber(accNumber);
		withdrawTransaction.setTransactionDate(date);
		withdrawTransaction.setTransactionType("Withdrawal");
		withdrawTransaction.setAmount(amount);
		transactions[transactionIndex++] = withdrawTransaction;
	}

//Fund-Transfer
	public static void fundTransferTransaction(String accNumber, Date date, double amount) {
		Transaction transferTransaction = new Transaction();
		transferTransaction.setAccountNumber(accNumber);
		transferTransaction.setTransactionDate(date);
		transferTransaction.setTransactionType("Fund Transfer");
		transferTransaction.setAmount(amount);
		transactions[transactionIndex++] = transferTransaction;
	}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////   
	
	
	
	
	// loan account - 11%

	/*
	 * counter activities create account daily transaction report interest
	 * calculation
	 */

	/*
	 * // Define an interface for common account methods interface BankAccount {
	 * void deposit(double amount); void withdrawal(double amount); void display();
	 * void interestCalculation(); }
	 */

}
