package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateSessionFactory;

import dao.MaterialDAO;
import entity.Material;

public class MaterialDAOImpl implements MaterialDAO{
	private Session session;
	private Transaction transaction;
	private Query query;
	public String addMaterial(Material material) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.save(material);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}
	public String delMaterial(Material material) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.delete(material);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}
	public Material getMaterialById(int materialId) {
		session = HibernateSessionFactory.getSession();
		Material material = (Material) session.get(Material.class, materialId);
		HibernateSessionFactory.closeSession();
		return material;
	}
	public Material getMaterialByKey(String key, String value) {
		session = HibernateSessionFactory.getSession();
		String hql = "select mId from Material as materials where " + key + "='"
				+ value + "'";
		query = session.createQuery(hql);
		try {
			int materialId = Integer.parseInt(query.uniqueResult().toString());
			Material material = (Material) session.get(Material.class, materialId);
			HibernateSessionFactory.closeSession();
			return material;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}
	public String updateMaterial(Material material) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.update(material);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}
	
	@SuppressWarnings("unchecked")
	public List<Material> getMaterials() {
		session = HibernateSessionFactory.getSession();
		String hql = "from Material as materials";
		query = session.createQuery(hql);
		if (query == null){
			return null;
		}
		List<Material> result = query.list();
		HibernateSessionFactory.closeSession();
		return result;
	}
	public boolean checkHave(String key, String value) {
		Material material = this.getMaterialByKey(key, value);
		if(material==null){
			return false;
		}
		return true;
	}
	

}
