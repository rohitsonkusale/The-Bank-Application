package com.Bank;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {

//////////////////////////////////////////Create Account Function/////////////////////////////////////////////////////////////////////////////////////////////////////	

	static Account[] arr = new Account[1000];
	static int index = 0;

	public static Account Create_Account() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		while (choice != 6) {
			System.out.println("\n\n\t ---Choose Account Type---");
			System.out.println("\t---------------------------\n");
			System.out.println("\t1) Saving Account.");
			System.out.println("\t2) Current Account.");
			System.out.println("\t3) Loan Account.");
			System.out.println("\t4) Salary Account.");
			System.out.println("\t5) Main Menu.");

			System.out.print("\n\t Enter your choice:   ");
			choice = sc.nextInt();

			switch (choice) {
			case 1: {
				String name;
				System.out.print("\n\tEnter Account Holder Name:  ");
				name = sc.next();
				String accNum;
				System.out.print("\tEnter Account Number:  ");
				accNum = sc.next();
				double bal;
				System.out.print("\tEnter initial deposite:  ");
				bal = sc.nextDouble();

				double minimumBalance = 10000;
				if (bal < minimumBalance) {
					System.out.println("\tSorry!! Minimum '10,000' Balance Required to Open Savings Account.");
				} else {
					Account act = new SavingAccount(name, accNum, new Date(), bal);
					arr[index++] = act;
					System.out.println("\n\n\t\t**Congratulations** Savings Account Created Sucessfully.\n\n");
					return act;
				}
			}
				break;
			case 2: {

				String name;
				System.out.print("\n\tEnter Account Holder Name:  ");
				name = sc.next();
				String accNum;
				System.out.print("\tEnter Account Number:  ");
				accNum = sc.next();
				double bal;
				System.out.print("\tEnter initial deposite:  ");
				bal = sc.nextDouble();

				Account act = new CurrentAccount(name, accNum, new Date(), bal);
				arr[index++] = act;
				System.out.println("\n\n\t\t--Congratulations-- Current Account Created Sucessfully.\n\n");
				return act;
			}
			case 3: {

				String name;
				System.out.print("\n\tEnter Account Holder Name:  ");
				name = sc.next();
				String accNum;
				System.out.print("\tEnter Account Number:  ");
				accNum = sc.next();
//	        		double bal;
//	        		System.out.print("\tEnter initial deposite:  ");
//	        		bal = sc.nextDouble();

				Account act = new LoanAccount(name, accNum, new Date(), 0.00);
				arr[index++] = act;
				System.out.println("\n\n\t\t--Congratulations-- Loan Account Created Sucessfully.\n\n");
				return act;
			}

			case 4: {
				String name;
				System.out.print("\n\tEnter Account Holder Name:  ");
				name = sc.next();
				String accNum;
				System.out.print("\tEnter Account Number:  ");
				accNum = sc.next();
				double bal;
				System.out.print("\tEnter initial deposite:  ");
				bal = sc.nextDouble();

				Account act = new SalaryAccount(name, accNum, new Date(), bal);
				arr[index++] = act;
				System.out.println("\n\n\t\t**Congratulations** Salary Account Created Sucessfully.\n\n");
				return act;
			}
			case 5: {
				System.out.println("\n\t\tBacking to Main Menu....");
				return null;
				// break;
			}
			default:
				System.out.println("\n\t\t  Invalid account type choice.");
				System.out.println("\t\t--------------------------------\n\n");
				return null;
			}
		}
		return null;
	}
/////////////////////////////////Search_Account Function///////////////////////////////////////////////////////////////////////

	public static Account Search_Account(String accNumber) {
		for (int i = 0; i < arr.length; i++) {
			Account account = arr[i];
			if (account != null && account.getAccountNumber().equals(accNumber)) {
				return account;
			}
		}
		return null;
	}

//////////////////////////Freeze//////////////////Last-Transaction Function//////////////////////////////////////////////////////

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

/////////////////////////////////Main Function///////////////////////////////////////////////////////////////////////

