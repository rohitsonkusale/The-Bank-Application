package com.Bank;

import java.util.Date;

public class Transaction {

	private String accountNumber;
	private Date transactionDate;
	private String transactionType;
	private double amount;
	
	
	public Transaction() {
		// TODO Auto-generated constructor stub
	}
	
	public Transaction(String accountNumber, Date transactionDate, String transactionType, double amount) {
		super();
		this.accountNumber = accountNumber;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.amount = amount;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public void display() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Transaction Date: " + transactionDate);
        System.out.println("Transaction Type: " + transactionType);
        System.out.println("Amount: " + amount);
        System.out.println("--------------");
    }
	
	
}
