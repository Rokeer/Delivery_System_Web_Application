package service.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import service.CMTransactionService;

import entity.CMTransaction;
import entity.Material;
import entity.User;
import entity.Product;
import dao.CMTransactionDAO;
import dao.MaterialDAO;
import dao.impl.CMTransactionDAOImpl;
import dao.impl.MaterialDAOImpl;
import dao.UserDAO;
import dao.impl.UserDAOImpl;
import dao.ProductDAO;
import dao.impl.ProductDAOImpl;

public class CMTransactionServiceImpl implements CMTransactionService {

	public boolean addCMTransaction(int cmtPId, int cmtAmount, int cmtClient) {
		CMTransaction cmtransaction = new CMTransaction();
		cmtransaction.setCmtAmount(cmtAmount);
		cmtransaction.setCmtClient(cmtClient);
		cmtransaction.setCmtPId(cmtPId);
		cmtransaction.setCmtStatus(0);
		cmtransaction.setCmtTime("00-00-0000");
		CMTransactionDAO cd = new CMTransactionDAOImpl();
		cd.addCMTransaction(cmtransaction);
		return true;
	}

	public boolean delCMTransaction(int cmtId) {
		CMTransaction cmtransaction = new CMTransaction();
		cmtransaction.setCmtId(cmtId);
		cmtransaction.setCmtAmount(0);
		cmtransaction.setCmtClient(0);
		cmtransaction.setCmtPId(0);
		cmtransaction.setCmtStatus(0);
		cmtransaction.setCmtTime("");
		CMTransactionDAO cd = new CMTransactionDAOImpl();
		cd.delCMTransaction(cmtransaction);
		return true;
	}

	public boolean editCMTransaction(int cmtId, int cmtPId, int cmtAmount,
			int cmtStatus, String cmtTime, int cmtClient) {
		CMTransaction cmtransaction = new CMTransaction();
		cmtransaction.setCmtId(cmtId);
		cmtransaction.setCmtAmount(cmtAmount);
		cmtransaction.setCmtClient(cmtClient);
		cmtransaction.setCmtPId(cmtPId);
		cmtransaction.setCmtStatus(cmtStatus);
		cmtransaction.setCmtTime(cmtTime);
		CMTransactionDAO cd = new CMTransactionDAOImpl();
		cd.updateCMTransaction(cmtransaction);
		return true;
	}

	public JSONObject getCMTransactionList() {
		// 持久化DAO
		CMTransactionDAO cd = new CMTransactionDAOImpl();
		UserDAO ud = new UserDAOImpl();
		ProductDAO pd = new ProductDAOImpl();
		MaterialDAO md = new MaterialDAOImpl();

		// 选择出所有cmtransaction
		List<CMTransaction> resultList = cd.getCMTransactions();
		Iterator<CMTransaction> itr = resultList.iterator();
		User user;
		Product product;
		while (itr.hasNext()) {
			// 获取需要显示的内容
			CMTransaction cmtransaction = (CMTransaction) itr.next();
			user = ud.getUser(cmtransaction.getCmtClient());
			cmtransaction.setClientName(user.getUsername());
			product = pd.getProductById(cmtransaction.getCmtPId());
			cmtransaction.setProductName(product.getpName());

			// 将需要的每种材料和数量分开
			String[] materialList = product.getpMaterial().split(";");
			int resultMakeAmount = 9999999;
			for (int i = 0; i < materialList.length; i++) {
				// 获取材料的id和数量
				int mId = Integer.parseInt(materialList[i].split(":")[0]);
				int mNeed = Integer.parseInt(materialList[i].split(":")[1]);
				Material material = md.getMaterialById(mId);
				// 计算最多可以制作多少此种商品
				if (material.getmAmount() / mNeed < resultMakeAmount) {
					resultMakeAmount = material.getmAmount() / mNeed;
				}
			}
			int resultTotal = resultMakeAmount + product.getpAmount();
			String tmp = "";

			// 查询库存情况
			if (cmtransaction.getCmtAmount() <= product.getpAmount()) {
				cmtransaction.setInventory("Ready");
			} else if (cmtransaction.getCmtAmount() <= resultTotal) {
				cmtransaction.setInventory("Inventory:" + product.getpAmount()
						+ ", "
						+ (cmtransaction.getCmtAmount() - product.getpAmount())
						+ " need to be made.");
			} else {
				tmp = "";
				for (int i = 0; i < materialList.length; i++) {
					// 获取材料的id和数量
					int mId = Integer.parseInt(materialList[i].split(":")[0]);
					int mNeed = Integer.parseInt(materialList[i].split(":")[1]);
					Material material = md.getMaterialById(mId);
					// 计算还需要多少原料
					int cmtNeed = mNeed * cmtransaction.getCmtAmount();
					if (material.getmAmount() < cmtNeed) {
						tmp = tmp + material.getmName() + ":"
								+ (cmtNeed - material.getmAmount()) + "; ";
					}
				}
				cmtransaction.setInventory("Need more materials. " + tmp);
			}

		}

		// 构造JSON对象
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", resultList.size());
		map.put("rows", resultList);
		JSONObject resultObj = JSONObject.fromObject(map);
		return resultObj;
	}

