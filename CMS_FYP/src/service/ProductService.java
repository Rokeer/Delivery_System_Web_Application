package service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface ProductService {
	public JSONObject getProductListByPage(int page, int rows);
	public JSONArray getProductList();
	public boolean addProduct(String name, int amount, String material);
	public boolean editProduct(int id, String name, int amount, String material);
	public boolean delProduct(int id);
}
