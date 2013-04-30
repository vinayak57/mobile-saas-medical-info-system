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
import mis.model.VisitType;
import mis.dbconnection.DBConnection;

public enum VisitTypeDao {
	instance;
	private static int userCount;

	private VisitTypeDao() {
	}

	public VisitType getVisitbyVisitType(VisitType visitType) {
		VisitType visitObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getVisitTypeByVisit;
			prest = con.prepareStatement(sqlStatement);
			prest.setString(1, visitType.getVisit_type());
			prest.setInt(2, visitType.getTenantid());
			rs = prest.executeQuery();
			if (rs != null) {
				visitObj = fetchSingleResult(rs);

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

		return visitObj;
	}

	public VisitType getVisitTypeById(int drugid) {
		VisitType visitObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getVisitTypeById;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, drugid);
			rs = prest.executeQuery();
			if (rs != null) {
				visitObj = fetchSingleResult(rs);

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

		return visitObj;
	}
	

	public Map<String, VisitType> getAllVisitType(int tenantid) {

		Map<String, VisitType> visitList = new HashMap<String, VisitType>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllVisitType;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				List<VisitType> resultList = fetchMultiResults(rs);

				for (VisitType visit : resultList)
					visitList.put(String.valueOf(userCount++), visit);

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

		return visitList;
	}

	public int putVisitDetails(VisitType visit) {
		int result = 0;
		VisitType visitObj = getVisitbyVisitType(visit);
		if (visitObj == null) {
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement prest = null;

			try {
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.putVisitDetails;
				prest = con.prepareStatement(sqlStatement);
				prest.setString(1, visit.getVisit_type());
				prest.setString(2, visit.getDescription());
				prest.setInt(3, visit.getTenantid());
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

	protected VisitType fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			VisitType dto = new VisitType();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}

	protected void populateVO(VisitType dto, ResultSet rs) throws SQLException {
		dto.setVisit_type(rs.getString("visit_type"));
		dto.setDescription(rs.getString("description"));
		dto.setVisit_type_id(rs.getInt("visit_type_id"));
		dto.setTenantid(rs.getInt("tenantid"));

	}

	protected List<VisitType> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<VisitType> resultList = new ArrayList<VisitType>();
		while (rs.next()) {
			VisitType dto = new VisitType();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}
}
