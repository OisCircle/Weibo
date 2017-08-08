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
public class LikeService {
	Dao dao;
	public Dao getDao() {
		return dao;
	}
	@Resource(name="dao")
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public void likeWeibo(int userId,int weiboId){
		User u=dao.getUser(userId);
		UserLike userLike=new UserLike();
		userLike.setUserLikeId(userId);
		Weibo weibo=dao.getWeibo(weiboId);
		weibo.getUserLike().add(userLike);
		dao.updateWeibo(weibo);
	}
	public ModelAndView addMessageIntoModelAndView(ModelAndView mv,User u,String guest,String password){
		mv.addObject("user", u);
		mv.addObject("guest", guest);
		mv.addObject("password", password);
		mv.addObject("weibo", dao.getWeibo());
		return mv;
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
