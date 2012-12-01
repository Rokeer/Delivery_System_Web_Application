package action;

import java.util.Map;

import service.MailboxService;
import service.impl.MailboxServiceImpl;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import dao.UserDAO;
import dao.impl.UserDAOImpl;

import entity.Mailbox;
import entity.User;

@SuppressWarnings("serial")
public class MailboxAction extends ActionSupport{

	private JSONObject resultObj = null;
	private int page;
	private int rows;
	private int editMBId;
	private Mailbox mb;
	private String mbTo;
	private String mbTitle;
	private String mbContent;
	
	public String getInboxList(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		int userId = Integer.parseInt((String) session.get("userId"));
		MailboxService ms = new MailboxServiceImpl();
		this.setResultObj(ms.getInboxListByPageAndUser(page, rows, userId));
		System.out.println(this.resultObj.toString());
		return SUCCESS;
	}
	
	
	public String openMailbox(){
		MailboxService ms = new MailboxServiceImpl();
		this.setMb(ms.getInboxByMBId(editMBId));
		ms.editMailboxStatus(editMBId, 1);
		//System.out.println(mb.getMbContent());
		this.setResultObj(JSONObject.fromObject(mb));
		System.out.println(this.resultObj.toString());
		return SUCCESS;
	}
	
	
	public String getMailboxContent() {
		MailboxService ms = new MailboxServiceImpl();
		this.setMb(ms.getInboxByMBId(editMBId));
		//System.out.println(mb.getMbContent());
		this.setResultObj(JSONObject.fromObject(mb));
		System.out.println(this.resultObj.toString());
		return SUCCESS;
	}
	
	public String getSentList(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		int userId = Integer.parseInt((String) session.get("userId"));
		MailboxService ms = new MailboxServiceImpl();
		this.setResultObj(ms.getSentListByPageAndUser(page, rows, userId));
		System.out.println(this.resultObj.toString());
		return SUCCESS;
	}
	
	public String sendMail() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		int userId = Integer.parseInt((String) session.get("userId"));
		MailboxService ms = new MailboxServiceImpl();
		UserDAO ud = new UserDAOImpl();
		User user = ud.getUserByKey("username", mbTo);
		ms.addMailbox(mbTitle, userId, user.getUserId(), mbContent);
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
	public int getEditMBId() {
		return editMBId;
	}

	public void setEditMBId(int editMBId) {
		this.editMBId = editMBId;
	}

	public Mailbox getMb() {
		return mb;
	}

	public void setMb(Mailbox mb) {
		this.mb = mb;
	}


	public String getMbTo() {
		return mbTo;
	}


	public void setMbTo(String mbTo) {
		this.mbTo = mbTo;
	}


	public String getMbTitle() {
		return mbTitle;
	}


	public void setMbTitle(String mbTitle) {
		this.mbTitle = mbTitle;
	}


	public String getMbContent() {
		return mbContent;
	}


	public void setMbContent(String mbContent) {
		this.mbContent = mbContent;
	}

	
}
