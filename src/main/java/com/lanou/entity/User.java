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
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date birthDate;
	private String qq;
	private List<Goods> goodsList;
	private String addressIp;
	private String loginDate;
	private String headImgUrl;
	private String oldPassword;
	private List<Comment> commentList;

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public User() {
		super();
	}

	public User(Integer uId, String username, String password, String phone, String name, String sex, Date birthDate, String qq, List<Goods> goodsList, String addressIp, String loginDate, String headImgUrl, String oldPassword, List<Comment> commentList) {
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
		this.headImgUrl = headImgUrl;
		this.oldPassword = oldPassword;
		this.commentList = commentList;
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

	public List<Goods> getGoodsList() {
		return goodsList;
	}

	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
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

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
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
				", headImgUrl='" + headImgUrl + '\'' +
				", oldPassword='" + oldPassword + '\'' +
				'}';
	}
}
