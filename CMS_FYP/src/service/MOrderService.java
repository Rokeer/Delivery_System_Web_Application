package service;

import net.sf.json.JSONObject;

public interface MOrderService {
	public JSONObject getMOrderListByPage(int page, int rows);
	public JSONObject getMOrderListByPageWithoutSelection(int page, int rows);
	public boolean addMOrder(int moMId, int moAmount);
	public boolean editMorder(int moId, int moMId, int moAmount, int moStatus, String moTime, int moSupplier);
	public boolean editMOrderStatus(String moId, String moStatus);
	public boolean delMOrder(int moId);
}
