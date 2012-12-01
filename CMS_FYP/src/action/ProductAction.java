package action;

import service.ProductService;
import service.impl.ProductServiceImpl;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;

public class ProductAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5869750282398096044L;
	private JSONObject resultObj = null;
	private JSONArray resultAry = null;
	private int page;
	private int rows;
	private int pId;
	private String pName;
	private int pAmount;
	
	public String getProductsListWithoutPage() {
		ProductService ps = new ProductServiceImpl();
		this.setResultAry(ps.getProductList());
		System.out.println(this.resultAry.toString());
		return SUCCESS;
	}
	
	public String getProductsList() {
		ProductService ps = new ProductServiceImpl();
		this.setResultObj(ps.getProductListByPage(page, rows));
		System.out.println(this.resultObj.toString());
		return SUCCESS;
	}
	
	public String delProduct() {
		ProductService ps = new ProductServiceImpl();
		ps.delProduct(pId);
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

	public int getPId() {
		return pId;
	}

	public void setPId(int pId) {
		this.pId = pId;
	}

	public String getPName() {
		return pName;
	}

	public void setPName(String pName) {
		this.pName = pName;
	}

	public int getPAmount() {
		return pAmount;
	}

	public void setPAmount(int pAmount) {
		this.pAmount = pAmount;
	}

	public JSONArray getResultAry() {
		return resultAry;
	}

	public void setResultAry(JSONArray resultAry) {
		this.resultAry = resultAry;
	}
	
}
