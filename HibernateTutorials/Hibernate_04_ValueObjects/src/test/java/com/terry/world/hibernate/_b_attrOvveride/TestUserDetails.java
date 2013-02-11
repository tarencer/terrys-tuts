package com.terry.world.hibernate._b_attrOvveride;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.terry.world.hibernate.main.HibernateUtil;

public class TestUserDetails {
	UserDetails details;
	Address homeAddress;
	Address workAddress;
	HibernateUtil<UserDetails> starter;
	@Before
	public void setUp() throws Exception {
		homeAddress = new Address();
		workAddress = new Address();
		details = new UserDetails();
		starter = new HibernateUtil<UserDetails>("/com/terry/world/hibernate/_b_attrOvveride/hibernate.cfg.xml");
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
		homeAddress.setPincode("403189");
		
		workAddress.setStreet("work street");
		workAddress.setPincode("work code");
		workAddress.setState("work state");
		workAddress.setCity("work city");
		
		details.setUserId(1);
		details.setUserName("Tarsicius rodrigues");
		details.setHomeAddress(homeAddress);
		details.setWorkAddress(workAddress);
		
		starter.saveObject(details);
	}

	@Test
	public void testGet_sucess() {
		UserDetails user=null;
		Address homeAddressVal = null;
		Address workAddressVal = null;
		try {
			createUser();
			user = starter.getObject(UserDetails.class, details.getUserId());
			homeAddressVal = user.getHomeAddress();
			workAddressVal = user.getWorkAddress();
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(user);
		assertNotNull(homeAddressVal);
		assertNotNull(workAddressVal);
		assertEquals(details.getUserName(),user.getUserName());
		assertEquals(homeAddress.getPincode(),homeAddressVal.getPincode());
		assertEquals(workAddress.getState(),workAddressVal.getState());
	}
}
