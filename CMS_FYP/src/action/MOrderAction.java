package action;

import java.util.Map;

import service.MOrderService;
import service.impl.MOrderServiceImpl;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MOrderAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1798517118406225799L;

	private JSONObject resultObj = null;
	private int page;
	private int rows;
	private int moId;
	private int moMId;
	private int moAmount;
	
	public String getMOrdersList() {
		MOrderService ms = new MOrderServiceImpl();
		this.setResultObj(ms.getMOrderListByPage(page, rows));
		System.out.println(this.resultObj.toString());
		return SUCCESS;
	}

	public String getMOrdersListWithoutSelection() {
		MOrderService ms = new MOrderServiceImpl();
		this.setResultObj(ms.getMOrderListByPageWithoutSelection(page, rows));
		System.out.println(this.resultObj.toString());
		return SUCCESS;
	}
	
	public String addMOrder(){
		MOrderService ms = new MOrderServiceImpl();
		ms.addMOrder(moMId, moAmount);
		return SUCCESS;
	}
	
	public String delMOrder(){
		MOrderService ms = new MOrderServiceImpl();
		ms.delMOrder(moId);
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
	
	
}
