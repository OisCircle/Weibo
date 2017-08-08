package com.weibo.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.functionService.DislikeService;
import com.weibo.functionService.LikeService;
import com.weibo.model.User;
import com.weibo.model.Weibo;

@Controller
@RequestMapping("/dislike")
public class DislikeController {
	ApplicationContext ctx;
	@RequestMapping(params="method=dislike")
	public ModelAndView dislike(ModelAndView mv,String username,String password,String guest,String page,int weiboId,int userId){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		DislikeService service=ctx.getBean(DislikeService.class);
		service.dislikeWeibo(userId, weiboId);
		if(page.equals("followPage")){
			mv.setViewName("redirect:/followPage?username="+username+"&guest="+guest+"&password="+password+"&method=followPage");
		}
		else if(page.equals("otherUser")){
			mv.setViewName("redirect:/guest?username="+username+"&guest="+guest+"&password="+password+"&method=guest");
		}
		else if(page.equals("home")){
			mv.setViewName("redirect:/login?username="+guest+"&password="+password+"&method=login");
		}
		else{
			mv.setViewName("redirect:/user?username="+username+"&guest="+guest+"&password="+password+"&method=user");
		}
		return mv;
	}
}
