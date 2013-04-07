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
import mis.model.PatientMedInfo;
import mis.model.Prescription;
import mis.model.VisitType;
import mis.util.DBConnection;

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
				prest.setInt(1, presc.getAppointment_id());
				prest.setInt(2, presc.getPrescription_id());
				prest.setString(3, presc.getAllergies());
				prest.setString(4, presc.getWarning());
				prest.setString(5, presc.getSide_effects());
				prest.setInt(6, presc.getPatient_id());
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

	protected PatientMedInfo fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			PatientMedInfo dto = new PatientMedInfo();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}

	protected void populateVO(PatientMedInfo dto, ResultSet rs) throws SQLException {
		dto.setAppointment_id(rs.getInt("appointment_id"));
		dto.setPrescription_id(rs.getInt("prescription_id"));

		dto.setAllergies(rs.getString("allergies"));
		dto.setWarning(rs.getString("warning"));
		dto.setSide_effects(rs.getString("side_effects"));
		dto.setPatient_med_info_id(rs.getInt("patient_med_info_id"));
		dto.setPatient_id(rs.getInt("patient_id"));

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
