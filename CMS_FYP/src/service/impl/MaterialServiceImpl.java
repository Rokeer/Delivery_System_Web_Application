package service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.MaterialDAO;
import dao.impl.MaterialDAOImpl;
import entity.Material;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.MaterialService;

public class MaterialServiceImpl implements MaterialService {

	public JSONObject getMaterialListByPage(int page, int rows) {
		int from;
		int to;
		MaterialDAO md = new MaterialDAOImpl();
		List<Material> resultList = md.getMaterials();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", resultList.size());
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
		map.put("rows", resultList.subList(from, to));
		JSONObject resultObj = JSONObject.fromObject(map);
		return resultObj;
	}

	public JSONArray getMaterialList() {
		MaterialDAO md = new MaterialDAOImpl();
		List<Material> resultList = md.getMaterials();
		JSONArray resultObj = JSONArray.fromObject(resultList);
		return resultObj;
	}

	public boolean addMaterial(String name, int amount) {
		Material material = new Material();
		material.setmName(name);
		material.setmAmount(amount);
		MaterialDAO md = new MaterialDAOImpl();
		md.addMaterial(material);
		return true;
	}

	public boolean editMaterial(int id, String name, int amount) {
		Material material = new Material();
		material.setmId(id);
		material.setmName(name);
		material.setmAmount(amount);
		MaterialDAO md = new MaterialDAOImpl();
		md.updateMaterial(material);
		return true;
	}

	public boolean delMaterial(int id) {
		Material material = new Material();
		material.setmId(id);
		material.setmName("");
		material.setmAmount(0);
		MaterialDAO md = new MaterialDAOImpl();
		md.delMaterial(material);
		return true;
	}

}
