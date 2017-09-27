package com.oa.authority.web;

import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oa.staff.entity.UserInfornation;
import com.oa.utils.AJAXResultMessage;

@Controller
public class AuthorityContrller {

	@RequestMapping("/loginAction")
	public @ResponseBody AJAXResultMessage login(HttpSession session, UserInfornation staff) {
		if(staff.getUserName() != null){
			
			try {
				//1.得到Subject
				Subject subject = SecurityUtils.getSubject();
				//2.调用登录方法
				UsernamePasswordToken token = new UsernamePasswordToken(staff.getUserName(), staff.getPassword());
				subject.login(token);//当这一代码执行时，就会自动跳入到AuthRealm中认证方法
				
				//3.登录成功时，就从Shiro中取出用户的登录信息
				UserInfornation user = (UserInfornation) subject.getPrincipal();
				
				//4.将用户放入session域中
				session.setAttribute("userName", user.getUserName());
				session.setAttribute("userId", user.getUserId());
	            return new AJAXResultMessage(true,"登录成功!");
	            
			} catch (Exception e) {
	        	return new AJAXResultMessage(false,"帐号或者密码有误!请重新登录!");
			}
		}
    	return new AJAXResultMessage(false,"用户名不能为空!请重新登录!");
	}
	
	@RequestMapping("/logoutAction")
	public @ResponseBody AJAXResultMessage logout(HttpSession session, String userName) {
		if(userName != null && !"".equals(userName.trim())) {
			session.removeAttribute("userName");
			session.removeAttribute("userId");
			return new AJAXResultMessage(true,"注销成功!");
		}
    	return new AJAXResultMessage(false,"用户名或Id为空!");
	}
	
}
