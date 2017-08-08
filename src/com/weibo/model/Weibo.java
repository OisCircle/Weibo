package com.weibo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Weibo {
	private int id;
	private String username;
	private String publishedTime;
	private String message;
	private List<UserLike> userLike=new ArrayList<UserLike>();
	private UserWeibo userWeibo;
	private List<Comment> comment=new ArrayList<Comment>();
	@OneToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="weiboId")
	public List<Comment> getComment() {
		return comment;
	}
	public void setComment(List<Comment> comment) {
		this.comment = comment;
	}
	@OneToOne(cascade=CascadeType.ALL)
	public UserWeibo getUserWeibo() {
		return userWeibo;
	}
	public void setUserWeibo(UserWeibo userWeibo) {
		this.userWeibo = userWeibo;
	}
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="weiboId")
	public List<UserLike> getUserLike() {
		return userLike;
	}
	public void setUserLike(List<UserLike> userLike) {
		this.userLike = userLike;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPublishedTime() {
		return publishedTime;
	}
	public void setPublishedTime(String publishedTime) {
		this.publishedTime = publishedTime;
	}
}
