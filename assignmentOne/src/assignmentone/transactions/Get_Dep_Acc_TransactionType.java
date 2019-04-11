package assignmentone.transactions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import assignmentone.databaseconnection.DBConnection;
import assignmentone.databasefunctions.AccountGetter;
import assignmentone.databasefunctions.DepartmentGetter;

public class Get_Dep_Acc_TransactionType {

	private int departmentId;
	private int accountNum;
	private String transactionType;
	private int amount;
	Random random = new Random();
	DepartmentGetter departmentGetter = new DepartmentGetter();
	AccountGetter accountGetter = new AccountGetter();
	ArrayList<Integer> departmentList = new ArrayList();
	ArrayList<Integer> accountList = new ArrayList();

	//getting random department from the list of departments fetched from the database
	public int getRandomDepartment() {

		departmentList = departmentGetter.getDepartmentList();

		departmentId = departmentList.get(random.nextInt(departmentList.size()));

		return departmentId;
	}

	//getting random account from the list of accounts fetched from the database
	public int getRandomAccountNumber() {

		accountList = accountGetter.getAccountNum();

		accountNum = accountList.get(random.nextInt(accountList.size()));

		return accountNum;
	}
	//getting random transaction type from the list of transactions
	public String getRandomTransactionType () {
		
		ArrayList<String> transList = new ArrayList();
		transList.add("Deposit");
		transList.add("Withdraw");
		transList.add("Transfer");
		
		transactionType = transList.get(random.nextInt(transList.size()));
		return transactionType;
	}
	
	//getting random amount between 0 to 100
	public int getRandomAmount () {
		
		
		return amount = random.nextInt(100);
	}

}
