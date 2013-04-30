package mis.constants;

import mis.util.DateConvert;

public class SqlConstants {

	public static String getAllUsers="SELECT * FROM medicalinfosystem.user where tenantid=?";
	public static String getUser = "SELECT * FROM medicalinfosystem.user where username=? and tenantid=?";
	public static String insertUser = "INSERT into medicalinfosystem.user(username,password,roleid,tenantid) VALUES(?,?,?,?)";
	public static String deleteUser = "DELETE from medicalinfosystem.user where userid = ?";
	public static String getUserByid ="SELECT * FROM medicalinfosystem.user where userid=? and tenantid=?";
	public static String updatePassword = "UPDATE medicalinfosystem.user SET password=? WHERE userid = ?";
	
	public static String insertPatient ="INSERT into medicalinfosystem.patient(fname,lname,email,gender,phone,dob,location_id,userid) VALUES(?,?,?,?,?,?,?,?)";
	public static String updatePatient = "UPDATE medicalinfosystem.patient SET fname=?,lname=?,email=?,gender=?,phone=?,dob=?,location_id=? WHERE patient_id = ?";
	public static String getAllPatients = "SELECT * FROM medicalinfosystem.user u, medicalinfosystem.patient p where u.userid = p.userid and u.tenantid=?;";
	public static String getPatientByid ="SELECT * FROM medicalinfosystem.user u, medicalinfosystem.patient p where u.userid = p.userid and u.userid=?;";
	public static String getAllPatientsByName = "SELECT * FROM medicalinfosystem.user u, medicalinfosystem.patient p where u.userid = p.userid and (u.tenantid=? and p.fname=? and p.lname=?)";
	public static String getPatientBypatientid = "SELECT * FROM medicalinfosystem.user u, medicalinfosystem.patient p where u.userid = p.userid and p.patient_id=?;";
	
	public static String getStaffByid = "SELECT * FROM medicalinfosystem.user u, medicalinfosystem.hospital_staff s where u.userid = s.userid and u.userid=?;";
	public static String getAllStaff = "SELECT * FROM medicalinfosystem.user u, medicalinfosystem.hospital_staff s where u.userid = s.userid and u.tenantid=?;";
	public static String insertStaff ="INSERT into medicalinfosystem.hospital_staff(fname,lname,details,speciality,userid, hospital_id) VALUES(?,?,?,?,?,?)";
	public static String updateStaff = "UPDATE medicalinfosystem.hospital_staff SET fname=?,lname=?,details=?,speciality=?, hospital_id=? WHERE hospital_staff_id = ?";
	public static String getAllStaffByHospital = "SELECT * FROM medicalinfosystem.user u, medicalinfosystem.hospital_staff s where u.userid = s.userid and u.tenantid=? and s.hospital_id=?";
	public static String getStaffByStaffid = "SELECT * FROM medicalinfosystem.user u, medicalinfosystem.hospital_staff s where u.userid = s.userid and s.hospital_staff_id=?";
	
	public static String getAllHospital = "SELECT * FROM medicalinfosystem.hospital where tenantid=?";
	public static String getHospitalByid = "SELECT * FROM medicalinfosystem.hospital where hospital_id=?";
	public static String insertHospital = "INSERT into medicalinfosystem.hospital(hospital_name,location,tenantid) VALUES(?,?,?)";
	public static String updateHospital = "UPDATE medicalinfosystem.hospital SET hospital_name=? , location =? ,tenantid = ? WHERE hospital_id = ?";
	
	public static String getDrugByid="SELECT * FROM medicalinfosystem.drug where drug_id=?";
	public static String getAllDrug="SELECT * FROM medicalinfosystem.drug where tenantid=?";
	public static String insertDrug="INSERT into medicalinfosystem.drug(name,power,tenantid) VALUES(?,?,?)";
	public static String updateDrug="UPDATE medicalinfosystem.drug SET name=?,power=? WHERE drug_id = ?";
	
	public static String getLocationByid="SELECT * FROM medicalinfosystem.location where location_id=?";
	public static String getAllLocations="SELECT * FROM medicalinfosystem.location where tenantid=?";
	public static String insertLocation="INSERT into medicalinfosystem.location(addr1,addr2,city,state,country,zipcode,tenantid) VALUES(?,?,?,?,?,?,?)";
	public static String getLocationIdByLoc = "SELECT * FROM medicalinfosystem.location where addr1=? and addr2=? and city=? and state=? and zipcode=?";
	
	public static String getVisitTypeById="SELECT * FROM medicalinfosystem.visit_type where visit_type_id=?";
	public static String getAllVisitType="SELECT * FROM medicalinfosystem.visit_type where tenantid=?";
	public static String putVisitDetails="INSERT into medicalinfosystem.visit_type(visit_type,description,tenantid) VALUES(?,?,?)";
	public static String getVisitTypeByVisit="SELECT * FROM medicalinfosystem.visit_type where visit_type=? and tenantid=?";
	
