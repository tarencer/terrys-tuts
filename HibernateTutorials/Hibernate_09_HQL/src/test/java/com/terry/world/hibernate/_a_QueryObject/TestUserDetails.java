package com.terry.world.hibernate._a_QueryObject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.terry.world.hibernate.main.HibernateUtil;

public class TestUserDetails {
	UserDetails details;
	HibernateUtil<UserDetails> util;
	@Before
	public void setUp() throws Exception {
		util = new HibernateUtil<UserDetails>("/com/terry/world/hibernate/_a_QueryObject/hibernate.xml");
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
	public void testHQLQuery_sucess() {
		try
		{
			int numberOfUsers =10;
			createUsers(numberOfUsers);
			
			String queryString = "from UserDetails";
			List users = util.excecuteSelectQuery(queryString);
			assertEquals(numberOfUsers,users.size());
			
			queryString = "from UserDetails where userId > 5";
			users = util.excecuteSelectQuery(queryString);
			assertEquals(5,users.size());
		}catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
}
