package com.weibo.functionService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.dao.Dao;
import com.weibo.model.Follow;
import com.weibo.model.User;
import com.weibo.model.Weibo;

@Component
public class FollowPageService {
	Dao dao;
	public Dao getDao() {
		return dao;
	}
	@Resource
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	//取出关注的人的所有微博，按照时间顺序
	public List<Weibo> getFollowWeibo(String username){
		List<Weibo> weibo=dao.getWeibo();
		List<Weibo> followWeibo=new ArrayList<Weibo>();
		List<Follow> follow=dao.getUser(username).getFollow();
		for(int i=weibo.size()-1;i>=0;--i){
			for(int j=0;j<follow.size();++j){
				if(follow.get(j).getUsername().equals(weibo.get(i).getUsername())){
					followWeibo.add(weibo.get(i));
					break;
				}
			}
		}
		return followWeibo;
	}
	public ModelAndView addMessageIntoModelAndView(ModelAndView mv,String username,List<Weibo> followWeibo,String guest,String password){
		User u=dao.getUser(username);
		mv.addObject("user", u);
		mv.addObject("followWeibo",followWeibo);
		mv.addObject("password", password);
		mv.addObject("guest", guest);
		return mv;
	}
}
