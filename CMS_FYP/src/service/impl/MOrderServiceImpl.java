package service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.MOrderDAO;
import dao.MaterialDAO;
import dao.UserDAO;
import dao.impl.MOrderDAOImpl;
import dao.impl.MaterialDAOImpl;
import dao.impl.UserDAOImpl;
import entity.MOrder;
import entity.Material;
import entity.User;
import net.sf.json.JSONObject;
import service.MOrderService;

public class MOrderServiceImpl implements MOrderService{

	public boolean addMOrder(int moMId, int moAmount) {
		MOrder morder = new MOrder();
		MOrderDAO md = new MOrderDAOImpl();
		morder.setMoAmount(moAmount);
		morder.setMoMId(moMId);
		morder.setMoStatus(0);
		morder.setMoSupplier(0);
		md.addMOrder(morder);
		morder.setMoTime("00-00-0000");
		return true;
	}

	public boolean delMOrder(int moId) {
		MOrder morder = new MOrder();
		MOrderDAO md = new MOrderDAOImpl();
		morder.setMoAmount(0);
		morder.setMoId(moId);
		morder.setMoMId(0);
		morder.setMoStatus(0);
		morder.setMoSupplier(0);
		morder.setMoTime("");
		md.delMOrder(morder);
		return true;
	}

	public boolean editMOrderStatus(String moId, String moStatus) {
		MOrderDAO md = new MOrderDAOImpl();
		MOrder morder = md.getMOrderById(Integer.parseInt(moId));
		morder.setMoStatus(Integer.parseInt(moStatus));
		md.updateMOrder(morder);
		return true;
	}

	public boolean editMorder(int moId, int moMId, int moAmount, int moStatus,
			String moTime, int moSupplier) {
		MOrder morder = new MOrder();
		MOrderDAO md = new MOrderDAOImpl();
		morder.setMoAmount(moAmount);
		morder.setMoId(moId);
		morder.setMoMId(moMId);
		morder.setMoStatus(moStatus);
		morder.setMoSupplier(moSupplier);
		morder.setMoTime(moTime);
		md.updateMOrder(morder);
		return true;
	}

	public JSONObject getMOrderListByPage(int page, int rows) {
		int from;
		int to;
		// 持久化DAO
		MOrderDAO mod = new MOrderDAOImpl();
		UserDAO ud = new UserDAOImpl();
		MaterialDAO md = new MaterialDAOImpl();

		// 选择出所有cmtransaction
		List<MOrder> resultList = mod.getMOrders();
		// 构造json对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", resultList.size());

		// 获取需要的List
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

		List<MOrder> tmpList = resultList.subList(from, to);

		Iterator<MOrder> itr = tmpList.iterator();
		User user;
		Material material;
		while (itr.hasNext()) {
			// 获取需要显示的内容
			MOrder morder = (MOrder) itr.next();
			if (morder.getMoSupplier()>0){
				user = ud.getUser(morder.getMoSupplier());
			} else {
				user = new User();
				user.setUsername("TBD");
			}
			morder.setSupplierName(user.getUsername());

			material = md.getMaterialById(morder.getMoMId());
			morder.setMaterialName(material.getmName());
		}
		map.put("rows", tmpList);
		JSONObject resultObj = JSONObject.fromObject(map);
		return resultObj;
	}

	public JSONObject getMOrderListByPageWithoutSelection(int page, int rows) {
		int from;
		int to;
		// 持久化DAO
		MOrderDAO mod = new MOrderDAOImpl();
		UserDAO ud = new UserDAOImpl();
		MaterialDAO md = new MaterialDAOImpl();

		// 选择出所有cmtransaction
		List<MOrder> resultList = mod.getMOrdersWithoutSelection();
		// 构造json对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", resultList.size());

		// 获取需要的List
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

		List<MOrder> tmpList = resultList.subList(from, to);

		Iterator<MOrder> itr = tmpList.iterator();
		User user;
		Material material;
		while (itr.hasNext()) {
			// 获取需要显示的内容
			MOrder morder = (MOrder) itr.next();
			if (morder.getMoSupplier()>0){
				user = ud.getUser(morder.getMoSupplier());
			} else {
				user = new User();
				user.setUsername("TBD");
			}
			morder.setSupplierName(user.getUsername());

			material = md.getMaterialById(morder.getMoMId());
			morder.setMaterialName(material.getmName());
		}
		map.put("rows", tmpList);
		JSONObject resultObj = JSONObject.fromObject(map);
		return resultObj;
	}

}
