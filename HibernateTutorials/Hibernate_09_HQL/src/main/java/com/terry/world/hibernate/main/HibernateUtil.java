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
	public List excecuteSelectQuery(String queryString)
	{
		Session session = getSessionFactory().openSession();
		List resultSet = null;
		try {
			Query query = session.createQuery(queryString);
			resultSet =  query.list();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
		}finally{
			session.close();
		}
		return resultSet;
	}
	public List excecuteSelectQuery(String queryString,int startRow,int offSet)
	{
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		List resultSet = null;
		try {
			transaction = session.beginTransaction();
			
			Query query = session.createQuery(queryString);
			
			query.setFirstResult(startRow);
			
			query.setMaxResults(offSet);
			
			resultSet =  query.list();
			
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			if(transaction!=null)
				transaction.rollback();
		}finally{
			session.close();
		}
		return resultSet;
	}
	public List excecuteWronglyBuiltSelectQuery()
	{
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		List resultSet = null;
		//SQL injection problem
		String userIdString = "5 or 1=1";
		String queryString = "from UserDetails where userId > ";
		try {
			transaction = session.beginTransaction();
			
			Query query = session.createQuery(queryString+userIdString);
			
			resultSet =  query.list();
			
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			if(transaction!=null)
				transaction.rollback();
		}finally{
			session.close();
		}
		return resultSet;
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
	public List excecuteSelectQueryParameters() {
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		List resultSet = null;
		try {
			transaction = session.beginTransaction();
			
			Query query = session.createQuery("from UserDetails where userId > ? and userName = ?");
			
			query.setInteger(0, 5);
			query.setString(1, "User 9");
			
			resultSet =  query.list();
			
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			if(transaction!=null)
				transaction.rollback();
		}finally{
			session.close();
		}
		return resultSet;
	}
	public List excecuteSelectQueryNamedPositionalParameter() {
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		List resultSet = null;
		try {
			transaction = session.beginTransaction();
			
			Query query = session.createQuery("from UserDetails where userId > :userId and userName = :userName");
			
			query.setInteger("userId", 5);
			query.setString("userName", "User 9");
			
			resultSet =  query.list();
			
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			if(transaction!=null)
				transaction.rollback();
		}finally{
			session.close();
		}
		return resultSet;
	}
	public List excecuteNamedQueries() {
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		List resultSet = null;
		try {
			transaction = session.beginTransaction();
			
			Query query = session.getNamedQuery("UserDetails.byId");
			
			query.setInteger("userId", 5);
			query.setString("userName", "User 9");
			
			resultSet =  query.list();
			
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			if(transaction!=null)
				transaction.rollback();
		}finally{
			session.close();
		}
		return resultSet;
	}
	public List excecuteNamedNativeQueries() {
		Session session = getSessionFactory().openSession();
		Transaction transaction = null;
		List resultSet = null;
		try {
			transaction = session.beginTransaction();
			
			Query query = session.getNamedQuery("UserDetails.byName");
			
			query.setString("userName", "User 9");
			
			resultSet =  query.list();
			
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println(e.getMessage());
			if(transaction!=null)
				transaction.rollback();
		}finally{
			session.close();
		}
		return resultSet;
	}
}
