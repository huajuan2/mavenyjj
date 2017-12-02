package com.lanou.service;

import java.util.List;

import com.lanou.entity.User;

public interface UserService {

	public List<User> findUsers();

	public boolean regUser(User user);

	public List<User> findUserByName(String userName);

}
