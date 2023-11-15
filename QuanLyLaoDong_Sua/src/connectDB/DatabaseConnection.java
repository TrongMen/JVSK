package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static DatabaseConnection instance= new DatabaseConnection();
	
	private Connection cnt;

	public void connect() {
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

	public static DatabaseConnection getInstance() {
		return instance;
	}
	public Connection getConnection() {
		return cnt;
	}
	public void disconnect() {
		if(cnt!=null)
			try {
				cnt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
	}
	

}