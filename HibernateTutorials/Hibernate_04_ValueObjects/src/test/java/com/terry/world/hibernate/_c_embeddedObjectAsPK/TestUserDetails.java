package com.terry.world.hibernate._c_embeddedObjectAsPK;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.terry.world.hibernate.main.HibernateUtil;

public class TestUserDetails {
	UserDetails details;
	HibernateUtil<UserDetails> starter;
	UserId id;
	@Before
	public void setUp() throws Exception {
		id=new UserId();
		details = new UserDetails();
		starter = new HibernateUtil<UserDetails>("/com/terry/world/hibernate/_c_embeddedObjectAsPK/hibernate.cfg.xml");
	}

	@Test
	public void testSave_sucess() {
		try
		{
			id.setEmailId("name@email.com");
			id.setUserName("name");
			createUser(id);
		}catch(Exception e)
		{
			fail(e.getMessage());
		}
	}

	private void createUser(UserId id) {
		details.setUserId(id);
		details.setPassword("password");
		starter.saveObject(details);
	}

	@Test
	public void testSaveTwice_failure() {
		id.setEmailId("name@email.com");
		id.setUserName("name");
		createUser(id);
		createUser(id);
	}
}
