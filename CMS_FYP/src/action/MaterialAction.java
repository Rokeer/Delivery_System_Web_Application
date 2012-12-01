package action;

import service.MaterialService;
import service.impl.MaterialServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class MaterialAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9119879774374946517L;
	private JSONObject resultObj = null;
	private JSONArray resultAry = null;
	private int page;
	private int rows;
	private int mId;
	private String mName;
	private int mAmount;
	public String execute() {
		return SUCCESS;
	}

	public String getMaterialsListWithoutPage() {
		MaterialService ms = new MaterialServiceImpl();
		//this.setResultArray(ms.getMaterialList1());
		this.setResultAry(ms.getMaterialList());
		System.out.println(this.resultAry.toString());
		//System.out.println(this.resultArray.toString());
		return SUCCESS;
	}
	
	public String getMaterialList() {
		MaterialService ms = new MaterialServiceImpl();
		//this.setResultArray(ms.getMaterialList1());
		this.setResultObj(ms.getMaterialListByPage(page, rows));
		System.out.println(this.resultObj.toString());
		//System.out.println(this.resultArray.toString());
		return SUCCESS;
	}
	
	public String addMaterial() {
		MaterialService ms = new MaterialServiceImpl();
		ms.addMaterial(mName, mAmount);
		return SUCCESS;
	}
	
	public String editMaterial() {
		MaterialService ms = new MaterialServiceImpl();
		ms.editMaterial(mId, mName, mAmount);
		return SUCCESS;
	}

	public String delMaterial() {
		MaterialService ms = new MaterialServiceImpl();
		ms.delMaterial(mId);
		return SUCCESS;
	}
	
	public JSONObject getResultObj() {
		return resultObj;
	}

	public void setResultObj(JSONObject resultObj) {
		this.resultObj = resultObj;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public String getMName() {
		return mName;
	}

	public void setMName(String mName) {
		this.mName = mName;
	}

	public int getMAmount() {
		return mAmount;
	}

	public void setMAmount(int mAmount) {
		this.mAmount = mAmount;
	}

	public int getMId() {
		return mId;
	}

	public void setMId(int mId) {
		this.mId = mId;
	}

	public JSONArray getResultAry() {
		return resultAry;
	}

	public void setResultAry(JSONArray resultAry) {
		this.resultAry = resultAry;
	}
	
}
