package assignmentone.databasefunctions;

import java.util.ArrayList;

import assignmentone.transactions.BankTransactions;

public class Account {
	public static ArrayList<BankTransactions> accountList;

	//setting up 51 accounts
	public ArrayList<BankTransactions> createAccountArrayList() 
	{
		accountList = new ArrayList<BankTransactions>();
		for (int a = 0; a < 51; a++) { 
			accountList.add(new BankTransactions(a));
		}
		return accountList;
	}

}
