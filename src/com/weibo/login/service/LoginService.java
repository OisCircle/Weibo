package com.weibo.login.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.dao.Dao;
import com.weibo.model.User;
import com.weibo.model.Weibo;

@Component("loginService")
public class LoginService {
	Dao dao;
	public Dao getDao() {
		return dao;
	}
	@Resource(name="dao")
	public void setLoginDao(Dao dao) {
		this.dao = dao;
	}
	public boolean login(String username,String password){
		User u1=dao.getUser(username);
		if(u1==null){
			return false;
		}
		User u2=new User();
		u2.setUsername(username);
		u2.setPassword(password);
		//对比信息
		if(check(u1, u2)==true)
			return true;
		else
			return false;
	}
	public boolean check(User u1,User u2) {
		boolean check;
		if(u1.getUsername().equals(u2.getUsername())&&
				u1.getPassword().equals(u2.getPassword()))
			check=true;
		else check=false;
		return check;
	}
	public ModelAndView addMessagesIntoModel(ModelAndView mv,User u){
		mv.addObject("user", u);
		mv.addObject("weibo", dao.getWeibo());
		mv.addObject("guest", u.getUsername());
		mv.addObject("password", u.getPassword());
		return mv;
	}
}
