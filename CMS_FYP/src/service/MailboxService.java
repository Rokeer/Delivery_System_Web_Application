package service;

import entity.Mailbox;
import net.sf.json.JSONObject;

public interface MailboxService {
	public JSONObject getInboxListByPageAndUser(int page, int rows, int uId);
	public JSONObject getSentListByPageAndUser(int page, int rows, int uId);
	public Mailbox getInboxByMBId(int mbId);
	public boolean addMailbox(String mbTitle, int mbFrom, int mbTo, String mbContent);
	public boolean editMailboxStatus(int mbId, int mbStatus);
	public boolean delMailbox(int mbId);
}
