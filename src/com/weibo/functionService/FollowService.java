package com.weibo.functionService;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.dao.Dao;
import com.weibo.model.Follow;
import com.weibo.model.Follower;
import com.weibo.model.User;

@Component
public class FollowService {
	Dao dao;

	public Dao getDao() {
		return dao;
	}
	@Resource
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public void followUser(String username,String guest){
		User u1=dao.getUser(guest);
		User u2=dao.getUser(username);
		Follow follow=new Follow();
		follow.setUsername(username);
		Follower follower=new Follower();
		follower.setUsername(guest);
		u1.setFollowNum(u1.getFollowNum()+1);
		u2.setFollowerNum(u2.getFollowerNum()+1);
		u1.getFollow().add(follow);
		u2.getFollower().add(follower);
		dao.updateUser(u1);
		dao.updateUser(u2);
	}
	public ModelAndView addMessageIntoModelAndView(ModelAndView mv,String username,String guest,String password){
		User u=dao.getUser(username);
		User u2=dao.getUser(guest);
		mv.addObject("user", u);
		mv.addObject("guestId", (Integer)u2.getId());
		mv.addObject("guest",guest);
		mv.addObject("password", password);
		return mv;
	}
}
