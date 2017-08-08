package com.weibo.functionService;

import java.util.Date;
import java.util.HashSet;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.dao.Dao;
import com.weibo.model.User;
import com.weibo.model.UserWeibo;

@Component(value="publishService")
public class PublishService {
	private Dao dao;
	public Dao getDao() {
		return dao;
	}
	@Resource(name="dao")
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	public void publish(String message,int userId){
		dao.publishWeibo(userId, message);
		
	}
}
