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
import mis.model.Location;
import mis.model.Patient;
import mis.model.User;
import mis.dbconnection.DBConnection;

public enum LocationDao {
	instance;

	private static int userCount;

	private LocationDao() {
	}

	public Location getLocationById(int locationId) {
		Location locationObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getLocationByid;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, locationId);
			rs = prest.executeQuery();
			if (rs != null) {
				locationObj = fetchSingleResult(rs);
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
		return locationObj;
	}

	public Map<String, Location> getAllLocations(int tenantid) {

		Map<String, Location> locationList = new HashMap<String, Location>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllLocations;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				List<Location> resultList = fetchMultiResults(rs);

				for (Location loc : resultList)
					locationList.put(String.valueOf(userCount++), loc);

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

		return locationList;
	}

	public int getLocationIdByLoc(Location loc)
	{
		Location locationObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getLocationIdByLoc;
			prest = con.prepareStatement(sqlStatement);
			prest.setString(1, loc.getAddr1());
			prest.setString(2, loc.getAddr2());
			prest.setString(3, loc.getCity());
			prest.setString(4, loc.getState());
			prest.setInt(5, loc.getZipcode());
			rs = prest.executeQuery();
			if (rs != null) {
				System.out.println("location match found");
				locationObj = fetchSingleResult(rs);
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
		return locationObj==null?0:locationObj.getLocation_id();
	}
	
	public int putLocationDetails(Location loc) {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		int result = 0;

		Location locobj = getLocationById(loc.getLocation_id());
		if (locobj == null) {
			// insert
			try {
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.insertLocation;
				prest = con.prepareStatement(sqlStatement);
				prest.setString(1, loc.getAddr1());
				prest.setString(2, loc.getAddr2());
				prest.setString(3, loc.getCity());
				prest.setString(4, loc.getState());
				prest.setString(5, loc.getCountry());
				prest.setInt(6, loc.getZipcode());
				// TODO: dob and location id
				// prest.setInt(6, patient.get);
				System.out.println(loc.getTenantid());
				prest.setInt(7, loc.getTenantid());
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

	protected Location fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Location dto = new Location();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}

	protected void populateVO(Location dto, ResultSet rs) throws SQLException {
		dto.setLocation_id(rs.getInt("location_id"));
		dto.setAddr1(rs.getString("addr1"));
		dto.setAddr2(rs.getString("addr2"));
		dto.setZipcode(rs.getInt("zipcode"));
		dto.setTenantid(rs.getInt("tenantid"));
		dto.setCity(rs.getString("city"));
		dto.setState(rs.getString("state"));
		dto.setCountry(rs.getString("country"));
	}

	protected List<Location> fetchMultiResults(ResultSet rs)
			throws SQLException {
		List<Location> resultList = new ArrayList<Location>();
		while (rs.next()) {
			Location dto = new Location();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}

}
