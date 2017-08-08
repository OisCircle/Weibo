package com.weibo.controller;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.apache.bcel.util.ClassPath;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.functionService.FollowPageService;
import com.weibo.model.Weibo;

@Controller
@RequestMapping("/followPage")
public class FollowPageController {
	ApplicationContext ctx;
	@RequestMapping(params="method=followPage")
	public ModelAndView followPage(ModelAndView mv,String username,String guest,String password){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		FollowPageService service=ctx.getBean(FollowPageService.class);
		List<Weibo> followWeibo;
		followWeibo=service.getFollowWeibo(username);
		mv=service.addMessageIntoModelAndView(mv,username,followWeibo,guest,password);
		mv.setViewName("followPage");
		return mv;
	}
}