	public JSONObject getCMTransactionListByPage(int page, int rows) {
		int from;
		int to;
		// 持久化DAO
		CMTransactionDAO cd = new CMTransactionDAOImpl();
		UserDAO ud = new UserDAOImpl();
		ProductDAO pd = new ProductDAOImpl();
		MaterialDAO md = new MaterialDAOImpl();

		// 选择出所有cmtransaction
		List<CMTransaction> resultList = cd.getCMTransactions();
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

		List<CMTransaction> tmpList = resultList.subList(from, to);

		Iterator<CMTransaction> itr = tmpList.iterator();
		User user;
		Product product;
		while (itr.hasNext()) {
			// 获取需要显示的内容
			CMTransaction cmtransaction = (CMTransaction) itr.next();
			user = ud.getUser(cmtransaction.getCmtClient());
			cmtransaction.setClientName(user.getUsername());
			product = pd.getProductById(cmtransaction.getCmtPId());
			cmtransaction.setProductName(product.getpName());
			// 将需要的每种材料和数量分开
			String[] materialList = product.getpMaterial().split(";");
			int resultMakeAmount = 9999999;
			for (int i = 0; i < materialList.length; i++) {
				// 获取材料的id和数量
				int mId = Integer.parseInt(materialList[i].split(":")[0]);
				int mNeed = Integer.parseInt(materialList[i].split(":")[1]);
				Material material = md.getMaterialById(mId);
				// 计算最多可以制作多少此种商品
				if (material.getmAmount() / mNeed < resultMakeAmount) {
					resultMakeAmount = material.getmAmount() / mNeed;
				}
			}
			int resultTotal = resultMakeAmount + product.getpAmount();
			String tmp = "";

			// 查询库存情况
			if (cmtransaction.getCmtAmount() <= product.getpAmount()) {
				cmtransaction.setInventory("Ready");
			} else if (cmtransaction.getCmtAmount() <= resultTotal) {
				cmtransaction.setInventory("Inventory:" + product.getpAmount()
						+ ", "
						+ (cmtransaction.getCmtAmount() - product.getpAmount())
						+ " more need to be made.");
			} else {
				tmp = "Inventory:" + product.getpAmount() + ", Still need ";
				for (int i = 0; i < materialList.length; i++) {
					// 获取材料的id和数量
					int mId = Integer.parseInt(materialList[i].split(":")[0]);
					int mNeed = Integer.parseInt(materialList[i].split(":")[1]);
					Material material = md.getMaterialById(mId);
					// 计算还需要多少原料
					int cmtNeed = mNeed * (cmtransaction.getCmtAmount() - product.getpAmount());
					if (material.getmAmount() < cmtNeed) {
						tmp = tmp + material.getmName() + ":"
								+ (cmtNeed - material.getmAmount()) + "; ";
					}
				}
				cmtransaction.setInventory("Need more materials. " + tmp);
			}
		}

		map.put("rows", tmpList);
		JSONObject resultObj = JSONObject.fromObject(map);
		return resultObj;
	}

