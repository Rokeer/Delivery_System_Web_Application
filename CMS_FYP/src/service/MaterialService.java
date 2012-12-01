package service;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public interface MaterialService {
	public JSONObject getMaterialListByPage(int page, int rows);
	public JSONArray getMaterialList();
	public boolean addMaterial(String name, int amount);
	public boolean editMaterial(int id, String name, int amount);
	public boolean delMaterial(int id);
}
