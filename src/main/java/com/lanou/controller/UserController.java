package com.lanou.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanou.entity.User;
import com.lanou.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
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
	@ResponseBody
	public boolean findN(String username){
		List<User> users = userService.findUserByName(username);
		if (users.size()==0){
			return true;
			//用户名可以使用
		}
		//用户名已存在
		return false;
	}

	//登录
	@RequestMapping("/login.do")
	@ResponseBody
	public boolean findUserByNameAndPwd(User user,HttpServletRequest request) throws UnknownHostException {
		User user1 = userService.findUserByNameAndPwd(user);
		if (user1!=null){
			request.getSession().setAttribute("user",user);
			//获取当前时间
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String loginDate = dateFormat.format(date);
			System.out.println(loginDate);
			//获取本地ip地址
			InetAddress address = InetAddress.getLocalHost();
			String addressIp = address.getHostAddress();
			System.out.println(addressIp);
			user.setAddressIp(addressIp);
			user.setLoginDate(loginDate);
			userService.updateIpAndTime(user);
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
	@ResponseBody
	public boolean updateUserInfo(User user){
		System.out.println(user);
		boolean result = userService.updateUserInfo(user);
		if (result){
			//修改成功
			return true;
		}
			//修改失败
		return false ;
	}

	//退出登录
	@RequestMapping("/exit.do")
	@ResponseBody
	public boolean exit(HttpServletRequest request){
		HttpSession session = request.getSession();
		if (session==null){
			return false;
		}
		request.removeAttribute("user");
		//退出成功
		return true;
	}

	//修改密码
	@RequestMapping("/updatePassword.do")
	@ResponseBody
	public boolean updatePassword(HttpServletRequest request,Integer uId){
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
				return true;
				//更新成功
			}
		}
		//旧密码输入不正确
		return false;
	}
}
