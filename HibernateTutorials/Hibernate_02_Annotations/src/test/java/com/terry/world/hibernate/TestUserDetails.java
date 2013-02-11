package com.terry.world.hibernate;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class TestUserDetails {
	User details;
	HibernateUtil<User> starter;
	@Before
	public void setUp() throws Exception {
		details = new User();
		starter = new HibernateUtil<User>();
	}

	@Test
	public void testSave_sucess() {
		details.setUserId(1);
		details.setUserName("Tarsicius rodrigues");
		details.setJoinedDate(new Date());
		details.setAddress("Adress");
		details.setDescription("desc");
		starter.saveObject(details);
	}
}
