package com.lanou.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {

	private Integer uId;
	private String username;
	private String password;
	private String phone;
	private String name;
	private String sex;
	private Date birthDate;
	private String qq;
	public User() {
		super();
	}

	public User(Integer uId, String username, String password, String phone, String name, String sex, Date birthDate, String qq) {
		this.uId = uId;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.name = name;
		this.sex = sex;
		this.birthDate = birthDate;
		this.qq = qq;
	}

	public Integer getuId() {
		return uId;
	}

	public void setuId(Integer uId) {
		this.uId = uId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	@Override
	public String toString() {
		return "User{" +
				"uId=" + uId +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", phone='" + phone + '\'' +
				", name='" + name + '\'' +
				", sex='" + sex + '\'' +
				", birthDate=" + birthDate +
				", qq='" + qq + '\'' +
				'}';
	}
}
