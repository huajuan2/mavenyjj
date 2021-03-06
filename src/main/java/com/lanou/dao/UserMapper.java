package com.lanou.dao;

import java.util.List;

import com.lanou.entity.User;

public interface UserMapper {

	public List<User> findUsers();

	public boolean regUser(User user);

	public List<User> findUserByName(String username);

	public User findUserById(Integer uId);

	public User findUserByNameAndPwd(User user);

	public int updateUserInfo(User user);

	public boolean updatePassword(User user);

	public void updateIpAndTime(User user);

	public boolean updateHeadImgUrl(User user);

	public boolean updateUserName(User user);



	public List<User> findAllUsers();

	public boolean deleteUser(Integer uId);
}
