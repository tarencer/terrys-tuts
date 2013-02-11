package com.terry.world.hibernate._a_Saving;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.terry.world.hibernate.main.HibernateUtil;

public class TestUserDetails {
	UserDetails details;
	Address address;
	HibernateUtil<UserDetails> util;
	@Before
	public void setUp() throws Exception {
		address = new Address();
		details = new UserDetails();
		util = new HibernateUtil<UserDetails>("/com/terry/world/hibernate/_a_Saving/hibernate.cfg.xml");
	}

	@Test
	public void testSave_sucess() {
		try
		{
			createUser();
		}catch(Exception e)
		{
			fail(e.getMessage());
		}
	}

	private void createUser() {
		address.setCity("city name");
		address.setPincode("403189");
		
		details.setUserId(1);
		details.setUserName("Tarsicius rodrigues");
		details.setAddress(address);
		
		util.saveObject(details);
	}

	@Test
	public void testGet_sucess() {
		UserDetails user=null;
		Address addressVal = null;
		try {
			createUser();
			user = util.getObject(UserDetails.class, details.getUserId());
			addressVal = user.getAddress();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(user);
		assertNotNull(addressVal);
		assertEquals(user.getUserName(),details.getUserName());
		assertEquals(address.getPincode(),addressVal.getPincode());
	}
}
