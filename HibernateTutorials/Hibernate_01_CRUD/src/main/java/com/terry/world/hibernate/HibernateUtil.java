package com.terry.world.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil<T> {
	SessionFactory sessionFactory ;
	public HibernateUtil()
	{
		Configuration configuration = new Configuration().configure();
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration
				.getProperties());
		sessionFactory = configuration
				.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
	}
	public void createObject(T object) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(object);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			if(transaction!=null)
				transaction.rollback();
		}finally{
			session.close();
		}
	}
	public T readObject(Class object,int id) {
		Session session = sessionFactory.openSession();
		T retVal = null;
		try {
			retVal = (T)session.get(object,id);
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}finally{
			session.close();
		}
		return retVal;
	}
	public void updateObject(T object) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		T retVal = null;
		try {
			transaction = session.beginTransaction();
			session.update(object);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			if(transaction!=null)
				transaction.rollback();
		}finally{
			session.close();
		}
	}
	public void deleteObject(T object) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		T retVal = null;
		try {
			transaction = session.beginTransaction();
			session.delete(object);
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			if(transaction!=null)
				transaction.rollback();
		}finally{
			session.close();
		}
	}
}
