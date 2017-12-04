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
	public boolean reg(User user){
		System.out.println(user);
		boolean result = userService.regUser(user);
		if (result){
			return true;
		}
		return false;
	}

	//通过用户名查找
	@RequestMapping("/findUserByName.do")
	public Integer findN(String username){
		List<User> users = userService.findUserByName(username);
		if (users.size()==0){
			return 0;
			//用户名可以使用
		}
		//用户名已存在
		return 1;
	}

	//登录
	@RequestMapping("/login.do")
	public boolean findUserByNameAndPwd(User user,HttpServletRequest request){
		User user1 = userService.findUserByNameAndPwd(user);
		if (user1!=null){
			request.getSession().setAttribute("user",user);
			return true;
			//登录成功
		}
		//登录失败返回登录
		return false;
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
	@RequestMapping("/exit.do")
	public String exit(HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session==null){
			return "index";
		}
		request.removeAttribute("user");
		return "login";
	}

	@RequestMapping("/updatePassword.do")
	public String updatePassword(HttpServletRequest request,Integer uId,Model model){
		User user = userService.findUserById(uId);
		//oldPassword:输入的旧密码
		//password:本来的密码
		String password = request.getParameter("oldPassword");
		String newpassword = request.getParameter("newPassword");
		//如果输入的密码和本来的密码相同
		if (password.equals(user.getPassword()))
		{
			//调用更新的方法
			boolean result = userService.updatePassword(newpassword);
			if (result){
				return "index";
			}
		}
		return "旧密码不对";
	}
}
