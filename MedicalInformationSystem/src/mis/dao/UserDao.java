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

	private Map<String, User> usersList = new HashMap<String, User>();
	private static int userCount;

	private UserDao() {

		intializeUsersList();

	}

	public Map<String, User> getAllUsers() {
		return usersList;
	}

	public void intializeUsersList() {
		userCount = 0;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllUsers;
			prest = con.prepareStatement(sqlStatement);

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

	}

	
	public void putUserDetails(String username, User user)
	{
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		
		User obj = getUserByUsername(username);
		if(obj==null)
		{
			//insert
			try {
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.insertUser;
				prest = con.prepareStatement(sqlStatement);
				prest.setString(1, user.getUsername());
				prest.setString(2, user.getPassword());
				prest.executeUpdate();

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
		else
		{
			//update
		}
		


	}
	
	public User getUserByUsername(String username) {

		User userObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getUser;
			prest = con.prepareStatement(sqlStatement);
			prest.setString(1, username);
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
