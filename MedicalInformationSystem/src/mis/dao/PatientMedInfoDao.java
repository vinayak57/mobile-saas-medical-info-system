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
import mis.model.Patient;
import mis.model.PatientMedInfo;
import mis.model.Prescription;
import mis.model.VisitType;
import mis.util.DBConnection;
import mis.util.DateConvert;

public enum PatientMedInfoDao {

	instance;
	private static int userCount;

	private PatientMedInfoDao() {
	}
	
	public Map<String, PatientMedInfo> getAllPatientMedInfo(int patientid) {
		Map<String, PatientMedInfo> medInfoList = new HashMap<String, PatientMedInfo>();
		
		PatientMedInfo medInfoObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllPatientMedInfo;
			prest = con.prepareStatement(sqlStatement);
			System.out.println(patientid);
			prest.setInt(1, patientid);
			rs = prest.executeQuery();
			if (rs != null) {
				List<PatientMedInfo> resultList = fetchMultiResults(rs);

				for (PatientMedInfo medInfo : resultList)
					medInfoList.put(String.valueOf(userCount++), medInfo);
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

	public PatientMedInfo getAllPatientMedInfoById(int patientMedInfo) {
		PatientMedInfo presObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllPatientMedInfoById;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, patientMedInfo);
			rs = prest.executeQuery();
			if (rs != null) {
				presObj = fetchSingleResult(rs);

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

		return presObj;
	}

	
	public int putPatientMedInfoDetails(PatientMedInfo presc) {
		int result = 0;
		PatientMedInfo presObj = getAllPatientMedInfoById(presc.getPatient_med_info_id());
		if (presObj == null) {
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement prest = null;

			try {
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.insertPatientMedInfoDetails;
				prest = con.prepareStatement(sqlStatement);

				prest.setString(1, presc.getAllergies());
				prest.setString(2, presc.getPrecautions());
				prest.setString(3, presc.getSide_effects());
				prest.setInt(4, presc.getPatient_id());
				prest.setString(5, presc.getBloodgroup());
				prest.setInt(6, presc.getWeight());
				prest.setInt(7, presc.getHeight());
				prest.setDate(8, DateConvert.convertUtilToSQLdate(presc.getDob()));
				prest.setInt(9, presc.getAge());
				prest.setString(10, presc.getGender());
				prest.setInt(11, presc.getTenant_id());
				
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
				String sqlStatement = SqlConstants.updatePatientMedInfoDetails;
				prest = con.prepareStatement(sqlStatement);

				prest.setString(1, presc.getAllergies());
				prest.setString(2, presc.getPrecautions());
				prest.setString(3, presc.getSide_effects());
				prest.setInt(4, presc.getPatient_id());
				prest.setString(5, presc.getBloodgroup());
				prest.setInt(6, presc.getWeight());
				prest.setInt(7, presc.getHeight());
				prest.setDate(8, DateConvert.convertUtilToSQLdate(presc.getDob()));
				prest.setInt(9, presc.getAge());
				prest.setString(10, presc.getGender());
				prest.setInt(11, presc.getTenant_id());
				prest.setInt(12, presc.getPatient_med_info_id());
				
				result = prest.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	protected PatientMedInfo fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			PatientMedInfo dto = new PatientMedInfo();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}

	protected PatientMedInfo fillPatientDetails(PatientMedInfo dto, ResultSet rs, int patient_id)
	{
		Patient patientObj = PatientDao.instance.getPatientByPatientId(patient_id);
		
		dto.setDob(patientObj.getDob());
		dto.setGender(patientObj.getGender());
		dto.setFname(patientObj.getFname());
		dto.setLname(patientObj.getLname());
		return dto;
	}
	
	protected void populateVO(PatientMedInfo dto, ResultSet rs) throws SQLException {
		dto.setWeight(rs.getInt("weight"));
		dto.setHeight(rs.getInt("height"));
		dto.setBloodgroup(rs.getString("bloodgroup"));
		dto.setAllergies(rs.getString("allergies"));
		dto.setPrecautions(rs.getString("precautions"));
		dto.setSide_effects(rs.getString("side_effects"));
		dto.setPatient_med_info_id(rs.getInt("patient_med_info_id"));
		dto.setPatient_id(rs.getInt("patient_id"));
		dto.setAge(rs.getInt("age"));
		dto.setTenant_id(rs.getInt("tenant_id"));
		dto = fillPatientDetails(dto, rs, dto.getPatient_id());
		
		dto.setSurgicalHistory(SurgicalHistoryDao.instance.getSurgicalHistoryInfoByPatientId(dto.getPatient_id()));
		dto.setSocialHistory(SocialHistoryDao.instance.getSocialHistoryInfoByPatientId(dto.getPatient_id()));
		dto.setFamilyHistory(FamilyHistoryDao.instance.getFamilyHistoryInfoByPatientId(dto.getPatient_id()));

	}

	protected List<PatientMedInfo> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<PatientMedInfo> resultList = new ArrayList<PatientMedInfo>();
		while (rs.next()) {
			PatientMedInfo dto = new PatientMedInfo();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}
}
