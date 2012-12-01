package service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import dao.MOrderDAO;
import dao.MaterialDAO;
import dao.SBidDAO;
import dao.UserDAO;
import dao.impl.MOrderDAOImpl;
import dao.impl.MaterialDAOImpl;
import dao.impl.SBidDAOImpl;
import dao.impl.UserDAOImpl;
import entity.MOrder;
import entity.Material;
import entity.SBid;
import entity.User;
import net.sf.json.JSONObject;
import service.SBidService;

public class SBidServiceImpl implements SBidService{

	public boolean addSBid(int sbMOId, int sbBid, int sbSupplier) {
		SBid sbid = new SBid();
		SBidDAO sd = new SBidDAOImpl();
		sbid.setSbBid(sbBid);
		//sbid.setSbId(sbId);
		sbid.setSbMOId(sbMOId);
		sbid.setSbStatus(0);
		sbid.setSbSupplier(sbSupplier);
		sd.addSBid(sbid);
		return true;
	}

	public boolean delSBid(int sbId) {
		SBid sbid = new SBid();
		SBidDAO sd = new SBidDAOImpl();
		sbid.setSbBid(0);
		sbid.setSbId(sbId);
		sbid.setSbMOId(0);
		sbid.setSbStatus(0);
		sbid.setSbSupplier(0);
		sd.delSBid(sbid);
		return true;
	}

	public boolean editSBid(int sbId, int sbMOId, int sbBid, int sbStatus,
			int sbSupplier) {
		SBid sbid = new SBid();
		SBidDAO sd = new SBidDAOImpl();
		sbid.setSbBid(sbBid);
		sbid.setSbId(sbId);
		sbid.setSbMOId(sbMOId);
		sbid.setSbStatus(sbStatus);
		sbid.setSbSupplier(sbSupplier);
		sd.updateSBid(sbid);
		return true;
	}

	public boolean editSBidStatus(String sbId, String sbStatus) {
		SBidDAO sd = new SBidDAOImpl();
		SBid sbid = sd.getSBidById(Integer.parseInt(sbId));
		sbid.setSbStatus(Integer.parseInt(sbStatus));
		sd.updateSBid(sbid);
		return true;
	}

	public JSONObject getSBidListByPageAndMOId(int page, int rows, int moId) {
		int from;
		int to;
		// 持久化DAO
		SBidDAO sd = new SBidDAOImpl();
		MOrderDAO mod = new MOrderDAOImpl();
		UserDAO ud = new UserDAOImpl();
		MaterialDAO md = new MaterialDAOImpl();

		// 选择出所有bid
		List<SBid> resultList = sd.getSBidsByMOId(moId);
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

		List<SBid> tmpList = resultList.subList(from, to);
		Iterator<SBid> itr = tmpList.iterator();
		User user;
		Material material;
		MOrder morder;
		while (itr.hasNext()) {
			// 获取需要显示的内容
			SBid sbid = (SBid) itr.next();
			user = ud.getUser(sbid.getSbSupplier());
			sbid.setSupplierName(user.getUsername());
			morder = mod.getMOrderById(sbid.getSbMOId());
			material = md.getMaterialById(morder.getMoMId());
			sbid.setMoDisplay("Order ID:"+morder.getMoId()+", "+material.getmName()+":"+material.getmAmount());
		}
		map.put("rows", tmpList);
		JSONObject resultObj = JSONObject.fromObject(map);
		return resultObj;
	}

	public JSONObject getSBidListByMOId(int moId) {
		// 持久化DAO
		SBidDAO sd = new SBidDAOImpl();
		MOrderDAO mod = new MOrderDAOImpl();
		UserDAO ud = new UserDAOImpl();
		MaterialDAO md = new MaterialDAOImpl();

		// 选择出所有bid
		List<SBid> resultList = sd.getSBidsByMOId(moId);
		// 构造json对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", resultList.size());

		
		Iterator<SBid> itr = resultList.iterator();
		User user;
		Material material;
		MOrder morder;
		while (itr.hasNext()) {
			// 获取需要显示的内容
			SBid sbid = (SBid) itr.next();
			user = ud.getUser(sbid.getSbSupplier());
			sbid.setSupplierName(user.getUsername());
			morder = mod.getMOrderById(sbid.getSbMOId());
			material = md.getMaterialById(morder.getMoMId());
			sbid.setMoDisplay("Order ID:"+morder.getMoId()+", "+material.getmName()+":"+material.getmAmount());
		}
		map.put("rows", resultList);
		JSONObject resultObj = JSONObject.fromObject(map);
		return resultObj;
	}

	public boolean acceptBid(int sbId) {
		SBidDAO sd = new SBidDAOImpl();
		MaterialDAO md = new MaterialDAOImpl();
		MOrderDAO mod = new MOrderDAOImpl();
		SBid sbid = sd.getSBidById(sbId);
		sbid.setSbStatus(1);
		sd.updateSBid(sbid);
		MOrder morder = mod.getMOrderById(sbid.getSbMOId());
		morder.setMoSupplier(sbid.getSbSupplier());
		morder.setMoStatus(1);
		mod.updateMOrder(morder);
		Material material = md.getMaterialById(morder.getMoMId());
		material.setmAmount(material.getmAmount()+morder.getMoAmount());
		md.updateMaterial(material);
		List<SBid> resultList = sd.getSBids();
		Iterator<SBid> itr = resultList.iterator();
		while (itr.hasNext()) {
			// 获取需要显示的内容
			SBid sbidItr = (SBid) itr.next();
			if(sbidItr.getSbMOId()==sbid.getSbMOId()&&sbidItr.getSbId()!=sbid.getSbId()){
				if(sbidItr.getSbStatus()==1){
					material.setmAmount(material.getmAmount()-morder.getMoAmount());
					md.updateMaterial(material);
				}
				sbidItr.setSbStatus(0);
				sd.updateSBid(sbidItr);
			}
		}
		
		return true;
	}

	public JSONObject getSBidListByPageAndSupplier(int page, int rows,
			int supplier) {
		int from;
		int to;
		// 持久化DAO
		SBidDAO sd = new SBidDAOImpl();
		MOrderDAO mod = new MOrderDAOImpl();
		UserDAO ud = new UserDAOImpl();
		MaterialDAO md = new MaterialDAOImpl();

		// 选择出所有bid
		List<SBid> resultList = sd.getSBidsBySupplier(supplier);
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

		List<SBid> tmpList = resultList.subList(from, to);
		Iterator<SBid> itr = tmpList.iterator();
		User user;
		Material material;
		MOrder morder;
		while (itr.hasNext()) {
			// 获取需要显示的内容
			SBid sbid = (SBid) itr.next();
			user = ud.getUser(sbid.getSbSupplier());
			sbid.setSupplierName(user.getUsername());
			morder = mod.getMOrderById(sbid.getSbMOId());
			material = md.getMaterialById(morder.getMoMId());
			sbid.setMoDisplay("Order ID:"+morder.getMoId()+", "+material.getmName()+":"+material.getmAmount());
		}
		map.put("rows", tmpList);
		JSONObject resultObj = JSONObject.fromObject(map);
		return resultObj;
	}

}
