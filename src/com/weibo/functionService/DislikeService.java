package com.weibo.functionService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.dao.Dao;
import com.weibo.model.Follow;
import com.weibo.model.User;
import com.weibo.model.UserLike;
import com.weibo.model.UserWeibo;
import com.weibo.model.Weibo;

@Component
public class DislikeService {
	Dao dao;
	public Dao getDao() {
		return dao;
	}
	@Resource
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public ModelAndView addMessageIntoModelAndView(ModelAndView mv,User u,String guest,String password){
		mv.addObject("guest", password);
		mv.addObject("password", password);
		mv.addObject("weibo", dao.getWeibo());
		mv.addObject("user", u);
		return mv;
	}
	public void dislikeWeibo(int userId,int weiboId){
		Weibo weibo=dao.getWeibo(weiboId);
		List<UserLike> userLike=weibo.getUserLike();
		int i;
		for(i=0;i<userLike.size();++i){
			if(userId==userLike.get(i).getUserLikeId()){
				break;
			}
		}
		dao.deleteUserLike(userLike.get(i));
	}
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
