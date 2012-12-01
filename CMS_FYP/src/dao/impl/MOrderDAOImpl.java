package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateSessionFactory;
import dao.MOrderDAO;
import entity.MOrder;

public class MOrderDAOImpl implements MOrderDAO{
	private Session session;
	private Transaction transaction;
	private Query query;
	public String addMOrder(MOrder morder) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.save(morder);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}

	public boolean checkHave(String key, String value) {
		MOrder morder = this.getMOrderByKey(key, value);
		if(morder == null){
			return false;
		}
		return true;
	}

	public String delMOrder(MOrder morder) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.delete(morder);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}

	public MOrder getMOrderById(int morderId) {
		session = HibernateSessionFactory.getSession();
		MOrder morder = (MOrder) session.get(MOrder.class, morderId);
		HibernateSessionFactory.closeSession();
		return morder;
	}

	public MOrder getMOrderByKey(String key, String value) {
		session = HibernateSessionFactory.getSession();
		String hql = "select moId from MOrder as morders where " + key + "='"
				+ value + "'";
		query = session.createQuery(hql);
		try {
			int moId = Integer.parseInt(query.uniqueResult().toString());
			MOrder morder = (MOrder) session.get(MOrder.class, moId);
			HibernateSessionFactory.closeSession();
			return morder;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<MOrder> getMOrders() {
		session = HibernateSessionFactory.getSession();
		String hql = "from MOrder as morders";
		//String hql = "from Material as materials";
		query = session.createQuery(hql);
		if (query == null){
			return null;
		}
		try {
			List<MOrder> result = query.list();
			//List<MOrder> result = query.list();
			HibernateSessionFactory.closeSession();
			return result;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
		
	}

	public String updateMOrder(MOrder morder) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.update(morder);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}

	@SuppressWarnings("unchecked")
	public List<MOrder> getMOrdersWithoutSelection() {
		session = HibernateSessionFactory.getSession();
		String hql = "from MOrder as morders where moStatus=0";
		//String hql = "from Material as materials";
		query = session.createQuery(hql);
		if (query == null){
			return null;
		}
		try {
			List<MOrder> result = query.list();
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
