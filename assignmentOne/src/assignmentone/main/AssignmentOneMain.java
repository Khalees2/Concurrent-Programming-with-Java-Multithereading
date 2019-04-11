package assignmentone.main;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import assignmentone.databaseconnection.DBConnection;
import assignmentone.databasefunctions.Account;
import assignmentone.databasefunctions.DepartmentGetter;
import assignmentone.transactions.BankTransactions;
import assignmentone.transactions.Get_Dep_Acc_TransactionType;

public class AssignmentOneMain {
	private static int departmentId;
	private static int accountNum1;
	private static int accountNum2;
	private static String transactionType;
	private static int amount;
	// to get random Department,Two random Account Numbers, random Transaction type(Deposit,Withdraw,Transfer), random Amount 
	private static Get_Dep_Acc_TransactionType getDepAccTransaction = new Get_Dep_Acc_TransactionType();
	

	public static void main(String[] args) {
		
		//A list of 50 Accounts  will be created of type BankTransaction class.
		ArrayList<BankTransactions> accountList = new Account().createAccountArrayList();
		//to get the number of cores of the system 
		int numberOfCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executorService = Executors.newFixedThreadPool(8);

		for (int i = 0; i < 10000; i++) {
			//getting random values
			departmentId = getDepAccTransaction.getRandomDepartment();
			accountNum1 = getDepAccTransaction.getRandomAccountNumber();
			accountNum2 = getDepAccTransaction.getRandomAccountNumber();
			transactionType = getDepAccTransaction.getRandomTransactionType();
			amount = getDepAccTransaction.getRandomAmount();

			Runnable department = new DepartmentGetter(departmentId, accountNum1, accountNum2, transactionType, amount,accountList);
			//Runnable department1 = new DepartmentGetter(20190301, 1, 2, "Deposit", 1,accountList);
			executorService.execute(department);

		}
		executorService.shutdown();
		while (!executorService.isTerminated()) {
		}

	}

}
