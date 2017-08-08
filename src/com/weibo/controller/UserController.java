package com.weibo.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.functionService.UserService;
import com.weibo.model.User;

@Controller(value="userController")
@RequestMapping("/user")
public class UserController implements ServletContextAware{
	
	ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext.xml");
	UserService service=ctx.getBean(UserService.class);
	private ServletContext servletContext;
	@Override
	public void setServletContext(ServletContext context) {
		this.servletContext  = context;
	}
	
	@RequestMapping(params="method=user")
	public ModelAndView userPage(ModelAndView mv,String username,String guest,String password){
		
		//以后增加用户信息修改功能
		User u=service.getDao().getUser(username);
		mv=service.addMessagesIntoModel(mv, u,guest,password);
		mv.setViewName("user");
		return mv;
	}
	
	@RequestMapping(params="method=userImgUpload")
	public ModelAndView userImgUpload(ModelAndView mv,String username,@RequestParam("file") CommonsMultipartFile file){
			if(!file.isEmpty()){
			
		   
		   
		   service.userImgUpload(username, file);
		   String password=service.getDao().getUser(username).getPassword();
		   
		   mv.getModel().put("username", username);
		   mv.getModel().put("password", password);
		   mv.setViewName("userImgUploadSuccess");
		   return mv;
		}
		
		
		
		
		mv.setViewName("userImgUploadFail");
		return mv;
	}
}
