package entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CMTransaction implements Serializable {
	private int cmtId;
	private int cmtPId;
	private int cmtAmount;
	private int cmtStatus;
	private String cmtTime;
	private int cmtClient;
	private String productName;
	private String clientName;
	private String inventory;
	
	public int getCmtId() {
		return cmtId;
	}
	public void setCmtId(int cmtId) {
		this.cmtId = cmtId;
	}
	public int getCmtPId() {
		return cmtPId;
	}
	public void setCmtPId(int cmtPId) {
		this.cmtPId = cmtPId;
	}
	public int getCmtAmount() {
		return cmtAmount;
	}
	public void setCmtAmount(int cmtAmount) {
		this.cmtAmount = cmtAmount;
	}
	public int getCmtStatus() {
		return cmtStatus;
	}
	public void setCmtStatus(int cmtSatus) {
		this.cmtStatus = cmtSatus;
	}
	public String getCmtTime() {
		return cmtTime;
	}
	public void setCmtTime(String cmtTime) {
		this.cmtTime = cmtTime;
	}
	public int getCmtClient() {
		return cmtClient;
	}
	public void setCmtClient(int cmtClient) {
		this.cmtClient = cmtClient;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getInventory() {
		return inventory;
	}
	public void setInventory(String inventory) {
		this.inventory = inventory;
	}
	
}
