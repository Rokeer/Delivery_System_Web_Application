package dao;

import java.util.List;

import entity.Product;

public interface ProductDAO {
	public List<Product> getProducts();
	public Product getProductById(int pId);
	public Product getProductByKey(String key, String value);
	public String addProduct(Product product);
	public String delProduct(Product product);
	public String updateProduct(Product product);
	public boolean checkHave(String key, String value);
}
