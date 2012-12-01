package service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


import dao.MaterialDAO;
import dao.ProductDAO;
import dao.impl.MaterialDAOImpl;
import dao.impl.ProductDAOImpl;
import entity.Material;
import entity.Product;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.ProductService;

public class ProductServiceImpl implements ProductService{

	public boolean addProduct(String name, int amount, String material) {
		Product product = new Product();
		product.setpName(name);
		product.setpAmount(amount);
		product.setpMaterial(material);
		ProductDAO pd = new ProductDAOImpl();
		pd.addProduct(product);
		return true;
	}

	public boolean delProduct(int id) {
		Product product = new Product();
		product.setpId(id);
		product.setpName("");
		product.setpAmount(0);
		product.setpMaterial("");
		ProductDAO pd = new ProductDAOImpl();
		pd.delProduct(product);
		return true;
	}

	public boolean editProduct(int id, String name, int amount, String material) {
		Product product = new Product();
		product.setpId(id);
		product.setpName(name);
		product.setpAmount(amount);
		product.setpMaterial(material);
		ProductDAO pd = new ProductDAOImpl();
		pd.addProduct(product);
		return true;
	}

	public JSONArray getProductList() {
		// 持久化DAO
		ProductDAO pd = new ProductDAOImpl();
		MaterialDAO md = new MaterialDAOImpl();
		
		// 获取products列表
		List<Product> resultList = pd.getProducts();
		Iterator<Product> itr = resultList.iterator();
		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		String resultDisplayMaterials = "";
		
		while (itr.hasNext()) {
			Product product = (Product) itr.next();
			// 将需要的每种材料和数量分开
			String[] materialList = product.getpMaterial().split(";");
			int resultMakeAmount = 9999999;
			for(int i = 0; i < materialList.length; i++) {
				// 获取材料的id和数量
				int mId = Integer.parseInt(materialList[i].split(":")[0]);
				int mNeed = Integer.parseInt(materialList[i].split(":")[1]);
				resultMap.put(mId, mNeed);
				Material material = md.getMaterialById(mId);
				// 计算最多可以制作多少此种商品
				if (material.getmAmount()/mNeed < resultMakeAmount) {
					resultMakeAmount = material.getmAmount()/mNeed;
				}
				// 重组显示字符串
				resultDisplayMaterials = resultDisplayMaterials + material.getmName() + ":" + mNeed + "; ";
			}
			int resultTotal = resultMakeAmount + product.getpAmount();
			product.setTotal(resultTotal);
			product.setMaterials(resultMap);
			product.setDisplayMaterials(resultDisplayMaterials);
			product.setMakeAmount(resultMakeAmount);
		}
		JSONArray resultObj = JSONArray.fromObject(resultList);
		return resultObj;
	}

	public JSONObject getProductListByPage(int page, int rows) {
		int from;
		int to;
		// 持久化DAO
		ProductDAO pd = new ProductDAOImpl();
		MaterialDAO md = new MaterialDAOImpl();
		
		// 获取products列表
		List<Product> resultList = pd.getProducts();
		// 构造json对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", resultList.size());
		
		// 获取需要的List
		if (rows * (page - 1) < 0) {
			from = 0;
		} else {
			from = rows * (page - 1);
		}
		if (page * rows > resultList.size()) {
			to = resultList.size();
		} else {
			to = page * rows;
		}
		
		List<Product> tmpList = resultList.subList(from, to);
		Iterator<Product> itr = tmpList.iterator();
		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		String resultDisplayMaterials = "";
		
		while (itr.hasNext()) {
			resultDisplayMaterials = "";
			Product product = (Product) itr.next();
			// 将需要的每种材料和数量分开
			String[] materialList = product.getpMaterial().split(";");
			int resultMakeAmount = 9999999;
			for(int i = 0; i < materialList.length; i++) {
				// 获取材料的id和数量
				int mId = Integer.parseInt(materialList[i].split(":")[0]);
				int mNeed = Integer.parseInt(materialList[i].split(":")[1]);
				resultMap.put(mId, mNeed);
				Material material = md.getMaterialById(mId);
				// 计算最多可以制作多少此种商品
				if (material.getmAmount()/mNeed < resultMakeAmount) {
					resultMakeAmount = material.getmAmount()/mNeed;
				}
				// 重组显示字符串
				resultDisplayMaterials = resultDisplayMaterials + material.getmName() + ":" + mNeed + "; ";
			}
			int resultTotal = resultMakeAmount + product.getpAmount();
			product.setTotal(resultTotal);
			product.setMaterials(resultMap);
			product.setDisplayMaterials(resultDisplayMaterials);
			product.setMakeAmount(resultMakeAmount);
		}
		
		map.put("rows", tmpList);
		JSONObject resultObj = JSONObject.fromObject(map);
		return resultObj;
	}

}