	public boolean editCMTransactionStatus(String cmtId, String cmtStatus) {
		CMTransactionDAO cd = new CMTransactionDAOImpl();
		CMTransaction cmtransaction = cd.getCMTransactionById(Integer.parseInt(cmtId));
		cmtransaction.setCmtStatus(Integer.parseInt(cmtStatus));
		cd.updateCMTransaction(cmtransaction);
		return true;
	}

	public JSONObject getCMTransactionListByUser(int page, int rows, int cmtClient) {
		int from;
		int to;
		// 持久化DAO
		CMTransactionDAO cd = new CMTransactionDAOImpl();
		UserDAO ud = new UserDAOImpl();
		ProductDAO pd = new ProductDAOImpl();
		MaterialDAO md = new MaterialDAOImpl();

		// 选择出所有cmtransaction
		List<CMTransaction> resultList = cd.getCMTransactionsByUser(cmtClient);
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

		List<CMTransaction> tmpList = resultList.subList(from, to);

		Iterator<CMTransaction> itr = tmpList.iterator();
		User user;
		Product product;
		while (itr.hasNext()) {
			// 获取需要显示的内容
			CMTransaction cmtransaction = (CMTransaction) itr.next();
			user = ud.getUser(cmtransaction.getCmtClient());
			cmtransaction.setClientName(user.getUsername());
			product = pd.getProductById(cmtransaction.getCmtPId());
			cmtransaction.setProductName(product.getpName());
			// 将需要的每种材料和数量分开
			String[] materialList = product.getpMaterial().split(";");
			int resultMakeAmount = 9999999;
			for (int i = 0; i < materialList.length; i++) {
				// 获取材料的id和数量
				int mId = Integer.parseInt(materialList[i].split(":")[0]);
				int mNeed = Integer.parseInt(materialList[i].split(":")[1]);
				Material material = md.getMaterialById(mId);
				// 计算最多可以制作多少此种商品
				if (material.getmAmount() / mNeed < resultMakeAmount) {
					resultMakeAmount = material.getmAmount() / mNeed;
				}
			}
			int resultTotal = resultMakeAmount + product.getpAmount();
			String tmp = "";

			// 查询库存情况
			if (cmtransaction.getCmtAmount() <= product.getpAmount()) {
				cmtransaction.setInventory("Ready");
			} else if (cmtransaction.getCmtAmount() <= resultTotal) {
				cmtransaction.setInventory("Inventory:" + product.getpAmount()
						+ ", "
						+ (cmtransaction.getCmtAmount() - product.getpAmount())
						+ " more need to be made.");
			} else {
				tmp = "Inventory:" + product.getpAmount() + ", Still need ";
				for (int i = 0; i < materialList.length; i++) {
					// 获取材料的id和数量
					int mId = Integer.parseInt(materialList[i].split(":")[0]);
					int mNeed = Integer.parseInt(materialList[i].split(":")[1]);
					Material material = md.getMaterialById(mId);
					// 计算还需要多少原料
					int cmtNeed = mNeed * (cmtransaction.getCmtAmount() - product.getpAmount());
					if (material.getmAmount() < cmtNeed) {
						tmp = tmp + material.getmName() + ":"
								+ (cmtNeed - material.getmAmount()) + "; ";
					}
				}
				cmtransaction.setInventory("Need more materials. " + tmp);
			}
		}

		map.put("rows", tmpList);
		JSONObject resultObj = JSONObject.fromObject(map);
		return resultObj;
	}

}
