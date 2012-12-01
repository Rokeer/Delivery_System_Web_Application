package service;

import net.sf.json.JSONObject;

public interface CMTransactionService {
	public JSONObject getCMTransactionListByPage(int page, int rows);
	public JSONObject getCMTransactionListByUser(int page, int rows, int cmtClient);
	public JSONObject getCMTransactionList();
	public boolean addCMTransaction(int cmtPId, int cmtAmount, int cmtClient);
	public boolean editCMTransaction(int cmtId, int cmtPId, int cmtAmount, int cmtStatus, String cmtTime, int cmtClient);
	public boolean editCMTransactionStatus(String cmtId, String cmtStatus);
	public boolean delCMTransaction(int cmtId);
}
