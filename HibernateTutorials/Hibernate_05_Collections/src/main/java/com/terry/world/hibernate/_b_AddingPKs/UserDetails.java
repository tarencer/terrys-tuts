package com.terry.world.hibernate._b_AddingPKs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
public class UserDetails {
	@Id
	private int userId;
	private String userName;
	@ElementCollection
	@JoinTable(name="User_Address",joinColumns=@JoinColumn(name="UserId"))
	@GenericGenerator(name="hilo-gen",strategy="hilo")
	@CollectionId(columns=@Column(name="AddressId"),generator="hilo-gen",type=@Type(type="long"))
	private Collection<Address> addressSet;
	public UserDetails() {
		setAddressSet(new ArrayList<Address>());
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
	public Collection<Address> getAddressSet() {
		return addressSet;
	}
	public void setAddressSet(Collection<Address> addressSet) {
		this.addressSet = addressSet;
	}
}
