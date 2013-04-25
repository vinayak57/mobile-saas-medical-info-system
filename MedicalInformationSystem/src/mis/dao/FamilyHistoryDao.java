package mis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mis.constants.SqlConstants;
import mis.model.FamilyHistory;
import mis.model.SocialHistory;
import mis.util.DBConnection;

public enum FamilyHistoryDao {

	instance;
	private static int userCount;

	private FamilyHistoryDao() {
	}
	
	public FamilyHistory getFamilyHistoryByid(int family_history_id) {
		
		FamilyHistory medInfoObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getFamilyHistoryById;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, family_history_id);
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
	
	public List<FamilyHistory> getFamilyHistoryInfoByPatientId(int patientid) {
		
		List<FamilyHistory> medInfoList = new ArrayList<FamilyHistory>();
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getFamilyHistoryInfoById;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, patientid);
			rs = prest.executeQuery();
			if (rs != null) {		
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
	
	public int putFamilyHistoryDetails(FamilyHistory presc) {
		int result = 0;
		FamilyHistory presObj = getFamilyHistoryByid(presc.getFamily_history_id());
		if (presObj == null) {
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement prest = null;

			try {
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.insertFamilyHistoryDetails;
				prest = con.prepareStatement(sqlStatement);

				prest.setString(1, presc.getMember_name());
				prest.setString(2, presc.getRelationship());
				prest.setString(3, presc.getDisease());
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
				String sqlStatement = SqlConstants.updateFamilyHistoryDetails;
				prest = con.prepareStatement(sqlStatement);

				prest.setString(1, presc.getMember_name());
				prest.setString(2, presc.getRelationship());
				prest.setString(3, presc.getDisease());
				prest.setInt(4, presc.getPatient_id());
				prest.setInt(5, presc.getTenant_id());
				prest.setInt(6, presc.getFamily_history_id());
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
	
	
	protected FamilyHistory fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			FamilyHistory dto = new FamilyHistory();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}


	protected void populateVO(FamilyHistory dto, ResultSet rs) throws SQLException {
		dto.setFamily_history_id(rs.getInt("family_history_id"));
		dto.setMember_name(rs.getString("member_name"));
		dto.setRelationship(rs.getString("relationship"));
		dto.setDisease(rs.getString("disease"));
		dto.setPatient_id(rs.getInt("patient_id"));
		dto.setTenant_id(rs.getInt("tenant_id"));
	}

	protected List<FamilyHistory> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<FamilyHistory> resultList = new ArrayList<FamilyHistory>();
		while (rs.next()) {
			FamilyHistory dto = new FamilyHistory();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}
}
