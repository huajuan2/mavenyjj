package com.lanou.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lanou.dao.UserMapper;
import com.lanou.entity.User;
import com.lanou.service.UserService;
import org.springframework.transaction.annotation.Transactional;

@Service("UserService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;

	public List<User> findUsers() {
		return userMapper.findUsers();
	}

	public boolean regUser(User user){
		userMapper.regUser(user);
		return true;
	}

	public List<User> findUserByName(String userName){                            
		List<User> users = userMapper.findUserByName(userName);
		return users;
	}

}
