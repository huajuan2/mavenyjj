package com.lanou.dao;

import java.util.List;

import com.lanou.entity.User;

public interface UserMapper {

	public List<User> findUsers();

	public boolean regUser(User user);

	public List<User> findUserByName(String username);
	
}
