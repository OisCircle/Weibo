package com.weibo.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.weibo.functionService.OtherUserService;
import com.weibo.model.Follow;

@Controller
@RequestMapping("/guest")
public class OtherUserController {
	ApplicationContext ctx;
	@RequestMapping(params="method=guest")
	public ModelAndView otherUser(ModelAndView mv,String username,String guest,String password){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		OtherUserService service=ctx.getBean(OtherUserService.class);
		mv=service.addMessageIntoModelAndView(mv, username,guest,password);
		if(service.isFollowed(username, guest))
			mv.addObject("isFollowed", true);
		else
			mv.addObject("isFollowed", false);
		mv.setViewName("otherUser");
		return mv;
	}
	
}
