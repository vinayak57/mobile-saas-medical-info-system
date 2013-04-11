package mis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mis.constants.SqlConstants;
import mis.model.AppointmentDetail;
import mis.model.EmergencyRequest;
import mis.util.DBConnection;

public enum EmergencyRequestDao {

	instance;
	
	private EmergencyRequestDao() {
		// TODO Auto-generated constructor stub
	}
	
	private static int count;
	
	public List<EmergencyRequest> getAllOpenEmergencyRequests(int tenantid)
	{
		List<EmergencyRequest> list = new ArrayList<EmergencyRequest>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllOpenEmergencyReq;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				list = fetchMultiResults(rs);

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

		return list;
	}
	
	public List<EmergencyRequest> getAllEmergencyRequests(int tenantid)
	{
		List<EmergencyRequest> list = new ArrayList<EmergencyRequest>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllEmergencyReq;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				list = fetchMultiResults(rs);

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

		return list;
	}
	
	protected EmergencyRequest fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			EmergencyRequest dto = new EmergencyRequest();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}

	protected void populateVO(EmergencyRequest dto, ResultSet rs)
			throws SQLException {
		dto.setEmergency_request_id(rs.getInt("emergency_request_id"));
		dto.setEmergencylocation(rs.getString("emergencylocation"));
		dto.setLatitude(rs.getString("latitude"));
		dto.setLongitude(rs.getString("longitude"));
		dto.setUserid(rs.getInt("userid"));
		dto.setTenantid(rs.getInt("tenantid"));
	}

	protected List<EmergencyRequest> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<EmergencyRequest> resultList = new ArrayList<EmergencyRequest>();
		while (rs.next()) {
			EmergencyRequest dto = new EmergencyRequest();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}
	
}
