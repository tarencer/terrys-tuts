package com.terry.world.hibernate._a_Saving;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.terry.world.hibernate.main.HibernateUtil;

public class TestUserDetails {
	UserDetails details;
	Address homeAddress;
	Address officeAddress;;
	HibernateUtil<UserDetails> util;
	@Before
	public void setUp() throws Exception {
		homeAddress = new Address();
		officeAddress = new Address();
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
		homeAddress.setCity("home city");
		homeAddress.setPincode("home pin");
		
		officeAddress.setCity("office city");
		officeAddress.setPincode("office pin");
		officeAddress.setState("office state");
		officeAddress.setStreet("office street");
		
		details.setUserId(1);
		details.setUserName("Tarsicius rodrigues");
		details.getAddressSet().add(homeAddress);
		details.getAddressSet().add(officeAddress);
		
		util.saveObject(details);
	}

	@Test
	public void testGet_sucess() {
		UserDetails user=null;
		Set<Address> addressList = null;
		try {
			createUser();
			user = util.getObject(UserDetails.class, details.getUserId());
			addressList=user.getAddressSet();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(user);
		assertNotNull(addressList);
		assertEquals(user.getUserName(),details.getUserName());
		assertEquals(addressList.size(),2);
	}
}
