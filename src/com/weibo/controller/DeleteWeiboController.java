package com.weibo.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.functionService.DeleteWeiboService;

@Controller
@RequestMapping("/deleteWeibo")
public class DeleteWeiboController {
	ApplicationContext ctx;
	@RequestMapping(params="method=deleteWeibo")
	public ModelAndView deleteWeibo(ModelAndView mv,String username,String password,String guest,int weiboId,int userId){
		ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
		DeleteWeiboService service=ctx.getBean(DeleteWeiboService.class);
		service.deleteWeibo(userId, weiboId);
		mv.setViewName("redirect:/user?username="+username+"&guest="+guest+"&password="+password+"&method=user");
		return mv;
	}
}
