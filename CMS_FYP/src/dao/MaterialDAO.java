package dao;

import java.util.List;

import entity.Material;

public interface MaterialDAO {
	// 获取原材料列表
	public List<Material> getMaterials();
	
	
	// 获取原料实例
	public Material getMaterialById(int materialId);
	
	public Material getMaterialByKey(String key, String value);

	// 添加原材料种类
	public String addMaterial(Material material);

	// 删除原材料种类
	public String delMaterial(Material material);

	// 修改原材料种类
	public String updateMaterial(Material material);
	
	public boolean checkHave(String key, String value);
}
