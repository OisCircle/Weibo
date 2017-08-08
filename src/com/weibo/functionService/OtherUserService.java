package com.weibo.functionService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.dao.Dao;
import com.weibo.model.Follow;
import com.weibo.model.User;

@Component
public class OtherUserService {
	private Dao dao;
	public Dao getDao() {
		return dao;
	}
	@Resource(name="dao")
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public ModelAndView addMessageIntoModelAndView(ModelAndView mv,String username,String guest,String password){
		User u=dao.getUser(username);
		User u2=dao.getUser(guest);
		mv.addObject("user", u);
		mv.addObject("guestId", u2.getId());
		mv.addObject("guest",guest);
		mv.addObject("password", password);
		return mv;
	}
	
	public boolean isFollowed(String username,String guest){
		User u=dao.getUser(guest);
		List<Follow> follow=u.getFollow();
		for(int i=0;i<follow.size();++i){
			if(follow.get(i).getUsername().equals(username))
				return true;
		}
		return false;
	}
}
