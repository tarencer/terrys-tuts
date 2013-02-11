package com.terry.world.hibernate._b_attrOvveride;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Address {
	@Column(name="DEFAULT_STREET_NAME")
	private String street;
	@Column(name="DEFAULT_CITY_NAME")
	private String city;
	@Column(name="DEFAULT_STATE_NAME")
	private String state;
	@Column(name="DEFAULT_PINCODE_NAME")
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
