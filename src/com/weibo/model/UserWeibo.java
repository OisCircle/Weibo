package com.weibo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
@Entity
public class UserWeibo {
	private int id;
	private String message;
	private String messagePublishedTime;
//	private List<UserLike> userLike=new ArrayList<UserLike>();
	private Weibo weibo;
	@OneToOne(cascade=CascadeType.ALL,mappedBy="userWeibo")
	public Weibo getWeibo() {
		return weibo;
	}
	public void setWeibo(Weibo weibo) {
		this.weibo = weibo;
	}
//	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
//	public List<UserLike> getUserLike() {
//		return userLike;
//	}
//	public void setUserLike(List<UserLike> userLike) {
//		this.userLike = userLike;
//	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getMessagePublishedTime() {
		return messagePublishedTime;
	}
	public void setMessagePublishedTime(String messagePublishedTime) {
		this.messagePublishedTime = messagePublishedTime;
	}
//	@Override
//	public String toString(){
//		return this.message;
//	}
}
