package com.terry.world.hibernate.main;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil<T> {
	private List<Object> objectsToSaveList;
	private SessionFactory sessionFactory ;
	public HibernateUtil(String fileName)
	{
		Configuration configuration = new Configuration().configure(fileName);
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration
				.getProperties());
		setSessionFactory(configuration
				.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry()));
		objectsToSaveList = new ArrayList<Object>();
	}
	public void saveObjects() {
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			if(getObjectsToSaveList()!=null)
			{
				for(Object tableObject : getObjectsToSaveList())
					session.save(tableObject);
			}
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			if(transaction!=null)
				transaction.rollback();
		}finally{
			session.close();
		}
	}
	public List<Object> getObjectsToSaveList() {
		return objectsToSaveList;
	}
	public void setObjectsToSaveList(List<Object> objectsToSaveList) {
		this.objectsToSaveList = objectsToSaveList;
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
