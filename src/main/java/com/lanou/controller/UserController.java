package com.lanou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanou.entity.User;
import com.lanou.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/findUsers.do")
	public String finds(Model model) {
		List<User> users = userService.findUsers();
		model.addAttribute("users",users);
		return "index";
	}

	public void finds2(){
		System.out.println("123");
	}

	//注册
	@RequestMapping("/regUsers.do")
	public String reg(User user){
		System.out.println(user);
		boolean result = userService.regUser(user);
		if (result){
			return "index";
		}
		return "login";
	}

	//通过用户名查找
	@RequestMapping("/findUserByName.do")
	public String findUserByName(String username){
		List<User> users = userService.findUserByName(username);
		System.out.println("users:"+users);
		return "index";
	}
}
