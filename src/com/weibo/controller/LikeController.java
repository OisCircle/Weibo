package com.weibo.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.functionService.LikeService;
import com.weibo.model.User;
import com.weibo.model.Weibo;

@Controller
@RequestMapping("/like")
public class LikeController {
	ApplicationContext ctx;
	@RequestMapping(params="method=like")
	public ModelAndView likeViaHome(ModelAndView mv,String username,String password,String guest,int weiboId,int userId,String page){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		LikeService service=ctx.getBean(LikeService.class);
		service.likeWeibo(userId, weiboId);
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
