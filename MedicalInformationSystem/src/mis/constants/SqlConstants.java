package mis.constants;

public class SqlConstants {

	public static String getAllUsers="SELECT * FROM medicalinfosystem.user where tenantid=?";
	public static String getUser = "SELECT * FROM medicalinfosystem.user where username=? and tenantid=?";
	public static String insertUser = "INSERT into medicalinfosystem.user(username,password,roleid,tenantid) VALUES(?,?,?,?)";
	public static String deleteUser = "DELETE from medicalinfosystem.user where userid = ?";
	public static String getUserByid ="SELECT * FROM medicalinfosystem.user where userid=? and tenantid=?";
	public static String insertPatient ="INSERT into medicalinfosystem.patient(fname,lname,email,gender,phone,userid) VALUES(?,?,?,?,?,?)";
}
