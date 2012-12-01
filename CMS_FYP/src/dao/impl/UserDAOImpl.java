package dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateSessionFactory;

import dao.UserDAO;
import entity.User;

public class UserDAOImpl implements UserDAO{
	private Session session;
	private Transaction transaction;
	private Query query;
	public void editUser(User user) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		HibernateSessionFactory.closeSession();
	}

	public User getUser(int userId) {
		session = HibernateSessionFactory.getSession();
		User user = (User) session.get(User.class, userId);
		HibernateSessionFactory.closeSession();
		return user;
	}

	public User getUserByKey(String key, String value) {
		session = HibernateSessionFactory.getSession();
		String hql = "select userId from User as users where " + key + "='"
				+ value + "'";
		query = session.createQuery(hql);
		try {
			int userId = Integer.parseInt(query.uniqueResult().toString());
			User user = (User) session.get(User.class, userId);
			HibernateSessionFactory.closeSession();
			return user;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public void regUser(User user) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		HibernateSessionFactory.closeSession();
	}

}
