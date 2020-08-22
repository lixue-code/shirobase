package com.shop.controller;

import java.util.List;

import javax.security.auth.login.LoginContext;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LogoutAware;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.domain.User;
import com.shop.service.impl.IUserService;

@Controller
public class UserController {

	@Autowired
	private IUserService iUserService;
	
	
	@RequestMapping(value = "/toIndex")
	public String toIndex() {
		return "index";
	}
	


	@RequestMapping(value = "/login")
	public String login(String username,String password) {
		
		System.out.println("登陆控制器:"+username+" "+password);
		Subject subject = SecurityUtils.getSubject();
	
		AuthenticationToken token = new UsernamePasswordToken(username,password);
		
		try {
			subject.login(token);
		} catch (AuthenticationException e) {
			System.out.println("账号或者密码错误");
		}
			
		if(subject.isAuthenticated()) {
			subject.getSession().setAttribute("username", subject.getPrincipal().toString());
			return "index";
		}
		return "redirect:login.jsp";
		
		

	}
	
	
	/**
	 * 注销
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String Logout() {
		Subject subject = SecurityUtils.getSubject();
		System.out.println("用户:"+subject.getPrincipal().toString());
		subject.logout();
		return "redirect:login.jsp";
	}
	
	
	@RequestMapping(value = "/getUserList")
	@ResponseBody
	public List<User> getUserList(){
		return iUserService.getUserList();
	}
}
