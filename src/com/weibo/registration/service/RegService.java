package com.weibo.registration.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.weibo.dao.Dao;
import com.weibo.model.User;

@Component
public class RegService {
	Dao dao;
	public Dao getDao() {
		return dao;
	}
	@Resource(name="dao")
	public void setRegDao(Dao dao) {
		this.dao = dao;
	}
	
	public void addUser(User u){
		dao.addUser(u);
	}
}
