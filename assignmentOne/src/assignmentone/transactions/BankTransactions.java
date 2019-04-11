package assignmentone.transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import assignmentone.databaseconnection.DBConnection;
import assignmentone.databasefunctions.Account;

public class BankTransactions {

	public int accountNumber;

	private boolean withDrawFlag = false;
	private String updateQuery = "UPDATE ACCOUNTS SET Balance=? WHERE AccountNumber=?";
	private String getBalanceQuery = "SELECT BALANCE FROM ACCOUNTS WHERE AccountNumber=?";
	boolean depositFlag = false;
	boolean transferFlag = false;

	public BankTransactions(int accountNum1) {
		this.accountNumber = accountNum1;
	}

	public synchronized boolean deposit(int depatId, int accNum, int amount) {
		int balance = 0;
		//if the deposit request comes from withdraw or transfer method then balance will be same as sent by withdraw/transfer method
		//existing balance will be fetched and sent by withdraw/transfer methods
		if (withDrawFlag | transferFlag) {

			balance = amount;
		} 
		//otherwise first get the existing balance from database for that account number
		else {

			synchronized (this) {
				//calling getBalance method which will fetch the existing balance from database
				balance = this.getBalance(accNum);
			}

			balance = balance + amount;
		}
		synchronized (this) {
			//calling setBalance method which will update the existing balance with new amount
			this.setBalance(accNum, balance, depatId);

		}

		return depositFlag;
	}

	public synchronized void setBalance(int accountNum, int balance, int depatId) {
		try {
			//getting connection from getConnection() (a static method ) from DBConnection class
			Connection connection2 = DBConnection.getConnection();
			PreparedStatement preparedStatement;

			preparedStatement = connection2.prepareStatement(updateQuery);
			preparedStatement.setInt(1, balance);
			preparedStatement.setInt(2, accountNum);

			int rowCount = preparedStatement.executeUpdate();
			if (rowCount != 0) {

				depositFlag = true;
			}

			System.out.println(Thread.currentThread().getName() + "::: DepartmentID: " + depatId
					+ " is updating the Account: " + accountNum + "'s balance to " + balance);

			connection2.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public synchronized int getBalance(int accountNum) {
		int balance = 0;
		//to get the existing balance of the accountNumber passed from the database
		try {

			Connection connection1 = DBConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection1.prepareStatement(getBalanceQuery);
			preparedStatement.setInt(1, accountNum);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				balance = resultSet.getInt(1);

			}

			connection1.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return balance;
	}

	public synchronized void withdraw(int departId, int accountNum, int amount) {
		//get the existing balance
		int balance = getBalance(accountNum);

		if (balance < amount) {
			System.out.println("Transaction failed : Insufficient Funds");
		} else {
			System.out.println("Processing withdraw...");
			balance = balance - amount;
			System.out.println("new balance after withdrawl: " + balance);
			withDrawFlag = true;
			//updating the balance after withdrawl
			deposit(departId, accountNum, balance);
			if (depositFlag) {
				System.out.println("Withdraw sucessful");
			}
		}

	}

	public synchronized void transfer(int departId, int fromAccNum, int toAccNum, int amount) {

		int fromBalance = getBalance(fromAccNum);
		int toBalance = getBalance(toAccNum);
		int tempAmount = 0;
		boolean deposit;

		if (fromBalance < amount) {
			System.out.println("Transaction failed : Insufficient Funds");
		} else {
			System.out.println("Processing Transfer...");
			tempAmount = toBalance + amount;

			transferFlag = true;
			deposit = deposit(departId, toAccNum, tempAmount);
			if (deposit) {
				//deducting money from source account only if the transfer successful
				withdraw(departId, fromAccNum, amount);
				System.out.println("Transfer Sucessful");
			} else {
				System.out.println("Transaction failed: Transfer unsucessful");
			}

		}

	}

}
