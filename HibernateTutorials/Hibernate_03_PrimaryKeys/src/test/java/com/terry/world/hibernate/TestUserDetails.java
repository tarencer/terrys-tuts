package com.terry.world.hibernate;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class TestUserDetails {
	UserDetails details;
	HibernateUtil<UserDetails> util;
	@Before
	public void setUp() throws Exception {
		details = new UserDetails();
		util = new HibernateUtil<UserDetails>();
	}

	@Test
	public void testSave_sucess() {
		try
		{
			details.setUserName("First User");
			util.saveObject(details);
			
			details.setUserName("Second User");
			util.saveObject(details);
		}catch(Exception e)
		{
			fail(e.getMessage());
		}
	}

}
