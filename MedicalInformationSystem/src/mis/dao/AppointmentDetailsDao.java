package mis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mis.constants.SqlConstants;
import mis.model.AppointmentDetail;
import mis.model.Drug;
import mis.model.Hospital;
import mis.model.HospitalStaff;
import mis.model.Patient;
import mis.model.PatientMedInfo;
import mis.model.VisitType;
import mis.util.DateConvert;
import mis.dbconnection.DBConnection;

public enum AppointmentDetailsDao {
	instance;
	private static int userCount;

	private AppointmentDetailsDao() {
	}
	
	public AppointmentDetail getAppointmentById(int appointmentid) {
		AppointmentDetail apmntObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAppointmentByid;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, appointmentid);
			rs = prest.executeQuery();
			if (rs != null) {
				apmntObj = fetchSingleResult(rs);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return apmntObj;
	}

	
	
	public Map<String, AppointmentDetail> getAllAppointmentByStaffWeekly(int tenantid, int hospital_staff_id)
	{
		Map<String, AppointmentDetail> apmntList = new HashMap<String, AppointmentDetail>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllAppointmentByStaffWeekly;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, hospital_staff_id);
			rs = prest.executeQuery();
			if (rs != null) {
				List<AppointmentDetail> resultList = fetchMultiResults(rs);

				for (AppointmentDetail appmnt : resultList)
					apmntList.put(String.valueOf(userCount++), appmnt);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return apmntList;
	}
	
	public Map<String, AppointmentDetail> getAllAppointmentByStaff(int tenantid, int hospital_staff_id)
	{
		Map<String, AppointmentDetail> apmntList = new HashMap<String, AppointmentDetail>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllAppointmentByStaff;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, tenantid);
			prest.setInt(2, hospital_staff_id);
			rs = prest.executeQuery();
			if (rs != null) {
				List<AppointmentDetail> resultList = fetchMultiResults(rs);

				for (AppointmentDetail appmnt : resultList)
					apmntList.put(String.valueOf(userCount++), appmnt);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return apmntList;
	}
	
	
	public Map<String, AppointmentDetail> getAppointmentByPatientIdWeekly(int patientid) {

		Map<String, AppointmentDetail> apmntList = new HashMap<String, AppointmentDetail>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllAppointmentsWeekly;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, patientid);
			rs = prest.executeQuery();
			if (rs != null) {
				List<AppointmentDetail> resultList = fetchMultiResults(rs);

				for (AppointmentDetail appmnt : resultList)
					apmntList.put(String.valueOf(userCount++), appmnt);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return apmntList;
	}
	
	
	public Map<String, AppointmentDetail> getAllAppointmentDetailsByPatient(int patientid) {

		Map<String, AppointmentDetail> apmntList = new HashMap<String, AppointmentDetail>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllAppointment;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, patientid);
			rs = prest.executeQuery();
			if (rs != null) {
				List<AppointmentDetail> resultList = fetchMultiResults(rs);

				for (AppointmentDetail appmnt : resultList)
					apmntList.put(String.valueOf(userCount++), appmnt);

			}

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return apmntList;
	}

	public int insertAppointment(AppointmentDetail apmnt) {
		System.out.println("calling insert");
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		int result = 0;

		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.insertAppointment;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, apmnt.getHospital_staff_id());
			prest.setInt(2, apmnt.getLocation_id());
			prest.setInt(3, apmnt.getVisit_type_id());
			prest.setInt(4, apmnt.getTenantid());
			prest.setTimestamp(5, new Timestamp(apmnt.getAppointment_date().getTime()));
			prest.setInt(6, apmnt.getPatient_id());
			prest.setString(7, apmnt.getStatus());
			result = prest.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public int updateAppointmentStatus(int appointment_id)
	{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		int result = 0;

		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.updateAppointmentStatus;
			prest = con.prepareStatement(sqlStatement);
			prest.setString(1, "close");
			prest.setInt(2, appointment_id);
			result = prest.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}
	
	public int updateAppointment(AppointmentDetail apmnt)
	{
		System.out.println("calling update");
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		int result = 0;

		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.updateAppointment;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, apmnt.getHospital_staff_id());
			prest.setInt(2, apmnt.getLocation_id());
			prest.setInt(3, apmnt.getVisit_type_id());
			prest.setInt(4, apmnt.getTenantid());
			prest.setTimestamp(5, new Timestamp(apmnt.getAppointment_date().getTime()));
			prest.setInt(6, apmnt.getPatient_id());
			//prest.setInt(7, apmnt.getPrescription_id());
			prest.setString(7, apmnt.getStatus());
			prest.setInt(8, apmnt.getAppointment_id());
			result = prest.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;
	}

	public int putAppointmentDetails(AppointmentDetail appointment) {

		AppointmentDetail appointmentObj = getAppointmentById(appointment.getAppointment_id());
		if (appointmentObj == null) {
			return insertAppointment(appointment);
		} else {
			return updateAppointment(appointment);
		}

	}

	protected AppointmentDetail fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			AppointmentDetail dto = new AppointmentDetail();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}

	protected AppointmentDetail fillPatientDetails(AppointmentDetail dto, ResultSet rs, int patient_id)
	{
		Patient patientObj = PatientDao.instance.getPatientByPatientId(patient_id);
		dto.setFname(patientObj.getFname());
		dto.setLname(patientObj.getLname());
		
		VisitType obj = VisitTypeDao.instance.getVisitTypeById(dto.getVisit_type_id());
		
		dto.setVisit_type(obj.getVisit_type());
		return dto;
	}
	
	protected AppointmentDetail fillStaffDetails(AppointmentDetail dto, ResultSet rs, int patient_id)
	{
		HospitalStaff patientObj = HospitalStaffDao.instance.getStaffByStaffId(patient_id);
		dto.setStaff_name(patientObj.getLname()+ ", " + patientObj.getFname());
		
		Hospital obj = HospitalDao.instance.getHospitalById(patientObj.getHospital_id());
		
		dto.setHospital_name(obj.getHospital_name());
		
		return dto;
	}
	
	
	protected void populateVO(AppointmentDetail dto, ResultSet rs)
			throws SQLException {
		dto.setAppointment_id(rs.getInt("appointment_id"));
		dto.setHospital_staff_id(rs.getInt("hospital_staff_id"));
		dto.setLocation_id(rs.getInt("location_id"));
		dto.setVisit_type_id(rs.getInt("visit_type_id"));
		dto.setPatient_id(rs.getInt("patient_id"));
		dto.setAppointment_date(new Date(rs.getTimestamp("appointment_date").getTime()));
		dto.setTenantid(rs.getInt("tenantid"));
		dto.setStatus(rs.getString("status"));
		dto = fillPatientDetails(dto, rs, dto.getPatient_id());
		dto = fillStaffDetails(dto, rs, dto.getHospital_staff_id());
		
	}

	protected List<AppointmentDetail> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<AppointmentDetail> resultList = new ArrayList<AppointmentDetail>();
		while (rs.next()) {
			AppointmentDetail dto = new AppointmentDetail();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}
	
	
}
