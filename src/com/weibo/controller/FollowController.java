package com.weibo.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.functionService.FollowService;

@Controller(value="followController")
@RequestMapping("/follow")
public class FollowController {
	ApplicationContext ctx;
	@RequestMapping(params="method=follow")
	public ModelAndView followUser(ModelAndView mv,String username,String guest,String password){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		FollowService service=ctx.getBean(FollowService.class);
		service.followUser(username, guest);
//		mv=service.addMessageIntoModelAndView(mv, username, guest,password);
		
		
		
		
		
		mv.setViewName("redirect:/guest?username="+username+"&guest="+guest+"&password="+password+"&method=guest");
		return mv;
	}
}
