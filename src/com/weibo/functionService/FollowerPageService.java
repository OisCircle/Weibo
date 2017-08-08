package com.weibo.functionService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.dao.Dao;
import com.weibo.model.Follower;
import com.weibo.model.User;
import com.weibo.model.Weibo;

@Component
public class FollowerPageService {
	Dao dao;
	public Dao getDao() {
		return dao;
	}
	@Resource
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public List<Follower> getFollower(String username){
		return dao.getUser(username).getFollower();
	}
	public ModelAndView addMessageIntoModelAndView(ModelAndView mv,String username,List<Follower> follower,String guest,String password){
		User u=dao.getUser(username);
		mv.addObject("user", u);
		mv.addObject("password", password);
		mv.addObject("guest", guest);
		return mv;
	}
	
}
