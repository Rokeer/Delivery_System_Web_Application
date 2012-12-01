package entity;

import java.io.Serializable;
import java.util.Map;

@SuppressWarnings("serial")
public class Product implements Serializable {
	private int pId;
	private String pName;
	private int pAmount;
	private String pMaterial;
	// 第一个Integer是Material ID，第二个是所需数量
	private Map<Integer, Integer> materials;
	private String displayMaterials;
	// 使用剩余原料还可以制作多少
	private int makeAmount;
	private int total;
	
	public int getMakeAmount() {
		return makeAmount;
	}
	public void setMakeAmount(int makeAmount) {
		this.makeAmount = makeAmount;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpAmount() {
		return pAmount;
	}
	public void setpAmount(int pAmount) {
		this.pAmount = pAmount;
	}
	public String getpMaterial() {
		return pMaterial;
	}
	public void setpMaterial(String pMaterial) {
		this.pMaterial = pMaterial;
	}
	public Map<Integer, Integer> getMaterials() {
		return materials;
	}
	public void setMaterials(Map<Integer, Integer> materials) {
		this.materials = materials;
	}
	public String getDisplayMaterials() {
		return displayMaterials;
	}
	public void setDisplayMaterials(String displayMaterials) {
		this.displayMaterials = displayMaterials;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
