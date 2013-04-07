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

public enum PrescriptionDao {

	instance;
	private static int userCount;

	private PrescriptionDao() {
	}
	
	public Map<String, Prescription> getAllPrescription(int tenantid) {
		Map<String, Prescription> prescList = new HashMap<String, Prescription>();
		
		Prescription prescObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllPrescription;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				List<Prescription> resultList = fetchMultiResults(rs);

				for (Prescription medInfo : resultList)
					prescList.put(String.valueOf(userCount++), medInfo);
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

		return prescList;
	}

	public Prescription getPrescriptionById(int prescription_id) {
		Prescription presObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getPrescriptionById;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, prescription_id);
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

	
	public int putPrescriptionDetails(Prescription presc) {
		int result = 0;
		Prescription presObj = getPrescriptionById(presc.getPrescription_id());
		if (presObj == null) {
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement prest = null;

			try {
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.insertPrescriptionDetails;
				prest = con.prepareStatement(sqlStatement);
				prest.setInt(1, presc.getDrug_id());
				prest.setString(2, presc.getDose());
				prest.setTimestamp(3, new Timestamp(presc.getStart_date().getTime()));
				prest.setTimestamp(4, new Timestamp(presc.getEnd_date().getTime()));
				prest.setString(5, presc.getInstruction());
				System.out.println(presc.getAppointment_id());
				prest.setInt(6, presc.getAppointment_id());
				prest.setInt(7, presc.getTenantid());
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

		}
		return result;
	}

	protected Prescription fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Prescription dto = new Prescription();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}

	protected void populateVO(Prescription dto, ResultSet rs) throws SQLException {
		dto.setPrescription_id(rs.getInt("prescription_id"));
		dto.setDrug_id(rs.getInt("drug_id"));
		dto.setDose(rs.getString("dose"));
		dto.setStart_date(new Date(rs.getTimestamp("start_date").getTime()));
		dto.setEnd_date(new Date(rs.getTimestamp("end_date").getTime()));
		dto.setInstruction(rs.getString("instruction"));
		dto.setAppointment_id(rs.getInt("appointment_id"));
		dto.setTenantid(rs.getInt("tenantid"));

	}

	protected List<Prescription> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<Prescription> resultList = new ArrayList<Prescription>();
		while (rs.next()) {
			Prescription dto = new Prescription();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}
}
