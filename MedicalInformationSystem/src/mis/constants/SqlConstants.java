package mis.constants;

public class SqlConstants {

	public static String getAllUsers="SELECT * FROM medicalinfosystem.user where tenantid=?";
	public static String getUser = "SELECT * FROM medicalinfosystem.user where username=? and tenantid=?";
	public static String insertUser = "INSERT into medicalinfosystem.user(username,password,roleid,tenantid) VALUES(?,?,?,?)";
	public static String deleteUser = "DELETE from medicalinfosystem.user where userid = ?";
	public static String getUserByid ="SELECT * FROM medicalinfosystem.user where userid=? and tenantid=?";
	public static String updatePassword = "UPDATE medicalinfosystem.user SET password=?WHERE userid = ?";
	
	public static String insertPatient ="INSERT into medicalinfosystem.patient(fname,lname,email,gender,phone,dob,location_id,userid) VALUES(?,?,?,?,?,?,?,?)";
	public static String updatePatient = "UPDATE medicalinfosystem.patient SET fname=?,lname=?,email=?,gender=?,phone=?,dob=?,location_id=? WHERE patient_id = ?";
	public static String getAllPatients = "SELECT * FROM medicalinfosystem.user u, medicalinfosystem.patient p where u.userid = p.userid and u.tenantid=?;";
	public static String getPatientByid ="SELECT * FROM medicalinfosystem.user u, medicalinfosystem.patient p where u.userid = p.userid and u.userid=?;";
	
	public static String getStaffByid = "SELECT * FROM medicalinfosystem.user u, medicalinfosystem.hospital_staff s where u.userid = s.userid and s.hospital_staff_id=?;";
	public static String getAllStaff = "SELECT * FROM medicalinfosystem.user u, medicalinfosystem.hospital_staff s where u.userid = s.userid and u.tenantid=?;";
	public static String insertStaff ="INSERT into medicalinfosystem.hospital_staff(fname,lname,details,speciality,userid) VALUES(?,?,?,?,?)";
	public static String updateStaff = "UPDATE medicalinfosystem.hospital_staff SET fname=?,lname=?,details=?,speciality=? WHERE hospital_staff_id = ?";
	
	public static String getDrugByid="SELECT * FROM medicalinfosystem.drug where drug_id=?";
	public static String getAllDrug="SELECT * FROM medicalinfosystem.drug where tenantid=?";
	public static String insertDrug="INSERT into medicalinfosystem.drug(name,power,tenantid) VALUES(?,?,?)";
	public static String updateDrug="UPDATE medicalinfosystem.drug SET name=?,power=? WHERE drug_id = ?";
	
	public static String getLocationByid="SELECT * FROM medicalinfosystem.location where location_id=?";
	public static String getAllLocations="SELECT * FROM medicalinfosystem.location where tenantid=?";
	public static String insertLocation="INSERT into medicalinfosystem.location(addr1,addr2,city,state,country,zipcode,tenantid) VALUES(?,?,?,?,?,?,?)";
	public static String getLocationIdByLoc = "SELECT * FROM medicalinfosystem.location where addr1=? and addr2=? and city=? and state=? and zipcode=?";
}
