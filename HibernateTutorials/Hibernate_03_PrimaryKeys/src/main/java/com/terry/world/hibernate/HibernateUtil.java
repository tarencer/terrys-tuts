package com.terry.world.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateUtil<T> {
	private List<Object> objectsToSaveList;
	SessionFactory sessionFactory ;
	public HibernateUtil()
	{
		Configuration configuration = new Configuration().configure();
		ServiceRegistryBuilder serviceRegistryBuilder = new ServiceRegistryBuilder().applySettings(configuration
				.getProperties());
		sessionFactory = configuration
				.buildSessionFactory(serviceRegistryBuilder.buildServiceRegistry());
		objectsToSaveList = new ArrayList<Object>();
	}
	public void saveObject(T object) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(object);
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
	public List<Object> getObjectsToSaveList() {
		return objectsToSaveList;
	}
	public void setObjectsToSaveList(List<Object> objectsToSaveList) {
		this.objectsToSaveList = objectsToSaveList;
	}
}
