package com.terry.world.hibernate._c_embeddedObjectAsPK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class UserDetails {
	@EmbeddedId
	private UserId userId;
	private String password;
	public UserId getUserId() {
		return userId;
	}
	public void setUserId(UserId userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
