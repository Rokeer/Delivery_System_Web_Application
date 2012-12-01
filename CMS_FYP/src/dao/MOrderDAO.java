package dao;

import java.util.List;

import entity.MOrder;


public interface MOrderDAO {
	// 获取经理订单列表
	public List<MOrder> getMOrders();
	public List<MOrder> getMOrdersWithoutSelection();
	
	// 获取经理订单实例
	public MOrder getMOrderById(int morderId);
	
	public MOrder getMOrderByKey(String key, String value);

	// 添加经理订单
	public String addMOrder(MOrder morder);

	// 删除经理订单
	public String delMOrder(MOrder morder);

	// 修改经理订单
	public String updateMOrder(MOrder morder);
	
	// 检查是否存在
	public boolean checkHave(String key, String value);
}
