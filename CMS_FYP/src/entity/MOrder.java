package entity;

public class MOrder {
	private int moId;
	private int moMId;
	private int moAmount;
	private int moStatus;
	private int moSupplier;
	private String moTime;
	private String supplierName;
	private String materialName;
	public int getMoId() {
		return moId;
	}
	public void setMoId(int moId) {
		this.moId = moId;
	}
	public int getMoMId() {
		return moMId;
	}
	public void setMoMId(int moMId) {
		this.moMId = moMId;
	}
	public int getMoAmount() {
		return moAmount;
	}
	public void setMoAmount(int moAmount) {
		this.moAmount = moAmount;
	}
	public int getMoStatus() {
		return moStatus;
	}
	public void setMoStatus(int moStatus) {
		this.moStatus = moStatus;
	}
	public int getMoSupplier() {
		return moSupplier;
	}
	public void setMoSupplier(int moSupplier) {
		this.moSupplier = moSupplier;
	}
	public String getMoTime() {
		return moTime;
	}
	public void setMoTime(String moTime) {
		this.moTime = moTime;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
}
