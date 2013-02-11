package com.terry.world.hibernate._b_TablePerClassStrategy;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.terry.world.hibernate.main.HibernateUtil;


public class TestUserDetails {
	HibernateUtil util;
	Vehicle vehicle;
	TwoWheeler twoWheeler;
	FourWheeler fourWheeler;
	@Before
	public void setUp() throws Exception {
		twoWheeler = new TwoWheeler();
		fourWheeler = new FourWheeler();
		vehicle = new Vehicle();
		util = new HibernateUtil("/com/terry/world/hibernate/_b_TablePerClassStrategy/hibernate.xml");
		util.getObjectsToSaveList().add(vehicle);
		util.getObjectsToSaveList().add(twoWheeler);
		util.getObjectsToSaveList().add(fourWheeler);
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
		vehicle.setVehicleName("Vehicle");
		
		fourWheeler.setVehicleName("Car");
		fourWheeler.setSteeringWheel("steeringWheel");
		
		twoWheeler.setVehicleName("Bike");
		twoWheeler.setHandle("handle");
		
		
		util.saveObjects();
	}

	@Test
	public void testGet_sucess() {/*
		Vehicle tempVehicle=null;
		Vehicle tempFourWheeler=null;
		Vehicle tempTwoWheeler=null;
		try {
			createUser();
			tempVehicle = (Vehicle) util.getObject(Vehicle.class, vehicle2.getVehicleId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
		assertNotNull(tempVehicle);
		assertNull(vehicle2.getUser());
	*/}
}
