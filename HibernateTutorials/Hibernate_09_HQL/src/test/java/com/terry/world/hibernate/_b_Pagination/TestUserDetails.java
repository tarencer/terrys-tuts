package com.terry.world.hibernate._b_Pagination;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.terry.world.hibernate.main.HibernateUtil;

public class TestUserDetails {
	UserDetails details;
	HibernateUtil<UserDetails> util;
	@Before
	public void setUp() throws Exception {
		util = new HibernateUtil<UserDetails>("/com/terry/world/hibernate/_b_Pagination/hibernate.xml");
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
			List<UserDetails> users = util.excecuteSelectQuery(queryString,5,4);
			assertEquals(4,users.size());
			assertEquals(6,users.get(0).getUserId());
			
			queryString = "select userName from UserDetails";
			List userNames = util.excecuteSelectQuery(queryString);
			assertTrue(userNames.get(0) instanceof String);
			
			queryString = "select new map(userId,userName) from UserDetails";
			List maps = util.excecuteSelectQuery(queryString);
			assertTrue(maps.get(0) instanceof Map);
		}catch(Exception e)
		{
			fail(e.getMessage());
		}
	}
}