	public static String getAllAppointment="SELECT * FROM medicalinfosystem.appointment_details where patient_id=?";
	public static String getAppointmentByid="SELECT * FROM medicalinfosystem.appointment_details where appointment_id=?";
	public static String insertAppointment="INSERT into medicalinfosystem.appointment_details(hospital_staff_id,location_id,visit_type_id, tenantid, appointment_date, patient_id,status) VALUES(?,?,?,?,?,?,?)";
	public static String getAllAppointmentByStaff="SELECT * FROM medicalinfosystem.appointment_details where tenantid=? and hospital_staff_id=?";
	public static String getAllAppointmentsWeekly="SELECT * FROM medicalinfosystem.appointment_details where  patient_id=? and appointment_date >= CURDATE() and appointment_date < DATE_ADD(CURDATE(), INTERVAL 7 DAY);";
	public static String getAllAppointmentByStaffWeekly="SELECT * FROM medicalinfosystem.appointment_details where  hospital_staff_id=? and appointment_date >= CURDATE() and appointment_date < DATE_ADD(CURDATE(), INTERVAL 7 DAY);";
	public static String updateAppointment="UPDATE medicalinfosystem.appointment_details SET hospital_staff_id = ?,location_id = ?,visit_type_id = ?, tenantid = ?, appointment_date=?, patient_id=?, status=? WHERE appointment_id =?";
	public static String updateAppointmentStatus = "UPDATE medicalinfosystem.appointment_details SET status=? WHERE appointment_id =?";
	
	public static String getAllPrescription = "SELECT * FROM medicalinfosystem.prescription_details where tenantid=?";
	public static String getPrescriptionById = "SELECT * FROM medicalinfosystem.prescription_details where prescription_id=?";
	public static String insertPrescriptionDetails = "INSERT into medicalinfosystem.prescription_details(drug_id,dose,start_date,end_date,instruction,tenantid,appointment_id) VALUES(?,?,?,?,?,?,?)";
	public static String getAllPrescriptionByAppointment = "SELECT * FROM medicalinfosystem.prescription_details where appointment_id=?";
	
	public static String getAllPatientMedInfo="SELECT * FROM medicalinfosystem.patient_medical_info where patient_id=?";
	public static String getAllPatientMedInfoById="SELECT * FROM medicalinfosystem.patient_medical_info where patient_med_info_id=?";
	public static String insertPatientMedInfoDetails="INSERT into medicalinfosystem.patient_medical_info(allergies, precautions, side_effects, patient_id,bloodgroup,weight, height, dob, age, gender,tenant_id) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	public static String updatePatientMedInfoDetails = "UPDATE medicalinfosystem.patient_medical_info SET allergies=?, precautions=?, side_effects=?, patient_id=?,bloodgroup=?,weight=?, height=?, dob=?, age=?, gender=?,tenant_id=? WHERE patient_med_info_id=?";
	
	public static String getSurgicalHistoryInfoById = "SELECT * FROM medicalinfosystem.surgical_history where patient_id=?";
	public static String getSurgicalHistoryById = "SELECT * FROM medicalinfosystem.surgical_history where surgical_history_id=?";
	public static String insertSurgicalHistoryDetails = "INSERT into medicalinfosystem.surgical_history(type_of_surgery, date_of_surgery, hospital_staff_id, patient_id, tenant_id) VALUES(?,?,?,?,?)";
	public static String updateSurgicalHistoryDetails = "UPDATE medicalinfosystem.surgical_history SET type_of_surgery=?, date_of_surgery=?, hospital_staff_id=?, patient_id=?, tenant_id=? WHERE surgical_history_id=?";

	public static String getSocialHistoryById = "SELECT * FROM medicalinfosystem.social_history where social_history_id=?";
	public static String getSocialHistoryInfoById = "SELECT * FROM medicalinfosystem.social_history where patient_id=?";
	public static String insertSocialHistoryDetails = "INSERT into medicalinfosystem.social_history(smoker, alcoholic, drug_consumer,vaccinations, no_of_pregnancies, patient_id, tenant_id) VALUES(?,?,?,?,?,?,?)";
	public static String updateSocialHistoryDetails = "UPDATE medicalinfosystem.social_history SET smoker=?, alcoholic=?, drug_consumer=?, vaccinations=?,no_of_pregnancies=?, patient_id=?, tenant_id=? WHERE social_history_id=?";
	
	public static String getFamilyHistoryById = "SELECT * FROM medicalinfosystem.family_history where family_history_id=?";
	public static String getFamilyHistoryInfoById = "SELECT * FROM medicalinfosystem.family_history where patient_id=?";
	public static String insertFamilyHistoryDetails = "INSERT into medicalinfosystem.family_history(member_name, relationship, disease, patient_id, tenant_id) VALUES(?,?,?,?,?)";
	public static String updateFamilyHistoryDetails = "UPDATE medicalinfosystem.family_history SET member_name=?, relationship=?, disease=?, patient_id=?, tenant_id=? WHERE family_history_id=?";
	
	public static String getEmergencyByid ="SELECT * FROM medicalinfosystem.emergency_request where emergency_request_id=?";
	public static String getAllOpenEmergencyReq = "SELECT * FROM medicalinfosystem.emergency_request where tenantid=? and status='open'";
	public static String getAllEmergencyReq = "SELECT * FROM medicalinfosystem.emergency_request where tenantid=?";
	public static String insertEmergency = "INSERT into medicalinfosystem.emergency_request(userid, emergencylocation, latitude, longitude, tenantid, status, requestdate)VALUES (?,?,?,?,?,?,?)";
	public static String updateEmergency = "UPDATE medicalinfosystem.emergency_request SET status=? WHERE emergency_request_id =?";
}
