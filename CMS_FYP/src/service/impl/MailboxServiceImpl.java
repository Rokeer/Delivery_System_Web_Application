package service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.MailboxDAO;
import dao.UserDAO;
import dao.impl.MailboxDAOImpl;
import dao.impl.UserDAOImpl;
import entity.Mailbox;
import entity.User;
import net.sf.json.JSONObject;
import service.MailboxService;

public class MailboxServiceImpl implements MailboxService{

	public boolean addMailbox(String mbTitle, int mbFrom, int mbTo,
			String mbContent) {
		Mailbox mailbox = new Mailbox();
		MailboxDAO md = new MailboxDAOImpl();
		mailbox.setMbContent(mbContent);
		mailbox.setMbFrom(mbFrom);
		mailbox.setMbStatus(0);
		mailbox.setMbTitle(mbTitle);
		mailbox.setMbTo(mbTo);
		md.addMailbox(mailbox);
		return true;
	}

	public boolean delMailbox(int mbId) {
		Mailbox mailbox = new Mailbox();
		MailboxDAO md = new MailboxDAOImpl();
		mailbox.setMbContent("");
		mailbox.setMbFrom(1);
		mailbox.setMbStatus(1);
		mailbox.setMbTitle("");
		mailbox.setMbTo(1);
		mailbox.setMbId(mbId);
		md.delMailbox(mailbox);
		return true;
	}

	public boolean editMailboxStatus(int mbId, int mbStatus) {
		MailboxDAO md = new MailboxDAOImpl();
		Mailbox mailbox = md.getMailboxById(mbId);
		mailbox.setMbStatus(mbStatus);
		md.updateMailbox(mailbox);
		return true;
	}

	public JSONObject getInboxListByPageAndUser(int page, int rows, int uId) {
		int from;
		int to;
		MailboxDAO md = new MailboxDAOImpl();
		UserDAO ud = new UserDAOImpl();
		List<Mailbox> resultList = md.getInboxByuId(uId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", resultList.size());
		if (rows * (page - 1) < 0) {
			from = 0;
		} else {
			from = rows * (page - 1);
		}
		if (page * rows > resultList.size()) {
			to = resultList.size();
		} else {
			to = page * rows;
		}
		
		Iterator<Mailbox> itr = resultList.iterator();
		User user;
		while (itr.hasNext()) {
			// 获取需要显示的内容
			Mailbox mailbox = (Mailbox) itr.next();
			user = ud.getUser(mailbox.getMbFrom());
			mailbox.setFromName(user.getUsername());
			user = ud.getUser(mailbox.getMbTo());
			mailbox.setToName(user.getUsername());
		}
		
		map.put("rows", resultList.subList(from, to));
		JSONObject resultObj = JSONObject.fromObject(map);
		return resultObj;
	}

	public JSONObject getSentListByPageAndUser(int page, int rows, int uId) {
		int from;
		int to;
		MailboxDAO md = new MailboxDAOImpl();
		UserDAO ud = new UserDAOImpl();
		List<Mailbox> resultList = md.getSentByuId(uId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", resultList.size());
		if (rows * (page - 1) < 0) {
			from = 0;
		} else {
			from = rows * (page - 1);
		}
		if (page * rows > resultList.size()) {
			to = resultList.size();
		} else {
			to = page * rows;
		}
		
		Iterator<Mailbox> itr = resultList.iterator();
		User user;
		while (itr.hasNext()) {
			// 获取需要显示的内容
			Mailbox mailbox = (Mailbox) itr.next();
			user = ud.getUser(mailbox.getMbFrom());
			mailbox.setFromName(user.getUsername());
			user = ud.getUser(mailbox.getMbTo());
			mailbox.setToName(user.getUsername());
		}
		
		map.put("rows", resultList.subList(from, to));
		JSONObject resultObj = JSONObject.fromObject(map);
		return resultObj;
	}

	public Mailbox getInboxByMBId(int mbId) {
		MailboxDAO md = new MailboxDAOImpl();
		Mailbox mailbox = md.getMailboxById(mbId);
		return mailbox;
	}
	
}
