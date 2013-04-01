package mis.constants;

public class SqlConstants {

	public static String getAllUsers="SELECT * FROM medicalinfosystem.user where tenantid=?";
	public static String getUser = "SELECT * FROM medicalinfosystem.user where username=? and tenantid=?";
	public static String insertUser = "INSERT into medicalinfosystem.user(username,password,roleid,tenantid) VALUES(?,?,?,?)";
	public static String deleteUser = "DELETE from medicalinfosystem.user where userid = ?";
	public static String getUserByid ="SELECT * FROM medicalinfosystem.user where userid=? and tenantid=?";
	public static String insertPatient ="INSERT into medicalinfosystem.patient(fname,lname,email,gender,phone,userid) VALUES(?,?,?,?,?,?)";
	
	public static String updatePatient = "UPDATE medicalinfosystem.patient SET fname=?,lname=?,email=?,gender=?,phone=? WHERE patient_id = ?";
	public static String getAllPatients = "SELECT * FROM medicalinfosystem.user u, medicalinfosystem.patient p where u.userid = p.userid and u.tenantid=?;";
	public static String getPatientByid ="SELECT * FROM medicalinfosystem.user u, medicalinfosystem.patient p where u.userid = p.userid and p.patient_id=?;";
}
