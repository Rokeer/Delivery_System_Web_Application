package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateSessionFactory;

import dao.MailboxDAO;
import entity.Mailbox;

public class MailboxDAOImpl implements MailboxDAO{

	private Session session;
	private Transaction transaction;
	private Query query;
	
	public String addMailbox(Mailbox mailbox) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.save(mailbox);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}

	public String delMailbox(Mailbox mailbox) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.delete(mailbox);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}

	public Mailbox getMailboxById(int mbId) {
		session = HibernateSessionFactory.getSession();
		Mailbox mailbox = (Mailbox) session.get(Mailbox.class, mbId);
		HibernateSessionFactory.closeSession();
		return mailbox;
	}

	public Mailbox getMailboxByKey(String key, String value) {
		session = HibernateSessionFactory.getSession();
		String hql = "select mbId from Mailbox as mailbox where " + key + "='"
				+ value + "'";
		query = session.createQuery(hql);
		try {
			int mbId = Integer.parseInt(query.uniqueResult().toString());
			Mailbox mailbox = (Mailbox) session.get(Mailbox.class, mbId);
			HibernateSessionFactory.closeSession();
			return mailbox;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<Mailbox> getInboxByuId(int uId) {
		session = HibernateSessionFactory.getSession();
		//String hql = "from SBid as sbids";
		String hql = "from Mailbox as mailbox where mbTo='" + uId + "' order by mbId desc";
		try {
		//String hql = "from Material as materials";
		query = session.createQuery(hql);
		if (query == null){
			return null;
		}
		
			List<Mailbox> result = query.list();
			//List<MOrder> result = query.list();
			HibernateSessionFactory.closeSession();
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Mailbox> getSentByuId(int uId) {
		session = HibernateSessionFactory.getSession();
		//String hql = "from SBid as sbids";
		String hql = "from Mailbox as mailbox where mbFrom='" + uId + "' order by mbId desc";
		try {
		//String hql = "from Material as materials";
		query = session.createQuery(hql);
		if (query == null){
			return null;
		}
		
			List<Mailbox> result = query.list();
			//List<MOrder> result = query.list();
			HibernateSessionFactory.closeSession();
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public String updateMailbox(Mailbox mailbox) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.update(mailbox);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}

	
}
