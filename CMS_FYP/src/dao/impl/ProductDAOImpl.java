package dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import config.HibernateSessionFactory;

import dao.ProductDAO;
import entity.Product;

public class ProductDAOImpl implements ProductDAO{

	private Session session;
	private Transaction transaction;
	private Query query;
	public String addProduct(Product product) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}
	public boolean checkHave(String key, String value) {
		Product product = this.getProductByKey(key, value);
		if(product == null){
			return false;
		}
		return true;
	}
	public String delProduct(Product product) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.delete(product);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}
	public Product getProductById(int pId) {
		session = HibernateSessionFactory.getSession();
		Product product = (Product) session.get(Product.class, pId);
		HibernateSessionFactory.closeSession();
		return product;
	}
	public Product getProductByKey(String key, String value) {
		session = HibernateSessionFactory.getSession();
		String hql = "select mId from Product as products where " + key + "='"
				+ value + "'";
		query = session.createQuery(hql);
		try {
			int pId = Integer.parseInt(query.uniqueResult().toString());
			Product product = (Product) session.get(Product.class, pId);
			HibernateSessionFactory.closeSession();
			return product;
		} catch (Exception e) {
			System.out.println(e.toString());
			HibernateSessionFactory.closeSession();
			return null;
		}
	}
	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		session = HibernateSessionFactory.getSession();
		String hql = "from Product as products";
		query = session.createQuery(hql);
		if (query == null){
			return null;
		}
		List<Product> result = query.list();
		HibernateSessionFactory.closeSession();
		return result;
	}
	public String updateProduct(Product product) {
		session = HibernateSessionFactory.getSession();
		try {
			transaction = session.beginTransaction();
			session.update(product);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		HibernateSessionFactory.closeSession();
		return "success";
	}
	
	
}
