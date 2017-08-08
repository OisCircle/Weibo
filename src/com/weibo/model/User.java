package com.weibo.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class User {
	private int id;
	private String username;
	private String password;
	private int age;
	private String gender;
	private int weibo;
	private int followNum;
	private int followerNum;
	private List<UserWeibo> userWeibo=new ArrayList<UserWeibo>();
	private List<Follow> follow=new ArrayList<Follow>();
	private List<Follower> follower=new ArrayList<Follower>();
	public int getFollowNum() {
		return followNum;
	}
	public void setFollowNum(int followNum) {
		this.followNum = followNum;
	}
	public int getFollowerNum() {
		return followerNum;
	}
	public void setFollowerNum(int followerNum) {
		this.followerNum = followerNum;
	}
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="userId")
	public List<Follow> getFollow() {
		return follow;
	}
	public void setFollow(List<Follow> follow) {
		this.follow = follow;
	}
	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.MERGE)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="userId")
	public List<Follower> getFollower() {
		return follower;
	}
	public void setFollower(List<Follower> follower) {
		this.follower = follower;
	}
	//cascadeType关联很重要，不然会出错
	@OneToMany(fetch=FetchType.EAGER)
	@Fetch(FetchMode.SUBSELECT)
	@JoinColumn(name="userId")
	public List<UserWeibo> getUserWeibo() {
		return userWeibo;
	}
	public void setUserWeibo(List<UserWeibo> userWeibo) {
		this.userWeibo = userWeibo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getWeibo() {
		return weibo;
	}
	public void setWeibo(int weibo) {
		this.weibo = weibo;
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
}