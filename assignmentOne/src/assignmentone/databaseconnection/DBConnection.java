package assignmentone.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//Establishing connection to MySQL
public class DBConnection {

	static Connection connection;

	public synchronized static Connection getConnection() {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/assignmentOne?useSSL=false&allowPublicKeyRetrieval=true", "root",
					"PASSword");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return connection;
	}

}
