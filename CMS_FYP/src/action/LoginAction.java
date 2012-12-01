package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import dao.UserDAO;
import dao.impl.UserDAOImpl;

import entity.User;

public class LoginAction extends ActionSupport {
	/**
	 * @author Seven
	 */
	private static final long serialVersionUID = -6739057561109539019L;
	User user;
	String username;
	String password;
	UserDAO ud;

	public String execute() {
		ud = new UserDAOImpl();
		user = ud.getUserByKey("username", username);
		if (user == null || !password.equals(user.getPassword())) {
			return LOGIN;
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.setAttribute("userId", Integer.toString(user.getUserId()));
		session.setAttribute("userName", user.getUsername());
		session.setAttribute("userPermission", user.getPermission());
		switch (user.getPermission()) {
		case 0:
			return "manager";
		case 1:
			return "client";
		case 2:
			return "supplier";
		default:
			return ERROR;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDAO getUd() {
		return ud;
	}

	public void setUd(UserDAO ud) {
		this.ud = ud;
	}

}
