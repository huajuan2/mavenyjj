package com.lanou.entity;

public class User {

	private int uId;
	private String username;
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "User [uId=" + uId + ", username=" + username + "]";
	}
	
	
}
