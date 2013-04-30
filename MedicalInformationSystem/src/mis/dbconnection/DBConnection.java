package mis.dbconnection; //TODO : HARSHIL.. 

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	public static void initializeConnPool()
	{
		// Initialize connection pool.2
	}
	public static Connection getConnection() {

		// TODO : Connection pooling remaining for coding.
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/medicalinfosystem", "root","root");
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
		}
		
		return conn;
	}
	
	public static void closeConnection(Connection con)
	{
		
	}
}