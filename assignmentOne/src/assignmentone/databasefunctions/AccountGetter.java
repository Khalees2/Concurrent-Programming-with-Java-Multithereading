package assignmentone.databasefunctions;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import assignmentone.databaseconnection.DBConnection;

//this class will return all 50 accounts in a ArrayList
public class AccountGetter {
	
	private Connection connection;
	ArrayList<Integer> accountList = new ArrayList();
	private String getAccountNumQuery = "SELECT * FROM ACCOUNTS";
	
	public ArrayList<Integer> getAccountNum(){
		
		try {
			connection = DBConnection.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(getAccountNumQuery);
			while (resultSet.next())

				accountList.add(resultSet.getInt(1));

		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return accountList;
		
	}

}
