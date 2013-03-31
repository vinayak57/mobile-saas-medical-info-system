package mis.test;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User implements Serializable {
	private int userid;
	private String username;
	private String password;
	private int tenantid;
	private int roleid;
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public int getUserid() {
		return userid;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUsername() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	
	public void setTenantid(int tenantid) {
		this.tenantid = tenantid;
	}
	public int getTenantid() {
		return tenantid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public int getRoleid() {
		return roleid;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ",username=" + username + ", password=" + password + "]";
	}

}
