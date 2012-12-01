package entity;

public class Mailbox {
	private int mbId;
	private String mbTitle;
	private int mbFrom;
	private int mbTo;
	private String mbContent;
	private int mbStatus;
	
	private String fromName;
	private String toName;
	
	public int getMbId() {
		return mbId;
	}
	public void setMbId(int mbId) {
		this.mbId = mbId;
	}
	public String getMbTitle() {
		return mbTitle;
	}
	public void setMbTitle(String mbTitle) {
		this.mbTitle = mbTitle;
	}
	public int getMbFrom() {
		return mbFrom;
	}
	public void setMbFrom(int mbFrom) {
		this.mbFrom = mbFrom;
	}
	public int getMbTo() {
		return mbTo;
	}
	public void setMbTo(int mbTo) {
		this.mbTo = mbTo;
	}
	public String getMbContent() {
		return mbContent;
	}
	public void setMbContent(String mbContent) {
		this.mbContent = mbContent;
	}
	public int getMbStatus() {
		return mbStatus;
	}
	public void setMbStatus(int mbStatus) {
		this.mbStatus = mbStatus;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	
	
}
