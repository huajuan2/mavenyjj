package com.lanou.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanou.entity.User;
import com.lanou.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
	public List<User> findN(String username){
		List<User> users = userService.findUserByName(username);
		if (users.size()==0){
		}
		return users;
	}

	//登录
	@RequestMapping("/login.do")
	public String findUserByNameAndPwd(User user,HttpServletRequest request){
		User user1 = userService.findUserByNameAndPwd(user);
		if (user1!=null){
			request.getSession().setAttribute("user",user);
			return "index";
		}
		return "login";
	}

	//通过uId查找用户信息
	@RequestMapping("/findUserById.do")
	@ResponseBody
	public User findUserById(Integer uId){
		User user =userService.findUserById(uId);
		System.out.println(user);
		return user;
	}

	//修改用户信息(密码和手机号除外)
	@RequestMapping("/updateUserInfo.do")
	public String updateUserInfo(User user){
		System.out.println(user);
		boolean result = userService.updateUserInfo(user);
		if (result){
			return "index";
		}
		return "xxx";
	}

	//退出登录
	@RequestMapping("/exit,do")
	public String exit(HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session==null){
			return "index";
		}
		request.removeAttribute("user");
		return "login";
	}
}
