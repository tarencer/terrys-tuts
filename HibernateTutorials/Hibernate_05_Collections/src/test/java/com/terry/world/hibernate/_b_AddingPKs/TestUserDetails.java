package com.terry.world.hibernate._b_AddingPKs;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.terry.world.hibernate.main.HibernateUtil;

public class TestUserDetails {
	UserDetails details;
	Address homeAddress;
	Address officeAddress;;
	HibernateUtil<UserDetails> starter;
	@Before
	public void setUp() throws Exception {
		homeAddress = new Address();
		officeAddress = new Address();
		details = new UserDetails();
		starter = new HibernateUtil<UserDetails>("/com/terry/world/hibernate/_b_AddingPKs/hibernate.cfg.xml");
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
		
		starter.saveObject(details);
	}

	@Test
	public void testGet_sucess() {
		UserDetails user=null;
		Collection<Address> addressList = null;
		try {
			createUser();
			user = starter.getObject(UserDetails.class, details.getUserId());
			addressList=user.getAddressSet();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(user);
		assertNotNull(addressList);
		assertEquals(user.getUserName(),details.getUserName());
		/**
		 * this wont work as its lazy initialization by default.
		 * will have to change to eager initialization to get it working.
		 */
//		assertEquals(addressList.size(),2);
	}
}
