package entity;

public class Admin {
	
	String adminId;
	String adminPassword;
	
	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public Admin(String adminId, String adminPassword) {
		this.adminId = adminId;
		this.adminPassword = adminPassword;
	}
	
	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminPassword() {
		return adminPassword;
	}

	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	@Override
	public String toString() {
		return "Admin [adminId=" + adminId + ", adminPassword=" + adminPassword + "]";
	}
		
}
