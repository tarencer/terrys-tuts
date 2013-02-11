package com.terry.world.hibernate._a_FirstLevelCache;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

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
		util = new HibernateUtil<UserDetails>("/com/terry/world/hibernate/_a_FirstLevelCache/hibernate.cfg.xml");
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
	public void testFirstLevelCache_sucess() {
		createUsers(2);
		try
		{
			SessionFactory factory = util.getSessionFactory();
			Session session = factory.openSession();
			session.beginTransaction();
			
			/**
			 * Session.get fires a select statement
			 */
			testMultipleCalls(session);
			
			
			session.getTransaction().commit();
			session.close();
			
			Session session2 = factory.openSession();
			session2.beginTransaction();
			
			/**
			 * Session2.get fires another select statement
			 */
			session2.get(UserDetails.class, 1);
			
			session2.getTransaction().commit();
			session2.close();
			
		}catch(Exception e)
		{
			fail(e.getMessage());
		}
	}

	private void testMultipleCalls(Session session) {
		UserDetails user = (UserDetails) session.get(UserDetails.class, 1);
		
		UserDetails user2 = (UserDetails) session.get(UserDetails.class, 1);
		
		assertEquals(user,user2);
	}
}
