package dao;

import java.util.List;

import entity.CMTransaction;


public interface CMTransactionDAO {
	// 获取客户订单列表
	public List<CMTransaction> getCMTransactions();
	public List<CMTransaction> getCMTransactionsByUser(int cmtClient);
	
	// 获取客户订单实例
	public CMTransaction getCMTransactionById(int cmtransactionId);
	
	public CMTransaction getCMTransactionByKey(String key, String value);

	// 添加客户订单
	public String addCMTransaction(CMTransaction cmtransaction);

	// 删除客户订单
	public String delCMTransaction(CMTransaction cmtransaction);

	// 修改客户订单
	public String updateCMTransaction(CMTransaction cmtransaction);
	
	// 检查是否存在
	public boolean checkHave(String key, String value);
}
