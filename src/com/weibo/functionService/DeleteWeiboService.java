package com.weibo.functionService;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.weibo.dao.Dao;
import com.weibo.model.User;
import com.weibo.model.Weibo;

@Component
public class DeleteWeiboService {
	Dao dao;
	public Dao getDao() {
		return dao;
	}
	@Resource
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public void deleteWeibo(int userId,int weiboId){
		dao.deleteWeibo(userId, weiboId);
	}
	
}
