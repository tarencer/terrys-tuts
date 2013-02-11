package com.terry.world.hibernate._b_PersistingDetached;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;

import com.terry.world.hibernate.main.HibernateUtil;


public class TestUserDetails {
	UserDetails user;
	HibernateUtil<UserDetails> util;
	SessionFactory sessionFactory;
	@Before
	public void setUp() throws Exception {
		util = new HibernateUtil<UserDetails>("/com/terry/world/hibernate/_b_PersistingDetached/hibernate.xml");
		sessionFactory = util.getSessionFactory();
	}

	@Test
	public void testSave_sucess() {
		//transient object
		user = new UserDetails();
		user.setUserName("Transient state");
		
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		UserDetails userFromDb = getUserFromDb(session);
		assertNull(userFromDb);
		
		session.save(user);
		/**
		 *  NOTE: once save/update method is called transient object becomes persistent object,
		 *   after which save or update is not necessary on object since hibernate keeps track of object changes. 
		 */
		userFromDb = getUserFromDb(session);
		assertNotNull(userFromDb);
		
		user.setUserName("Persistent State");
		
		session.getTransaction().commit();
		session.close();
		
		userFromDb = util.getObject(UserDetails.class,user.getUserId());
		assertNotNull(userFromDb);
		assertTrue("Persistent State".equalsIgnoreCase(userFromDb.getUserName()));
		
		/**
		 *  NOTE: once session.close is called the object is not tracke by hibernate and becomes a detached object
		 *  to check this, will update the value of user object and check the value retrieved from db. 
		 */
		//detached object
		user.setUserName("Detached State");
		userFromDb = util.getObject(UserDetails.class,user.getUserId());
		assertNotNull(userFromDb);
		assertFalse("Detached State".equalsIgnoreCase(userFromDb.getUserName()));
		
		/**
		 * Persisting detached object
		 */
		session = sessionFactory.openSession();
		session.beginTransaction();
		
		session.update(user);
		
		user.setUserName("Persisted From Detached State");
		
		userFromDb = getUserFromDb(session);
		assertNotNull(userFromDb);
		assertTrue("Persisted From Detached State".equalsIgnoreCase(userFromDb.getUserName()));
		
		session.getTransaction().commit();
		session.close();
		
	}

	private UserDetails getUserFromDb(Session session) {
		return (UserDetails)session.get(UserDetails.class,user.getUserId());
	}

}
