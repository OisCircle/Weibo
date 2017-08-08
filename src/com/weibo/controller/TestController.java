package com.weibo.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/test")
public class TestController {
	@RequestMapping(params="method=test")
	public  ModelAndView test(ModelAndView mv,HttpSession session){
		mv.setViewName("test");
		mv.addObject("message", "hello");
		mv.addObject("1", "world");
		Set set=new HashSet();
		set.add("hello");
		set.add("world");
		set.add("heaven");
//		session.setAttribute("hashset",set);

		mv.addObject("hashset",set);
		return mv;
	}
}
