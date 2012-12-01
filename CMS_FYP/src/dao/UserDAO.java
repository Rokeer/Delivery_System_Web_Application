package dao;

import entity.User;

public interface UserDAO {
	

	// 添加用户
	public void regUser(User user);

	// 编辑用户
	public void editUser(User user);

	// 根据userid获取用户
	public User getUser(int userId);

	// 根据任意字段获取用户
	public User getUserByKey(String key, String value);
}
