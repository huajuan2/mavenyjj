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

	public User findUserById(Integer uId){
		User user = userMapper.findUserById(uId);
		return user;
	};

	public User findUserByNameAndPwd(User user){
		User user1 = userMapper.findUserByNameAndPwd(user);
		return user1;
	};

	public boolean updateUserInfo(User user){
		int result = userMapper.updateUserInfo(user);
		if (result==0){
			return false;
		}
		return true;
	};

	public boolean updatePassword(User user){
		boolean result = userMapper.updatePassword(user);
		if (result){
			return true;
		}
		return false;
	};

	public void updateIpAndTime(User user){
		userMapper.updateIpAndTime(user);
	};


	public boolean updateHeadImgUrl(User user){
		return userMapper.updateHeadImgUrl(user);
	};

	public boolean updateUserName(User user){
		return userMapper.updateUserName(user);
	};


	public List<User> findAllUsers(){
		return userMapper.findAllUsers();
	};

	public boolean deleteUser(Integer uId){
		boolean result = userMapper.deleteUser(uId);
		return  result;
	};
}

