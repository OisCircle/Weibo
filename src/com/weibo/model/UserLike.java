package com.weibo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class UserLike {
	private int id;
	private int userLikeId;
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserLikeId() {
		return userLikeId;
	}
	public void setUserLikeId(int userLikeId) {
		this.userLikeId = userLikeId;
	}
}
