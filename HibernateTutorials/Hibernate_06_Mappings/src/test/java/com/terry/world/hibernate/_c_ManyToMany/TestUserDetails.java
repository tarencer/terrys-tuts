package com.terry.world.hibernate._c_ManyToMany;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.terry.world.hibernate.main.HibernateUtil;

public class TestUserDetails {
	UserDetails user;
	UserDetails user2;
	HibernateUtil<UserDetails> util;
	Vehicle vehicleCar;
	Vehicle vehicleBike;
	@Before
	public void setUp() throws Exception {
		vehicleCar = new Vehicle();
		vehicleBike = new Vehicle();
		user = new UserDetails();
		user2 = new UserDetails();
		util = new HibernateUtil<UserDetails>("/com/terry/world/hibernate/_c_ManyToMany/hibernate.xml");
		util.getObjectsToSaveList().add(vehicleCar);
		util.getObjectsToSaveList().add(vehicleBike);
		util.getObjectsToSaveList().add(user);
		util.getObjectsToSaveList().add(user2);
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
		vehicleCar.setVehicleName("Car");
		vehicleCar.getUsers().add(user);
		vehicleCar.getUsers().add(user2);
		vehicleBike.setVehicleName("Bike");
		vehicleBike.getUsers().add(user);
		
		user.setUserName("Tarsicius rodrigues");
		user.getVehicles().add(vehicleCar);
		user.getVehicles().add(vehicleBike);
		
		user2.setUserName("Anafay");
		user2.getVehicles().add(vehicleCar);
		util.saveObjects();
	}

	@Test
	public void testGet_sucess() {
		UserDetails retrievedUser=null;
		try {
			createUser();
			retrievedUser = util.getObject(UserDetails.class, user.getUserId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(retrievedUser);
		assertEquals(user.getUserName(),retrievedUser.getUserName());
	}
}
