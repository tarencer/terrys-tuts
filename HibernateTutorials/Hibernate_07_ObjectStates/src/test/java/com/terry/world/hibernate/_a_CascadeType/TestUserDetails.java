package com.terry.world.hibernate._a_CascadeType;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.terry.world.hibernate.main.HibernateUtil;


public class TestUserDetails {
	UserDetails user;
	HibernateUtil util;
	Vehicle vehicle1;
	Vehicle vehicle2;
	@Before
	public void setUp() throws Exception {
		vehicle1 = new Vehicle();
		vehicle2 = new Vehicle();
		user = new UserDetails();
		util = new HibernateUtil("/com/terry/world/hibernate/_a_CascadeType/hibernate.xml");
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
		vehicle1.setVehicleName("Vehicle one");
		vehicle1.setUser(user);
		vehicle2.setVehicleName("Vehicle Two");
		
		user.setUserName("Tarsicius rodrigues");
		user.getVehicles().add(vehicle1);
		user.getVehicles().add(vehicle2);
		/**
		 * NOTE : only user is being persisted and not vehicle1 and vehicle2
		 */
		util.persistObject(user);
	}

	@Test
	public void testNotFound_sucess() {
		Vehicle tempVehicle=null;
		try {
			createUser();
			tempVehicle = (Vehicle) util.getObject(Vehicle.class, vehicle2.getVehicleId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(tempVehicle);
		assertNull(vehicle2.getUser());
	}
}
