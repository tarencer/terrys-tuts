/**
 * 
 */
package com.terry.world.hibernate._c_embeddedObjectAsPK;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author tools
 *
 */
@Embeddable
public class UserId implements Serializable{
	private String emailId;
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	private String userName;
	
}
