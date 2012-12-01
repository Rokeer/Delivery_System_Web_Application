package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateSessionFactory;

import dao.CMTransactionDAO;
import entity.CMTransaction;

public class CMTransactionDAOImpl implements CMTransactionDAO{
	private Session session;
	private Transaction transaction;
	private Query query;
	public String addCMTransaction(CMTransaction cmtransaction) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.save(cmtransaction);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}

	public boolean checkHave(String key, String value) {
		CMTransaction cmtransaction = this.getCMTransactionByKey(key, value);
		if(cmtransaction == null){
			return false;
		}
		return true;
	}

	public String delCMTransaction(CMTransaction cmtransaction) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.delete(cmtransaction);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}

	public CMTransaction getCMTransactionById(int cmtransactionId) {
		session = HibernateSessionFactory.getSession();
		CMTransaction cmtransaction = (CMTransaction) session.get(CMTransaction.class, cmtransactionId);
		HibernateSessionFactory.closeSession();
		return cmtransaction;
	}

	public CMTransaction getCMTransactionByKey(String key, String value) {
		session = HibernateSessionFactory.getSession();
		String hql = "select cmtId from CMTransaction as cmtransactions where " + key + "='"
				+ value + "'";
		query = session.createQuery(hql);
		try {
			int cmtId = Integer.parseInt(query.uniqueResult().toString());
			CMTransaction cmtransaction = (CMTransaction) session.get(CMTransaction.class, cmtId);
			HibernateSessionFactory.closeSession();
			return cmtransaction;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<CMTransaction> getCMTransactions() {
		session = HibernateSessionFactory.getSession();
		String hql = "from CMTransaction as cmtransactions";
		//String hql = "from Material as materials";
		query = session.createQuery(hql);
		if (query == null){
			return null;
		}
		try {
			List<CMTransaction> result = query.list();
			//List<CMTransaction> result = query.list();
			HibernateSessionFactory.closeSession();
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
		
	}

	public String updateCMTransaction(CMTransaction cmtransaction) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.update(cmtransaction);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}

	public List<CMTransaction> getCMTransactionsByUser(int cmtClient) {
		session = HibernateSessionFactory.getSession();
		String hql = "from CMTransaction as cmtransactions where cmtClient='"+cmtClient+"'";
		//String hql = "from Material as materials";
		query = session.createQuery(hql);
		if (query == null){
			return null;
		}
		try {
			List<CMTransaction> result = query.list();
			//List<CMTransaction> result = query.list();
			HibernateSessionFactory.closeSession();
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

}
