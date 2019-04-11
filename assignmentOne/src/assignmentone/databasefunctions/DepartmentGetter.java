package assignmentone.databasefunctions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import assignmentone.databaseconnection.DBConnection;
import assignmentone.transactions.BankTransactions;

public class DepartmentGetter implements Runnable {

	private Connection connection;
	private String getDepartmentQuery = "SELECT * FROM DEPARTMENT";
	ArrayList<Integer> depList = new ArrayList();
	
	private int departmentId;
	private int accountNum1;
	private int accountNum2;
	private String transactionType;
	private int amount;
	public static ArrayList<BankTransactions> accountsList;
	//constructors below
	public DepartmentGetter() {}
	
	public DepartmentGetter(int DeptId, int accountNum1, int accountNum2, String transactionType, int amount,
			ArrayList<BankTransactions> list) {

		this.departmentId = DeptId;
		this.accountNum1 = accountNum1;
		this.accountNum2 = accountNum2;
		this.transactionType = transactionType;
		this.amount = amount;

		this.accountsList = list;
		// run();

	}
	//getting all 10 departments which are stored in the database and storing it into a ArrayList
	public ArrayList getDepartmentList() {

		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getDepartmentQuery);
			while (resultSet.next())

				depList.add(resultSet.getInt(1));

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return depList;
	}
	
	
	@Override
	public void run() {
		//getting account associated with the account Number
		BankTransactions acc = accountsList.get(this.accountNum1);
		
		switch (transactionType) {

		case "Deposit":
			acc.deposit(departmentId, acc.accountNumber, amount);
			break;
			case "Withdraw":
			acc.withdraw(departmentId, accountNum1, amount);
				break;
			case "Transfer":
			acc.transfer(departmentId, accountNum1, accountNum2, amount);
				break;
		default:
			break;

		}

	}


}
