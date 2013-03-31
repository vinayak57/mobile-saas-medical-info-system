package mis.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mis.constants.SqlConstants;
import mis.model.Patient;
import mis.model.User;
import mis.util.DBConnection;

public enum PatientDao {
	instance;


	private PatientDao() {}
	
	public int putUserDetails(Patient patient) {
		
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		int result = 0;
		
		User user = new User();
		user.setUsername(patient.getUsername());
		user.setPassword(patient.getPassword());
		user.setRoleid(patient.getRoleid());
		user.setTenantid(patient.getTenantid());
		
		
		
		if(UserDao.instance.putUserDetails(user) != 0)
			{
				User userobj = UserDao.instance.getUserByUsername(user.getUsername(), user.getTenantid());
				if (userobj != null) {
					// insert
					try {
						con = DBConnection.getConnection();
						String sqlStatement = SqlConstants.insertPatient;
						prest = con.prepareStatement(sqlStatement);
						prest.setString(1, patient.getFname());
						prest.setString(2, patient.getLname());
						prest.setString(3, patient.getEmail());
						prest.setString(4, patient.getGender());
						prest.setInt(5, patient.getPhone());
		//TODO: dob and location id
						//prest.setInt(6, patient.get);
						prest.setInt(6, userobj.getUserid());
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
			// update
		}
		
		
		
		

		return result;

	}

	
}
