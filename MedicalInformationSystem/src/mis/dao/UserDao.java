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
import mis.model.User;
import mis.util.DBConnection;

// Singleton UserDao
// improve this
public enum UserDao {
	instance;


	private static int userCount;

	private UserDao() {}

	public Map<String, User> getAllUsers(int tenantid) {
		
		Map<String, User> usersList = new HashMap<String, User>();
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllUsers;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				List<User> resultList = fetchMultiResults(rs);

				for (User user : resultList)
					usersList.put(String.valueOf(userCount++), user);

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
		
		return usersList;
	}

//	public int removeUser(int userid)
//	{
//		Connection con = null;
//		PreparedStatement prest = null;
//		int rowsUpdated = 0;
//		try{
//		con = DBConnection.getConnection();
//		String sqlStatement = SqlConstants.deleteUser;
//		prest = con.prepareStatement(sqlStatement);
//		prest.setInt(1, userid);
//
//		rowsUpdated = prest.executeUpdate();
//		
//		return rowsUpdated;
//		}catch (Exception e) {
//			System.out.println(e);
//		} finally {
//			try {
//				con.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return rowsUpdated;
//	}
	
	public int putUserDetails(User user) {
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		int result = 0;
		User obj = getUserByUsername(user.getUsername(),user.getTenantid());
		if (obj == null) {
			// insert
			System.out.println("calling insert user");
			try {
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.insertUser;
				prest = con.prepareStatement(sqlStatement);
				prest.setString(1, user.getUsername());
				prest.setString(2, user.getPassword());
				prest.setInt(3, user.getRoleid());
				prest.setInt(4, user.getTenantid());

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

		} else {
			// update
			try {
				System.out.println("calling udate user");
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.updatePassword;
				prest = con.prepareStatement(sqlStatement);
				prest.setString(1, user.getPassword());
				prest.setInt(2, user.getUserid());

				result = prest.executeUpdate();
System.out.println(result);
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

	public User getUserByUserid(int userid, int tenantid)
	{
		User userObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getUserByid;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, userid);
			prest.setInt(2, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				userObj = fetchSingleResult(rs);

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

		return userObj;
	}
	
	public User getUserByUsername(String username, int tenantid) {

		User userObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getUser;
			prest = con.prepareStatement(sqlStatement);
			prest.setString(1, username);
			prest.setInt(2, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				userObj = fetchSingleResult(rs);

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

		return userObj;
	}

	protected User fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			User dto = new User();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}

	protected void populateVO(User dto, ResultSet rs) throws SQLException {
		dto.setUsername(rs.getString("userName"));
		dto.setPassword(rs.getString("password"));
		dto.setRoleid(rs.getInt("roleid"));
		dto.setTenantid(rs.getInt("tenantid"));
		dto.setUserid(rs.getInt("userid"));
	}

	protected List<User> fetchMultiResults(ResultSet rs) throws SQLException {
		List<User> resultList = new ArrayList<User>();
		while (rs.next()) {
			User dto = new User();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}
}
