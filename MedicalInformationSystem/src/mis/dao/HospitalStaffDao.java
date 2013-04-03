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
import mis.model.HospitalStaff;
import mis.model.User;
import mis.util.DBConnection;

public enum HospitalStaffDao {
	instance;
	private static int userCount;

	private HospitalStaffDao() {
	}
	
	
	public HospitalStaff getStaffById(int staffid, int tenantid)
	{
		HospitalStaff staffObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getStaffByid;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, staffid);
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
	
	
	public Map<String, HospitalStaff> getAllStaff(int tenantid) {

		Map<String, HospitalStaff> staffList = new HashMap<String, HospitalStaff>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllStaff;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				List<HospitalStaff> resultList = fetchMultiResults(rs);

				for (HospitalStaff staff : resultList)
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
	
	
	
	public int putStaffDetails(HospitalStaff staff) {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		PreparedStatement prest1 = null;
		int result = 0;

		User user = new User();
		user.setUsername(staff.getUsername());
		user.setPassword(staff.getPassword());
		user.setRoleid(staff.getRoleid());
		user.setTenantid(staff.getTenantid());
		System.out.println(user.getUsername() + user.getTenantid());
		if (UserDao.instance.getUserByUsername(user.getUsername(),
				user.getTenantid()) == null) {
			System.out.println("calling insert");
			UserDao.instance.putUserDetails(user);
			User userobj = UserDao.instance.getUserByUsername(
					user.getUsername(), user.getTenantid());
			if (userobj != null) {
				// insert
				try {
					con = DBConnection.getConnection();
					String sqlStatement = SqlConstants.insertStaff;
					prest = con.prepareStatement(sqlStatement);
					prest.setString(1, staff.getFname());
					prest.setString(2, staff.getLname());
					prest.setString(3, staff.getDetails());
					prest.setString(4, staff.getSpeciality());
					// TODO: dob and location id
					// prest.setInt(6, patient.get);
					prest.setInt(5, userobj.getUserid());
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
		}

		else {
			System.out.println("calling update");
			try {
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.updateStaff;
				prest1 = con.prepareStatement(sqlStatement);
				prest1.setString(1, staff.getFname());
				prest1.setString(2, staff.getLname());
				prest1.setString(3, staff.getDetails());
				prest1.setString(4, staff.getSpeciality());
				// TODO: dob and location id
				// prest.setInt(6, patient.get);
				prest1.setInt(5, staff.getUserid());
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
	
	protected HospitalStaff fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			HospitalStaff dto = new HospitalStaff();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}

	protected void populateVO(HospitalStaff dto, ResultSet rs) throws SQLException {
		dto.setUsername(rs.getString("userName"));
		dto.setPassword(rs.getString("password"));
		dto.setRoleid(rs.getInt("roleid"));
		dto.setTenantid(rs.getInt("tenantid"));
		dto.setHospitalStaffId(rs.getInt("hospital_staff_id"));
		dto.setFname(rs.getString("fname"));
		dto.setLname(rs.getString("lname"));
		dto.setDetails(rs.getString("details"));
		dto.setSpeciality(rs.getString("speciality"));
		dto.setUserid(rs.getInt("userid"));
	}

	protected List<HospitalStaff> fetchMultiResults(ResultSet rs) throws SQLException {
		List<HospitalStaff> resultList = new ArrayList<HospitalStaff>();
		while (rs.next()) {
			HospitalStaff dto = new HospitalStaff();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}
	
}
