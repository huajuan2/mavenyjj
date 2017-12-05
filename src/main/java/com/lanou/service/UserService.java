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

	public boolean updatePassword(String newpassword);

	public void updateIpAndTime(User user);

}
