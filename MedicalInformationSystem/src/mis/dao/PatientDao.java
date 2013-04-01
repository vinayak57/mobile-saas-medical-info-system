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
import mis.model.Patient;
import mis.model.User;
import mis.util.DBConnection;

public enum PatientDao {
	instance;

	private static int userCount;

	private PatientDao() {
	}

	public Patient getPatientById(int patientId, int tenantid)
	{
		Patient patientObj = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getPatientByid;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, patientId);
			//prest.setInt(2, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				patientObj = fetchSingleResult(rs);

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

		return patientObj;
	}
	
	public Map<String, Patient> getAllPatients(int tenantid) {

		Map<String, Patient> patientsList = new HashMap<String, Patient>();

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		try {
			con = DBConnection.getConnection();
			String sqlStatement = SqlConstants.getAllPatients;
			prest = con.prepareStatement(sqlStatement);
			prest.setInt(1, tenantid);
			rs = prest.executeQuery();
			if (rs != null) {
				List<Patient> resultList = fetchMultiResults(rs);

				for (Patient patient : resultList)
					patientsList.put(String.valueOf(userCount++), patient);

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

		return patientsList;
	}

	public int putUserDetails(Patient patient) {

		Connection con = null;
		ResultSet rs = null;
		PreparedStatement prest = null;
		PreparedStatement prest1 = null;
		int result = 0;

		User user = new User();
		user.setUsername(patient.getUsername());
		user.setPassword(patient.getPassword());
		user.setRoleid(patient.getRoleid());
		user.setTenantid(patient.getTenantid());

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
					String sqlStatement = SqlConstants.insertPatient;
					prest = con.prepareStatement(sqlStatement);
					prest.setString(1, patient.getFname());
					prest.setString(2, patient.getLname());
					prest.setString(3, patient.getEmail());
					prest.setString(4, patient.getGender());
					prest.setInt(5, patient.getPhone());
					// TODO: dob and location id
					// prest.setInt(6, patient.get);
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
			System.out.println("calling update");
			try {
				con = DBConnection.getConnection();
				String sqlStatement = SqlConstants.updatePatient;
				prest1 = con.prepareStatement(sqlStatement);
				prest1.setString(1, patient.getFname());
				prest1.setString(2, patient.getLname());
				prest1.setString(3, patient.getEmail());
				prest1.setString(4, patient.getGender());
				prest1.setInt(5, patient.getPhone());
				// TODO: dob and location id
				// prest.setInt(6, patient.get);
				prest1.setInt(6, patient.getPatientId());
				result = prest1.executeUpdate();
				System.out.println(patient.toString() + prest1.toString());
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

		System.out.println(result);
		return result;

	}

	protected Patient fetchSingleResult(ResultSet rs) throws SQLException {
		if (rs.next()) {
			Patient dto = new Patient();
			populateVO(dto, rs);
			return dto;
		} else {
			return null;
		}
	}

	protected void populateVO(Patient dto, ResultSet rs) throws SQLException {
		dto.setUsername(rs.getString("userName"));
		dto.setPassword(rs.getString("password"));
		dto.setRoleid(rs.getInt("roleid"));
		dto.setTenantid(rs.getInt("tenantid"));
		dto.setPatientId(rs.getInt("patient_id"));
		dto.setFname(rs.getString("fname"));
		dto.setLname(rs.getString("lname"));
		dto.setEmail(rs.getString("email"));
		dto.setGender(rs.getString("gender"));
		dto.setPhone(rs.getInt("phone"));
		dto.setUserid(rs.getInt("userid"));
	}

	protected List<Patient> fetchMultiResults(ResultSet rs) throws SQLException {
		List<Patient> resultList = new ArrayList<Patient>();
		while (rs.next()) {
			Patient dto = new Patient();
			populateVO(dto, rs);
			resultList.add(dto);
		}

		return resultList;
	}

}
