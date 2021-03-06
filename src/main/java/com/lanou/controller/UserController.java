package com.lanou.controller;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lanou.service.ShoppingCarService;
import com.lanou.util.FastJson_All;
import com.lanou.util.FileUtil;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lanou.entity.User;
import com.lanou.service.UserService;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ShoppingCarService shoppingCarService;

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
	@ResponseBody
	public void findUserByNameAndPwd(User user,HttpServletRequest request,HttpServletResponse response) throws UnknownHostException {
		User user1 = userService.findUserByNameAndPwd(user);
		System.out.println(user1);
		boolean result = false;
		if (user1!=null){
			//获取当前时间
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String loginDate = dateFormat.format(date);
			System.out.println(loginDate);
			//获取本地ip地址
			InetAddress address = InetAddress.getLocalHost();
			String addressIp = address.getHostAddress();
			System.out.println(addressIp);
			user1.setAddressIp(addressIp);
			user1.setLoginDate(loginDate);
			userService.updateIpAndTime(user1);
			result = true;
			request.getSession().setAttribute("user1",user1);
			shoppingCarService.prepareShoppingCar(request);
			//登录成功
		}
		//登录失败返回登录
		FastJson_All.toJson(result,response);
	}

	//通过uId查找用户信息
	@RequestMapping("/findUserById.do")
	@ResponseBody
	public User findUserById(Integer uId,HttpServletResponse response){
		User user =userService.findUserById(uId);
		System.out.println(user);
		return user;
	}

	//修改用户信息(密码和手机号除外)
	@RequestMapping("/updateUserInfo.do")
//	@ResponseBody
	public void updateUserInfo(User user,HttpServletResponse response,HttpServletRequest request){
		System.out.println(user);
		boolean result = userService.updateUserInfo(user);
		boolean res = false;
		if (result){
			//修改成功
			User user1 = userService.findUserById(user.getuId());
			request.getSession().setAttribute("user1",user1);
			res = true;
		}
			//修改失败
		FastJson_All.toJson(res,response);
	}

	//修改用户名
	@RequestMapping("/updateUserName")
	public void updateUserName(User user,HttpServletResponse response,HttpServletRequest request){
		boolean result = false;
		System.out.println(user);
		boolean results = userService.updateUserName(user);
		if (results){
			User user1 = userService.findUserById(user.getuId());
			request.getSession().setAttribute("user1",user1);
			result = true;
		}
		FastJson_All.toJson(result,response);
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
		request.getSession().removeAttribute("user1");
		//退出成功
		FastJson_All.toJson(result,response);
	}

	//修改密码
	@RequestMapping("/updatePassword.do")
//	@ResponseBody
	public void updatePassword(HttpServletRequest request,HttpServletResponse response,User user){
		User user1 = (User)request.getSession().getAttribute("user1");
		System.out.println("user1是:"+user1);
		System.out.println("user是:"+user);
		System.out.println(user.getOldPassword());
		boolean res = false;
		//oldPassword:输入的旧密码
		//如果输入的密码和本来的密码相同
		if (user.getOldPassword().equals(user1.getPassword()))
		{
			//调用更新的方法
			user1.setPassword(user.getPassword());
			boolean result = userService.updatePassword(user1);
			if (result){
				res = true;
				//更新成功
			}
		}
		//旧密码输入不正确
		FastJson_All.toJson(res,response);
	}

	//返回Session
	@RequestMapping("/getSession.do")
	@ResponseBody
	public void Getsession(HttpServletRequest request,HttpServletResponse response){
		FastJson_All.toJson(request.getSession().getAttribute("user1"),response);
//		User user = (User) request.getSession().getAttribute("user1");
//		return user;
	}

	//上传头像
	@RequestMapping("/upload.do")
	public void updateHead(MultipartFile files,HttpServletRequest request,HttpServletResponse response){
		System.out.println(files);
		User user = (User) request.getSession().getAttribute("user1");
		boolean results = false;
		//user+userid
			//xiangce
				//xianggcetupian
		String headImg = "/usr/local/tomcat7/webapps/Seven_Two/resource/headUrl/user"+user.getuId()+"/"+user.getuId()+".jpg";
		FileUtil.createFiles(headImg);
		File files1 = new File(headImg);
		try {
		 	FileUtils.copyInputStreamToFile(files.getInputStream(),files1);
		 	String headImgUrl = "http://139.199.11.183:8080/Seven_Two/resource/headUrl/user"+user.getuId()+"/"+user.getuId()+".jpg";
			user.setHeadImgUrl(headImgUrl);
			boolean result = userService.updateHeadImgUrl(user);
			if (result){
				results = true;
				User user1 = userService.findUserById(user.getuId());
				request.getSession().setAttribute("user1",user1);
			}
			FastJson_All.toJson(results,response);
 		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//***********************************************************************
	//后台用户管理系统
	//只展示一部分东西
	@ResponseBody
	@RequestMapping("/findAllUsers.do")
	public List<User> findAllUsers(HttpServletResponse response){
		List<User> userList = userService.findAllUsers();
		return userList;
	}
	//通过uId查找信息
	//修改用户的信息

	//删除用户(需要uId,返回true成功，false失败)
	@RequestMapping("/deleteUser.do")
	public void deleteUser(HttpServletResponse response,Integer uId){
		boolean results = false;
		boolean result = userService.deleteUser(uId);
		if (result){
			results = true;
		}
		FastJson_All.toJson(results,response);
	}
}

