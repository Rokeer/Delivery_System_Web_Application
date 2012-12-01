package action;

import java.util.Map;

import service.SBidService;
import service.impl.SBidServiceImpl;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SBidAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6663547718945858090L;

	private JSONObject resultObj = null;
	private int page;
	private int rows;
	private int detailMOId;
	private int editSBId;
	private String addMOId;
	private int addSBPrice;
	
	public String getSBidListByMOId() {
		if (detailMOId != 0){
			SBidService ss = new SBidServiceImpl();
			this.setResultObj(ss.getSBidListByMOId(detailMOId));
			System.out.println(this.resultObj.toString());
		}
		return SUCCESS;
	}
	
	public String acceptBid() {
		SBidService ss = new SBidServiceImpl();
		ss.acceptBid(editSBId);
		return SUCCESS;
	}

	public String addBid() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		int userId = Integer.parseInt((String) session.get("userId"));
		SBidService ss = new SBidServiceImpl();
		ss.addSBid(Integer.parseInt(addMOId), addSBPrice, userId);
		return SUCCESS;
	}
	
	public String getSBidListByPageAndSupplier() {
		SBidService ss = new SBidServiceImpl();
		Map<String, Object> session = ActionContext.getContext().getSession();
		int userId = Integer.parseInt((String) session.get("userId"));
		this.setResultObj(ss.getSBidListByPageAndSupplier(page, rows, userId));
		System.out.println(this.resultObj.toString());
		return SUCCESS;
	}
	
	public String delBid() {
		SBidService ss = new SBidServiceImpl();
		ss.delSBid(editSBId);
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

	public int getDetailMOId() {
		return detailMOId;
	}

	public void setDetailMOId(int detailMOId) {
		this.detailMOId = detailMOId;
	}

	public int getEditSBId() {
		return editSBId;
	}

	public void setEditSBId(int editSBId) {
		this.editSBId = editSBId;
	}

	public String getAddMOId() {
		return addMOId;
	}

	public void setAddMOId(String addMOId) {
		this.addMOId = addMOId;
	}

	public int getAddSBPrice() {
		return addSBPrice;
	}

	public void setAddSBPrice(int addSBPrice) {
		this.addSBPrice = addSBPrice;
	}
	
}
