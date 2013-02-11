package com.terry.world.hibernate.main;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil<T> {
	SessionFactory sessionFactory ;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public HibernateUtil(String fileName)
	{
		Configuration configuration = new Configuration().configure(fileName);
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration
				.getProperties());
		sessionFactory = configuration
				.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
	}
	public void persistObject(T object) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.persist(object);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			if(transaction!=null)
				transaction.rollback();
		}finally{
			session.close();
		}
	}
	public T getObject(Class object,int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		T retVal = null;
		try {
			transaction = session.beginTransaction();
			retVal = (T)session.get(object,id);
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}finally{
			session.close();
		}
		return retVal;
	}
}
