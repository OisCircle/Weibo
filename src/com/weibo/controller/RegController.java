package com.weibo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.model.User;
import com.weibo.registration.service.RegService;

@Controller
@RequestMapping("/reg")
public class RegController {
	@RequestMapping(params="method=reg")
	public String reg(String password,String password2,String username,String gender,int age){
		//不能直接在这里new service，要从context取
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		RegService service=ctx.getBean(RegService.class);
		
		System.out.println("username:"+username);
		System.out.println("password:"+password);
		System.out.println("gender:"+gender);
		System.out.println("age:"+age);
		//检查username是否重叠
		
		if(service.getDao().getUser(username)!=null)
			return "regFail";
		
		//数据库添加User
		User u=new User();
		u.setAge(age);
		u.setGender(gender);
		u.setPassword(password);
		u.setUsername(username);
		//weibo默认是0，不用set
		service.addUser(u);
		
		
		return "regSuccess";
	}
	
}
