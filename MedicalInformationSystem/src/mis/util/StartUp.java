package mis.util;





import javax.servlet.ServletConfig;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;


import mis.dbconnection.JDBCConnectionDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
/**
 * Servlet implementation class StartUp
 */
public class StartUp extends HttpServlet {
	private final int noOfConn = 1000;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StartUp() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		
		try{
			new JDBCConnectionDriver("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/medicalinfosystem", "root", "root", noOfConn);
			
			
			
			System.out.println("HarryPotter1");
			Connection[] c = new Connection[noOfConn];
			for(int i=0;i<noOfConn;i++) {
				
				c[i] = DriverManager.getConnection("jdbc:user_mysql:");
			}
			
			//sleep(60000);
			
			Statement stmt = c[0].createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * from user");
			
			if(rs.next()) {
				//rs.getString("username");
				System.out.println(rs.getString("password"));
			}
			
			for(int i=0;i<noOfConn;i++) {
				
				c[i].close();
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("HarryPotter" );
		}
		
		
		
	}

}
