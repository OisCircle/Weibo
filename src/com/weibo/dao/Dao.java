package com.weibo.dao;

import java.util.List;

import com.weibo.model.Comment;
import com.weibo.model.Follow;
import com.weibo.model.Follower;
import com.weibo.model.User;
import com.weibo.model.UserLike;
import com.weibo.model.Weibo;

public interface Dao {

	public User getUser(String username);
	public User getUser(int userId);

	public void addUser(User u);
	
	public void publishWeibo(int userId,String message);
	
	public List<Weibo> getWeibo();
	
	public Weibo getWeibo(int weiboId);
	
	public void updateUser(User u);
	
	public void updateWeibo(Weibo weibo);
	
	public void saveWeibo(Weibo weibo);
	
	public void deleteUserLike(UserLike userLike);
	public void addComment(Comment comment);
	public void deleteWeibo(int userId,int weiboId);
}
