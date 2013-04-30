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
import mis.dbconnection.DBConnection;
import mis.util.DateConvert;

public enum SocialHistoryDao {

	instance;
	private static int userCount;

	private SocialHistoryDao() {
	}
	
	public SocialHistory getSocialHistoryByid(int social_history_id) {
		
		SocialHistory medInfoObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getSocialHistoryById;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, social_history_id);
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
	
	public List<SocialHistory> getSocialHistoryInfoByPatientId(int patientid) {
		
		List<SocialHistory> medInfoList = new ArrayList<SocialHistory>();
		//SocialHistory medInfoObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getSocialHistoryInfoById;
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
	
	public int putSurgicalHistoryDetails(SocialHistory presc) {
		int result = 0;
		SocialHistory presObj = getSocialHistoryByid(presc.getSocial_history_id());
		if (presObj == null) {
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement prest = null;

			try {
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.insertSocialHistoryDetails;
				prest = con.prepareStatement(sqlStatement);

				prest.setString(1, presc.getIsSmoker());
				prest.setString(2, presc.getIsAlcoholic());
				prest.setString(3, presc.getIsdrug_consumer());
				prest.setString(4, presc.getVaccinations());
				prest.setInt(5, presc.getNo_of_pregnancies());
				prest.setInt(6, presc.getPatient_id());
				prest.setInt(7, presc.getTenant_id());
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
				String sqlStatement = SqlConstants.updateSocialHistoryDetails;
				prest = con.prepareStatement(sqlStatement);

				prest.setString(1, presc.getIsSmoker());
				prest.setString(2, presc.getIsAlcoholic());
				prest.setString(3, presc.getIsdrug_consumer());
				prest.setString(4, presc.getVaccinations());
				prest.setInt(5, presc.getNo_of_pregnancies());
				prest.setInt(6, presc.getPatient_id());
				prest.setInt(7, presc.getTenant_id());
				prest.setInt(7, presc.getSocial_history_id());
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
	
	
	protected SocialHistory fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			SocialHistory dto = new SocialHistory();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}


	protected void populateVO(SocialHistory dto, ResultSet rs) throws SQLException {
		dto.setSocial_history_id(rs.getInt("social_history_id"));
		dto.setIsSmoker(rs.getString("smoker"));
		dto.setIsAlcoholic(rs.getString("alcoholic"));
		dto.setIsdrug_consumer(rs.getString("drug_consumer"));
		dto.setVaccinations(rs.getString("vaccinations"));
		dto.setPatient_id(rs.getInt("patient_id"));
		dto.setNo_of_pregnancies(rs.getInt("no_of_pregnancies"));
		dto.setTenant_id(rs.getInt("tenant_id"));
	}

	protected List<SocialHistory> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<SocialHistory> resultList = new ArrayList<SocialHistory>();
		while (rs.next()) {
			SocialHistory dto = new SocialHistory();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}
	
}
