package com.terry.world.hibernate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TestUserDetails {
	UserDetails details;
	HibernateUtil<UserDetails> util;
	@Before
	public void setUp() throws Exception {
		details = new UserDetails();
		details.setUserId(1);
		details.setUserName("Tarsicius rodrigues");
		util = new HibernateUtil<UserDetails>();
	}

	@Test
	public void testCreate_sucess() {
		try
		{
			util.createObject(details);
		}catch(Exception e)
		{
			fail(e.getMessage());
		}
	}

	@Test
	public void testRead_sucess() {
		UserDetails user=null;
		try {
			user = util.readObject(UserDetails.class, details.getUserId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(user);
		assertEquals(user.getUserName(),details.getUserName());
	}
	@Test
	public void testUpdate_sucess() {
		UserDetails user=null;
		String newUserName = "new username";
		try {
			user = util.readObject(UserDetails.class, details.getUserId());
			user.setUserName(newUserName);
			util.updateObject(user);
			user=null;
			user=util.readObject(UserDetails.class, details.getUserId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(user);
		assertEquals(user.getUserName(),newUserName);
	}
	@Test
	public void testDelete_sucess() {
		UserDetails user=null;
		String newUserName = "new username";
		try {
			user = util.readObject(UserDetails.class, details.getUserId());
			util.deleteObject(user);
			user=null;
			user=util.readObject(UserDetails.class, details.getUserId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNull(user);
	}
}
