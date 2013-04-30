package mis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mis.constants.SqlConstants;
import mis.model.FamilyHistory;
import mis.model.HospitalStaff;
import mis.model.Patient;
import mis.model.PatientMedInfo;
import mis.model.SurgicalHistory;
import mis.util.DBConnection;
import mis.util.DateConvert;

public enum SurgicalHistoryDao {

	instance;
	private static int userCount;

	private SurgicalHistoryDao() {
	}
	
	public SurgicalHistory getSurgicalHistoryByid(int surgical_history_id) {
		
		SurgicalHistory medInfoObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getSurgicalHistoryById;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, surgical_history_id);
			rs = prest.executeQuery();
			if (rs != null) {
				medInfoObj = fetchSingleResult(rs);

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

		return medInfoObj;
	}
	
	public List<SurgicalHistory> getSurgicalHistoryInfoByPatientId(int patientid) {
		
		//SurgicalHistory medInfoObj = null;
		List<SurgicalHistory> medInfoList = new ArrayList<SurgicalHistory>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getSurgicalHistoryInfoById;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, patientid);
			rs = prest.executeQuery();
			if (rs != null) {
				//medInfoObj = fetchSingleResult(rs);
				medInfoList = fetchMultiResults(rs);
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

		return medInfoList;
	}
	
	public int putSurgicalHistoryDetails(SurgicalHistory presc) {
		int result = 0;
		SurgicalHistory presObj = getSurgicalHistoryByid(presc.getSurgical_history_id());
		if (presObj == null) {
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement prest = null;

			try {
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.insertSurgicalHistoryDetails;
				prest = con.prepareStatement(sqlStatement);

				prest.setString(1, presc.getType_of_surgery());
				prest.setDate(2, DateConvert.convertUtilToSQLdate(presc.getDate_of_surgery()));
				prest.setInt(3, presc.getHospital_staff_id());
				prest.setInt(4, presc.getPatient_id());
				prest.setInt(5, presc.getTenant_id());
				result = prest.executeUpdate();

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

		}
		else
		{
			//update
			
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement prest = null;

			try {
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.updateSurgicalHistoryDetails;
				prest = con.prepareStatement(sqlStatement);

				prest.setString(1, presc.getType_of_surgery());
				prest.setDate(2, DateConvert.convertUtilToSQLdate(presc.getDate_of_surgery()));
				prest.setInt(3, presc.getHospital_staff_id());
				prest.setInt(4, presc.getPatient_id());
				prest.setInt(5, presc.getTenant_id());
				prest.setInt(6, presc.getSurgical_history_id());
				
				result = prest.executeUpdate();

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
			
		}
		return result;
	}
	
	
	protected SurgicalHistory fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			SurgicalHistory dto = new SurgicalHistory();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}

	protected SurgicalHistory fillStaffDetails(SurgicalHistory dto, ResultSet rs, int hospital_staff_id)
	{
		System.out.println("in fillstaff" + hospital_staff_id);
		HospitalStaff patientObj = HospitalStaffDao.instance.getStaffByStaffId(hospital_staff_id);
		System.out.println("middle fillstaff");
		dto.setHospital_staff_name(patientObj.getLname() + ", " + patientObj.getFname());
		return dto;
	}

	protected void populateVO(SurgicalHistory dto, ResultSet rs) throws SQLException {
		
		dto = fillStaffDetails(dto, rs, rs.getInt("hospital_staff_id"));
		
		System.out.println("after fillstaff");
		
		dto.setHospital_staff_id(rs.getInt("hospital_staff_id"));
		dto.setSurgical_history_id(rs.getInt("surgical_history_id"));
		dto.setType_of_surgery(rs.getString("type_of_surgery"));
		dto.setDate_of_surgery(DateConvert.convertSQLToUtilDate(rs.getDate("date_of_surgery")));
		dto.setPatient_id(rs.getInt("patient_id"));
		dto.setTenant_id(rs.getInt("tenant_id"));
		
	}

	protected List<SurgicalHistory> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<SurgicalHistory> resultList = new ArrayList<SurgicalHistory>();
		while (rs.next()) {
			SurgicalHistory dto = new SurgicalHistory();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}
	
	
}
