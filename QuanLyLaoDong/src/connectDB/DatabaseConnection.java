package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static DatabaseConnection instance;
	private Connection cnt;

	private DatabaseConnection() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyLaoDong;trustServerCertificate=true";
		String user = "sa";
		String pass = "123456";
		try {
			cnt = DriverManager.getConnection(url, user, pass);
			System.out.println("Connected");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public synchronized static DatabaseConnection getInstance() {
		if (instance == null)
			instance = new DatabaseConnection();
		return instance;
	}

	public Connection getConnection() {
		return cnt;
	}
	

}