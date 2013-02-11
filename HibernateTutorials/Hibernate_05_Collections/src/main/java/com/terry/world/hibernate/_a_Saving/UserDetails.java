package com.terry.world.hibernate._a_Saving;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

@Entity
public class UserDetails {
	@Id
	private int userId;
	private String userName;
	@ElementCollection
	@JoinTable(name="User_Address",joinColumns=@JoinColumn(name="UserId"))
	private Set<Address> addressSet;
	public UserDetails() {
		setAddressSet(new HashSet<Address>());
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Set<Address> getAddressSet() {
		return addressSet;
	}
	public void setAddressSet(Set<Address> addressSet) {
		this.addressSet = addressSet;
	}
}
