package com.terry.world.hibernate._b_AddingPKs;

import javax.persistence.Embeddable;

/**
 * Value object : is an object which does not have a meaning
 * on its own. It only provides information to another entity.
 * eg. Address
 * 
 * Entity : is an object which has a meaning on its own.
 * eg. UserDetails
 * 
 * NOTE : only value objects should be embedded in an entity.
 * 
 * @author tools
 *
 */
@Embeddable
public class Address {
	private String street;
	private String city;
	private String state;
	private String pincode;
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
}
