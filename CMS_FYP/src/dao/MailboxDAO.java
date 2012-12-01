package dao;

import java.util.List;

import entity.Mailbox;

public interface MailboxDAO {
	// 获取邮件列表
	public List<Mailbox> getInboxByuId(int uId);
	public List<Mailbox> getSentByuId(int uId);
	
	// 获取邮件实例
	public Mailbox getMailboxById(int mbId);
	
	public Mailbox getMailboxByKey(String key, String value);

	// 添加邮件
	public String addMailbox(Mailbox mailbox);

	// 删除邮件
	public String delMailbox(Mailbox mailbox);

	// 修改邮件
	public String updateMailbox(Mailbox mailbox);
	
}
