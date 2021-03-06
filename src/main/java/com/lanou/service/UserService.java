package com.lanou.service;

import java.util.Date;
import java.util.List;

import com.lanou.entity.User;

public interface UserService {

	public List<User> findUsers();

	public boolean regUser(User user);

	public List<User> findUserByName(String userName);

	public User findUserById(Integer uId);

	public User findUserByNameAndPwd(User user);

	public boolean updateUserInfo(User user);

	public boolean updatePassword(User user);

	public void updateIpAndTime(User user);

	public boolean updateHeadImgUrl(User user);

	public boolean updateUserName(User user);

	//后台用户信息管理
	public List<User> findAllUsers();

	public boolean deleteUser(Integer uId);
}
