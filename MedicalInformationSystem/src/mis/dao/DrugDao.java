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
import mis.model.Drug;
import mis.model.User;
import mis.dbconnection.DBConnection;

public enum DrugDao {
	instance;
	private static int userCount;

	private DrugDao() {
	}

	public Drug getDrugById(int drugid) {
		Drug drugObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getDrugByid;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, drugid);
			rs = prest.executeQuery();
			if (rs != null) {
				drugObj = fetchSingleResult(rs);

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

		return drugObj;
	}

	public Map<String, Drug> getAllDrug(int tenantid) {

		Map<String, Drug> drugList = new HashMap<String, Drug>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllDrug;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				List<Drug> resultList = fetchMultiResults(rs);

				for (Drug drug : resultList)
					drugList.put(String.valueOf(userCount++), drug);

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

		return drugList;
	}

	public int insertDrug(Drug drug) {
		System.out.println("calling insert");
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		int result = 0;

		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.insertDrug;
			prest = con.prepareStatement(sqlStatement);
			prest.setString(1, drug.getName());
			prest.setString(2, drug.getPower());
			prest.setInt(3, drug.getTenantid());
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

	public int updateDrug(Drug drug) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		int result = 0;

		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.updateDrug;
			prest = con.prepareStatement(sqlStatement);
			prest.setString(1, drug.getName());
			prest.setString(2, drug.getPower());
			prest.setInt(3, drug.getDrugId());
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

	public int putDrugDetails(Drug drug) {

		Drug drugObj = getDrugById(drug.getDrugId());
		if (drugObj == null) {
			return insertDrug(drug);
		} else {
			return updateDrug(drug);
		}

	}

	protected Drug fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Drug dto = new Drug();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}

	protected void populateVO(Drug dto, ResultSet rs)
			throws SQLException {
		dto.setName(rs.getString("name"));
		dto.setPower(rs.getString("power"));
		dto.setDrugId(rs.getInt("drug_id"));
		dto.setTenantid(rs.getInt("tenantid"));
		
	}

	protected List<Drug> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<Drug> resultList = new ArrayList<Drug>();
		while (rs.next()) {
			Drug dto = new Drug();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}

}
