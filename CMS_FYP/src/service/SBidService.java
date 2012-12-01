package service;

import net.sf.json.JSONObject;

public interface SBidService {
	public JSONObject getSBidListByPageAndMOId(int page, int rows, int moId);
	public JSONObject getSBidListByPageAndSupplier(int page, int rows, int supplier);
	public JSONObject getSBidListByMOId(int moId);
	public boolean addSBid(int sbMOId, int sbBid, int sbSupplier);
	public boolean editSBid(int sbId, int sbMOId, int sbBid, int sbStatus, int sbSupplier);
	public boolean editSBidStatus(String sbId, String sbStatus);
	public boolean delSBid(int sbId);
	public boolean acceptBid(int sbId);
}
