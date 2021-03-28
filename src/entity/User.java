package entity;

import java.util.Date;

public class User {

	private String userId;
	private String userPassword;
	private String userName;
	private String userEmail;
	private Date reg_Date;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	

	public User(String userId, String userPassword, String userName, String userEmail) {
		super();
		this.userId = userId;
		this.userPassword = userPassword;
		this.userName = userName;
		this.userEmail = userEmail;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserPassword() {
		return userPassword;
	}


	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public Date getReg_Date() {
		return reg_Date;
	}


	public void setReg_Date(Date reg_Date) {
		this.reg_Date = reg_Date;
	}


	@Override
	public String toString() {
		return "User [userId=" + userId + ", userPassword=" + userPassword + ", userName=" + userName + ", userEmail="
				+ userEmail + ", reg_Date=" + reg_Date + "]";
	}
	
	
	
	
	
	
}
