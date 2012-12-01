package dao;

import java.util.List;

import entity.SBid;

public interface SBidDAO {
	// 获取竞价列表
	public List<SBid> getSBids();
	public List<SBid> getSBidsByMOId(int moId);
	public List<SBid> getSBidsBySupplier(int supplier);
	
	
	// 获取竞价实例
	public SBid getSBidById(int sbId);
	
	public SBid getSBidByKey(String key, String value);

	// 添加竞价
	public String addSBid(SBid sbid);

	// 删除竞价
	public String delSBid(SBid sbid);

	// 修改竞价
	public String updateSBid(SBid sbid);
	
	// 检查是否存在
	public boolean checkHave(String key, String value);
}
