package com.lanou.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

public class User {

	private Integer uId;
	private String username;
	private String password;
	private String phone;
	private String name;
	private String sex;
	@DateTimeFormat(pattern="yyyy-MM-dd hh-mm-s")
	private Date birthDate;
	private String qq;
	private List<Goods> goodsList;
	private String addressIp;
	private String loginDate;

	public User() {
		super();
	}

	public User(Integer uId, String username) {
		this.uId = uId;
		this.username = username;
		this.password = password;
		this.phone = phone;
		this.name = name;
		this.sex = sex;
		this.birthDate = birthDate;
		this.qq = qq;
		this.goodsList = goodsList;
		this.addressIp = addressIp;
		this.loginDate = loginDate;
	}

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
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

	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
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

	public String getAddressIp() {
		return addressIp;
	}

	public void setAddressIp(String addressIp) {
		this.addressIp = addressIp;
	}

	public String getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(String loginDate) {
		this.loginDate = loginDate;
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
				", goodsList=" + goodsList +
				", addressIp='" + addressIp + '\'' +
				", loginDate='" + loginDate + '\'' +
				'}';
	}
}
