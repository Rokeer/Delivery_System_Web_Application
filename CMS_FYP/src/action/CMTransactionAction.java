package action;

import java.util.Map;

import service.CMTransactionService;
import service.impl.CMTransactionServiceImpl;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class CMTransactionAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7672137539027132669L;
	private JSONObject resultObj = null;
	private int page;
	private int rows;
	private int cmtPId;
	private int cmtAmount;
	private int cmtClient;
	private int cmtId;
	private int cmtStatus;
	private String cmtTime;
	private String cmtIds;
	private String cmtStatuses;
	
	
	public String getCMTransactionsList() {
		CMTransactionService cs = new CMTransactionServiceImpl();
		this.setResultObj(cs.getCMTransactionListByPage(page, rows));
		System.out.println(this.resultObj.toString());
		return SUCCESS;
	}
	
	public String getCMTransactionsListByUser() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		int userId = Integer.parseInt((String) session.get("userId"));
		CMTransactionService cs = new CMTransactionServiceImpl();
		this.setResultObj(cs.getCMTransactionListByUser(page, rows, userId));
		System.out.println(this.resultObj.toString());
		return SUCCESS;
	}
	
	public String addCMTransaction() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		int userId = Integer.parseInt((String) session.get("userId"));
		CMTransactionService cs = new CMTransactionServiceImpl();
		cs.addCMTransaction(cmtPId, cmtAmount, userId);
		return SUCCESS;
	}
	
	public String delCMTransaction() {
		CMTransactionService cs = new CMTransactionServiceImpl();
		cs.delCMTransaction(cmtId);
		return SUCCESS;
	}
	
	public String editCMTransactionStatus() {
		String[] tmpId = cmtIds.split(",");
		String[] tmpStatus = cmtStatuses.split(",");
		CMTransactionService cs = new CMTransactionServiceImpl();
		for (int i = 0; i < tmpId.length; i++) {
			cs.editCMTransactionStatus(tmpId[i], tmpStatus[i]);
		}
		return SUCCESS;
	}
	
	public String editCMTransaction() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		int userId = Integer.parseInt((String) session.get("userId"));
		CMTransactionService cs = new CMTransactionServiceImpl();
		cs.editCMTransaction(cmtId, cmtPId, cmtAmount, 0, "00-00-0000", userId);
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

	public int getCmtClient() {
		return cmtClient;
	}

	public void setCmtClient(int cmtClient) {
		this.cmtClient = cmtClient;
	}

	public int getCmtId() {
		return cmtId;
	}

	public void setCmtId(int cmtId) {
		this.cmtId = cmtId;
	}

	public int getCmtStatus() {
		return cmtStatus;
	}

	public void setCmtStatus(int cmtStatus) {
		this.cmtStatus = cmtStatus;
	}

	public String getCmtTime() {
		return cmtTime;
	}

	public void setCmtTime(String cmtTime) {
		this.cmtTime = cmtTime;
	}

	public String getCmtIds() {
		return cmtIds;
	}

	public void setCmtIds(String cmtIds) {
		this.cmtIds = cmtIds;
	}

	public String getCmtStatuses() {
		return cmtStatuses;
	}

	public void setCmtStatuses(String cmtStatuses) {
		this.cmtStatuses = cmtStatuses;
	}
	
}
