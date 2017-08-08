package com.weibo.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.functionService.FollowPageService;
import com.weibo.functionService.FollowService;
import com.weibo.functionService.FollowerPageService;
import com.weibo.model.Follower;

@Controller
@RequestMapping("/followerPage")
public class FollowerPageController {
	ApplicationContext ctx;
	@RequestMapping(params="method=followerPage")
	public ModelAndView followUser(ModelAndView mv,String username,String guest,String password){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		FollowerPageService service=ctx.getBean(FollowerPageService.class);
		List<Follower> follower=service.getFollower(username);
		mv=service.addMessageIntoModelAndView(mv,username,follower,guest,password);
		mv.setViewName("followerPage");
		return mv;
	}
}