//	MainApp main = new MainApp();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		// int exit = 1;
		int choice = 0;
		Account account = null;

		while (choice != 10) {

			boolean fine = false;
			while (fine == false) {

				System.out.println("\n\n\t-------------------------------------------------------------------------");
				System.out.println("\t|                   ---Banking Web Application---                       |");
				System.out.println("\t|-----------------------------------------------------------------------|");
				System.out.println("\t|                                                                       |");
				System.out.println("\t|\t1) Create Account  \t||\t6) Interest Calculations        |");
				System.out.println("\t|                                                                       |");
				System.out.println("\t|\t2) Deposit Money   \t||\t7) Account Status               |");
				System.out.println("\t|                                                                       |");
				System.out.println("\t|\t3) Withdrawal Money\t||\t8) Close Account                |");
				System.out.println("\t|                                                                       |");
				System.out.println("\t|\t4) Check Balance   \t||\t9) List Of Daily Transactions   |");
				System.out.println("\t|                                                                       |");
				System.out.println("\t|\t5) Fund Transfer   \t||\t10) End Program                 |");
				System.out.println("\t|_______________________________________________________________________|");

				try {
					System.out.print("\n\tEnter Your choice:   ");
					choice = sc.nextInt();

					switch (choice) {
					case 1: {
						if (account == null) {
							Create_Account();
						}
						break;
					}
					case 2: {
						String accNumber;
						System.out.print("\n\tEnter Account Number You want to deposite:  ");
						accNumber = sc.next();
						Account act = Search_Account(accNumber);

						if (act instanceof LoanAccount) {
							((LoanAccount) act).makeLoanRepayment(act, accNumber);
							break;
						}

						if (act != null && (!(act instanceof LoanAccount))) {
							double amount;
							System.out.print("\n\tEnter Amount:  ");
							amount = sc.nextDouble();
							act.deposit(act, amount, accNumber);
						} else {
							System.out.println("\n\n\t\t   Account with Account Number " + accNumber
									+ " not found. Deposit failed.");
							System.out
									.println("\t\t--------------------------------------------------------------\n\n");
						}
						break;

					}
					case 3: {
						String accNumber;
						System.out.print("\tEnter Account Number You want to withdrawal:  ");
						accNumber = sc.next();
						Account act = Search_Account(accNumber);

						if (act != null) {
							double amount;
							System.out.print("\tEnter Amount:  ");
							amount = sc.nextDouble();
							act.withdrawal(act, amount, accNumber);
						} else {
							System.out.println("\n\n\t\t   Account with Account Number " + accNumber
									+ " not found. withdrawal failed.");
							System.out
									.println("\t\t--------------------------------------------------------------\n\n");
						}
						break;
					}
					case 4: {
						String myAccNumber;
						System.out.print("\n\tEnter Your Account Number:   ");
						myAccNumber = sc.next();

						Account act = Search_Account(myAccNumber);

						if (act != null) {
							System.out.println("\n\t\t  Your Available Balance is:  " + act.getBalance());
							System.out.println("\t\t-------------------------------------------\n\n");
						} else {
							System.out.println("\n\n\t\t   Account with Account Number " + myAccNumber + " not found.");
							System.out.println("\t\t-----------------------------------------------\n\n");
						}
						break;
					}
					case 5: {
						String myAccNumber;
						System.out.print("\n\tEnter Your Account Number:   ");
						myAccNumber = sc.next();

						Account act = Search_Account(myAccNumber);

						if (act == null) {
							System.out.println("\n\n\t\t   Account with Account Number " + myAccNumber
									+ " not found. Fund-Transfer failed.");
							System.out.println(
									"\t\t-----------------------------------------------------------------------\n\n");
						} else {
							String anotherAccount;
							System.out.print("\tEnter Transfering Account Number:   ");
							anotherAccount = sc.next();

							double amount;
							System.out.print("\tEnter Amount:  ");
							amount = sc.nextDouble();
							Account.Fund_Transfer(act, amount, myAccNumber, anotherAccount);
						}
						break;
					}
					case 6: {
						String myAccNumber;
						System.out.print("\n\tEnter Your Account Number:   ");
						myAccNumber = sc.next();

						Account act = Search_Account(myAccNumber);

						if (act != null) {
							act.Interest_Calculation(act);
						} else {
							System.out.println("\n\n\t\t   Account with Account Number " + myAccNumber
									+ " not found. Interest-Calculation failed.");
							System.out.println(
									"\t\t-------------------------------------------------------------------------\n\n");
						}
						break;
					}
					case 7: {
						String myAccNumber;
						System.out.print("\n\tEnter Your Account Number:   ");
						myAccNumber = sc.next();

						Account act = Search_Account(myAccNumber);

						if (act != null) {
							Account.Account_Lifecycle(act, myAccNumber);
						} else {
							System.out.println("\n\n\t\t   Account with Account Number " + myAccNumber + " not found.");
							System.out.println("\t\t------------------------------------------------\n\n");
						}
						break;
					}
					case 8: {
						String myAccNumber;
						System.out.print("\n\tEnter Your Account Number:   ");
						myAccNumber = sc.next();

						Account act = Search_Account(myAccNumber);
						if (act != null) {
							Account.Close_Account(act);
						} else {
							System.out.println("\n\n\t\t   Account with Account Number " + myAccNumber + " not found.");
							System.out.println("\t\t------------------------------------------------\n\n");
						}
						break;
					}
					case 9: {
						Account.dailyReport();
						break;
					}
					case 10:
						System.out.println("End Program!");
						fine = true;
						break;

					default:
						System.out.println("\n\n\t\t  !!Invalid Input");
						System.out.println("\t\t-------------------\n\n");

					}

				} catch (InputMismatchException imm) {
					System.out.println("\n\t\tError!! Enter Only Numeric value");
					System.out.print("\t\tEnter Choice:  ");
					sc.nextLine();
					choice = sc.nextInt();
				} catch (Exception ex) {
					System.out.println("\n\t\tSomething went wrong!!");
				}
			}
		}
	}

}
