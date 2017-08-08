package com.weibo.controller;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.login.service.LoginService;
import com.weibo.model.User;

@Controller
@RequestMapping("/login")
public class LoginController {
	@RequestMapping(params="method=login")
	public ModelAndView login(ModelAndView mv,String username,String password){
		
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		LoginService service=ctx.getBean(LoginService.class);
		
		if(service.login(username,password)==true){
			//传User信息给页面
			User u=service.getDao().getUser(username);
			mv=service.addMessagesIntoModel(mv, u);
			mv.setViewName("home");
		}
		else{
			if(!service.getDao().getUser(username).getPassword().equals(password))
				mv.addObject("password", "password is wrong");
			else
				mv.addObject("username", "no such user");
			mv.setViewName("loginFail");
		}
		
		return mv;
	}
	
}
