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
import mis.model.Hospital;
import mis.model.HospitalStaff;
import mis.model.User;
import mis.util.DBConnection;

public enum HospitalDao {

	instance;
	private static int userCount;
	
	HospitalDao(){}
	
	public Hospital getHospitalById(int hospital_id)
	{
		Hospital staffObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getHospitalByid;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, hospital_id);
			//prest.setInt(2, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				staffObj = fetchSingleResult(rs);

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

		return staffObj;
	}
	
	public Map<String, Hospital> getAllHospital(int tenantid) {

		Map<String, Hospital> staffList = new HashMap<String, Hospital>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllHospital;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				List<Hospital> resultList = fetchMultiResults(rs);

				for (Hospital staff : resultList)
					staffList.put(String.valueOf(userCount++), staff);

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

		return staffList;
	}
	
	
	public int putHospitalDetails(Hospital hospital) {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		PreparedStatement prest1 = null;
		int result = 0;

//		Hospital obj = new Hospital();
//		obj.setHospital_name(hospital.getHospital_name());
//		obj.setLocation_id(hospital.getLocation_id());
//		obj.setTenantid(hospital.getTenantid());
//		
		
			Hospital hospobj = getHospitalById(hospital.getHospital_id());
			if (hospobj == null) {
				// insert
				try {
					con = DBConnection.getConnection();
					String sqlStatement = SqlConstants.insertHospital;
					prest = con.prepareStatement(sqlStatement);
					prest.setString(1, hospital.getHospital_name());
					prest.setInt(2, hospital.getLocation_id());
					prest.setInt(3, hospital.getTenantid());
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

		else {
			System.out.println("calling update");
			try {
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.updateStaff;
				prest1 = con.prepareStatement(sqlStatement);
				prest1.setString(1, hospital.getHospital_name());
				prest1.setInt(2, hospital.getLocation_id());
				prest1.setInt(3, hospital.getTenantid());
				prest1.setInt(4, hospital.getHospital_id());
				result = prest1.executeUpdate();
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
	
	
	protected Hospital fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Hospital dto = new Hospital();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}

	protected void populateVO(Hospital dto, ResultSet rs) throws SQLException {
		dto.setHospital_name(rs.getString("hospital_name"));
		dto.setHospital_id(rs.getInt("hospital_id"));
		dto.setTenantid(rs.getInt("tenantid"));
		dto.setLocation_id(rs.getInt("location_id"));
	}

	protected List<Hospital> fetchMultiResults(ResultSet rs) throws SQLException {
		List<Hospital> resultList = new ArrayList<Hospital>();
		while (rs.next()) {
			Hospital dto = new Hospital();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}
	
}
