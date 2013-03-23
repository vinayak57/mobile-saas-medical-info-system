package mis.constants;

public class SqlConstants {

	public static String getAllUsers="SELECT * FROM medicalinfosystem.user";
	public static String getUser = "SELECT * FROM medicalinfosystem.user where username=?";
	public static String insertUser = "INSERT into medicalinfosystem.user(username,password) VALUES(?,?)";
	public static String deleteUser = "DELETE from medicalinfosystem.user where userid = ?";
}
