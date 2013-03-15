package mis.util;

import java.sql.Connection;
import java.sql.DriverManager;

// TODO: improve DBConnection class. Include connection pooling
public class DBConnection {
	final static String url = "jdbc:mysql://localhost:3306/";
	final static String dbName = "medicalinfosystem";
	final static String driver = "com.mysql.jdbc.Driver";
	final static String userName = "root";
	final static String password = "root";

	public static Connection getConnection() throws Exception {
		Class.forName(driver).newInstance();
		Connection conn = DriverManager.getConnection(url + dbName, userName,
				password);
		return conn;
	}
}