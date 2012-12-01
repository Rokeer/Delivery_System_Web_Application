package entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
	private int userId;
	private String username;
	private String password;
	private int permission;
	

	public User() {
		super();
	}


	public User(int userId, String username, String password, int permission) {
		super();
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.permission = permission;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getPermission() {
		return permission;
	}


	public void setPermission(int permission) {
		this.permission = permission;
	}

	
}
