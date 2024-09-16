package me.trihung.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectSQLServer {

	public static Connection getConnection() {
		Connection connection = null;
		try {

//			SQLServerDriver driver = new SQLServerDriver();
//			DriverManager.registerDriver(driver);
//			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url = "jdbc:sqlserver://LAPTOP-T2D8QL38\\SQLSV2;databaseName=WEBDB;user=sa;password=123";
			connection = DriverManager.getConnection(url);
			return connection;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static boolean isValidPassword(String password) {
		password = password.strip();
		return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
	}
	
	public static void main(String[] arg) {
		Connection con = getConnection();
		try {
			if (con!=null)
				System.out.println("connect"+con.getCatalog());
		System.out.println(isValidPassword("121212Hung@"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeConnection(Connection connection) {
		try {
			if (connection != null)
				connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void printInfo(Connection connection) {
		try {
			if (connection != null)
				System.out.print(connection.getMetaData().toString());

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}