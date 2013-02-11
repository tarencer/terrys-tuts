package com.terry.world.hibernate._c_ParameterBinding;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.terry.world.hibernate.main.HibernateUtil;

public class TestUserDetails {
	UserDetails details;
	HibernateUtil<UserDetails> util;
	@Before
	public void setUp() throws Exception {
		util = new HibernateUtil<UserDetails>("/com/terry/world/hibernate/_c_ParameterBinding/hibernate.xml");
	}

	@Test
	public void testSave_sucess() {
		try
		{
			createUsers(10);
		}catch(Exception e)
		{
			fail(e.getMessage());
		}
	}

	private void createUsers(int numberOfUsers) {

		for(int i=1;i<=numberOfUsers;i++)
		{
			details = new UserDetails();
			details.setUserName("User "+i);
			util.getObjectsToSaveList().add(details);
		}
		util.saveObjects();
	}

	@Test
	public void testSQLInjection_sucess() {
		createUsers(10);
		List users = null;
		try
		{
			
			users = util.excecuteWronglyBuiltSelectQuery();
			
			// the expected result is 5 but all the records from the DB are fetched.
			assertFalse(users.size()==5);
			assertTrue(users.size()==10);
			
		}catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testParameterBinding_sucess() {
		createUsers(10);
		List users = null;
		try
		{
			users = util.excecuteSelectQueryParameters();
			assertTrue(users.size()==1);
			
		}catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	@Test
	public void testNamedPositionalParameters_sucess() {
		createUsers(10);
		List users = null;
		try
		{
			users = util.excecuteSelectQueryNamedPositionalParameter();
			assertTrue(users.size()==1);
			
		}catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
	
}
