package com.lanou.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lanou.util.FastJson_All;
import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanou.entity.User;
import com.lanou.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
//	@ResponseBody
	public void reg(HttpServletResponse response, User user){
		System.out.println(user);
		boolean result = userService.regUser(user);
		FastJson_All.toJson(result,response);
	}

	//通过用户名查找
	@RequestMapping("/findUserByName.do")
//	@ResponseBody
	public void findN(HttpServletResponse response,String username){
		boolean result = false;
		List<User> users = userService.findUserByName(username);
		if (users.size()==0){
			result=true;
		}
		FastJson_All.toJson(result,response);
	}

	//登录
	@RequestMapping("/login.do")
//	@ResponseBody
	public void findUserByNameAndPwd(User user,HttpServletRequest request,HttpServletResponse response) throws UnknownHostException {
		User user1 = userService.findUserByNameAndPwd(user);
		boolean result = false;
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
			result = true;
			//登录成功
		}
		//登录失败返回登录
		FastJson_All.toJson(result,response);
	}

	//通过uId查找用户信息
	@RequestMapping("/findUserById.do")
//	@ResponseBody
	public void findUserById(Integer uId,HttpServletResponse response){
		User user =userService.findUserById(uId);
		System.out.println(user);
		FastJson_All.toJson(user,response);
	}

	//修改用户信息(密码和手机号除外)
	@RequestMapping("/updateUserInfo.do")
//	@ResponseBody
	public void updateUserInfo(User user,HttpServletResponse response){
		System.out.println(user);
		boolean result = userService.updateUserInfo(user);
		boolean res = false;
		if (result){
			//修改成功
			res = true;
		}
			//修改失败
		FastJson_All.toJson(res,response);
	}

	//退出登录
	@RequestMapping("/exit.do")
//	@ResponseBody
	public void exit(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();
		boolean result = true;
		if (session==null){
			result = false;
		}
		request.removeAttribute("user");
		//退出成功
		FastJson_All.toJson(result,response);
	}

	//修改密码
	@RequestMapping("/updatePassword.do")
//	@ResponseBody
	public void updatePassword(HttpServletRequest request,Integer uId,HttpServletResponse response){
		User user = userService.findUserById(uId);
		boolean res = false;
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
				res = true;
				//更新成功
			}
		}
		//旧密码输入不正确
		FastJson_All.toJson(res,response);
	}

	//返回Session
	@RequestMapping("/getSession")
	public void Getsession(HttpServletRequest request,HttpServletResponse response){
		User user = new User(1,"wcl");
		request.getSession().setAttribute("user",user);
		FastJson_All.toJson(request.getSession().getAttribute("user"),response);
	}
}

