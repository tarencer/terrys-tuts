package com.terry.world.hibernate._a_OneToOne;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.terry.world.hibernate.main.HibernateUtil;

public class TestUserDetails {
	UserDetails details;
	HibernateUtil<UserDetails> util;
	Vehicle vehicle;
	@Before
	public void setUp() throws Exception {
		vehicle = new Vehicle();
		details = new UserDetails();
		util = new HibernateUtil<UserDetails>("/com/terry/world/hibernate/_a_OneToOne/hibernate.xml");
		util.getObjectsToSaveList().add(vehicle);
		util.getObjectsToSaveList().add(details);
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
		vehicle.setVehicleName("Vehicle one");
		
		details.setUserName("Tarsicius rodrigues");
		details.setVehicle(vehicle);
		util.saveObjects();
	}

	@Test
	public void testGet_sucess() {
		UserDetails user=null;
		try {
			createUser();
			user = util.getObject(UserDetails.class, details.getUserId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(user);
		assertEquals(user.getUserName(),details.getUserName());
	}
}
