package com.weibo.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.functionService.PublishService;
import com.weibo.model.User;

@Controller
@RequestMapping("/publish")
public class PublishController {
	@RequestMapping(params="method=publish")
	public ModelAndView publish(ModelAndView mv,String message,int userId,String guest,String password){
		ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		PublishService service=ctx.getBean(PublishService.class);
		//说说存入数据库
		service.publish(message, userId);
		User u=service.getDao().getUser(userId);
		mv.setViewName("redirect:/login?username="+u.getUsername()+"&password="+password+"&method=login");
		return mv;
	}
}
