package entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Material implements Serializable {
	private int mId;
	private String mName;
	private int mAmount;
	public Material() {
		super();
	}
	public Material(int mId, String mName, int mAmount) {
		super();
		this.mId = mId;
		this.mName = mName;
		this.mAmount = mAmount;
	}
	public int getmId() {
		return mId;
	}
	public void setmId(int mId) {
		this.mId = mId;
	}
	public String getmName() {
		return mName;
	}
	public void setmName(String mName) {
		this.mName = mName;
	}
	public int getmAmount() {
		return mAmount;
	}
	public void setmAmount(int mAmount) {
		this.mAmount = mAmount;
	}
}
