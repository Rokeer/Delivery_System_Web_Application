package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateSessionFactory;

import dao.SBidDAO;
import entity.SBid;

public class SBidDAOImpl implements SBidDAO{

	private Session session;
	private Transaction transaction;
	private Query query;
	
	public String addSBid(SBid sbid) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.save(sbid);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}

	public boolean checkHave(String key, String value) {
		SBid sbid = this.getSBidByKey(key, value);
		if(sbid == null){
			return false;
		}
		return true;
	}

	public String delSBid(SBid sbid) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.delete(sbid);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}

	public SBid getSBidById(int sbId) {
		session = HibernateSessionFactory.getSession();
		SBid sbid = (SBid) session.get(SBid.class, sbId);
		HibernateSessionFactory.closeSession();
		return sbid;
	}

	public SBid getSBidByKey(String key, String value) {
		session = HibernateSessionFactory.getSession();
		String hql = "select sbId from SBid as sbids where " + key + "='"
				+ value + "'";
		query = session.createQuery(hql);
		try {
			int sbId = Integer.parseInt(query.uniqueResult().toString());
			SBid sbid = (SBid) session.get(SBid.class, sbId);
			HibernateSessionFactory.closeSession();
			return sbid;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SBid> getSBids() {
		session = HibernateSessionFactory.getSession();
		String hql = "from SBid as sbids";
		//String hql = "from Material as materials";
		query = session.createQuery(hql);
		if (query == null){
			return null;
		}
		try {
			List<SBid> result = query.list();
			//List<MOrder> result = query.list();
			HibernateSessionFactory.closeSession();
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public String updateSBid(SBid sbid) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.update(sbid);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}

	@SuppressWarnings("unchecked")
	public List<SBid> getSBidsByMOId(int moId) {
		session = HibernateSessionFactory.getSession();
		//String hql = "from SBid as sbids";
		String hql = "from SBid as sbids where sbMOId='" + moId + "'";
		try {
		//String hql = "from Material as materials";
		query = session.createQuery(hql);
		if (query == null){
			return null;
		}
		
			List<SBid> result = query.list();
			//List<MOrder> result = query.list();
			HibernateSessionFactory.closeSession();
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	public List<SBid> getSBidsBySupplier(int supplier) {
		session = HibernateSessionFactory.getSession();
		//String hql = "from SBid as sbids";
		String hql = "from SBid as sbids where sbSupplier='" + supplier + "'";
		try {
		//String hql = "from Material as materials";
		query = session.createQuery(hql);
		if (query == null){
			return null;
		}
		
			List<SBid> result = query.list();
			//List<MOrder> result = query.list();
			HibernateSessionFactory.closeSession();
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

}
