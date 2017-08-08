package com.weibo.controller;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.functionService.CommentService;
import com.weibo.model.User;
import com.weibo.model.Weibo;

@Controller
@RequestMapping("/comment")
public class CommentController {
	ApplicationContext ctx;
	@RequestMapping(params="method=comment")
	public ModelAndView comment(ModelAndView mv,String username,String guest,String password,String comment,int weiboId,int userId,String page){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		CommentService service=ctx.getBean(CommentService.class);
		
		service.addComment(weiboId,guest,comment);
		
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
