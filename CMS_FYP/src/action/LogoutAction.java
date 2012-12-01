package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import util.GlobalMethods;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LogoutAction extends ActionSupport{
	public String execute()
	{
		GlobalMethods gm = new GlobalMethods();
		gm.clearCookie("userId");
		gm.clearCookie("loginToken");
		gm.clearCookie("loginSeries");

		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();

		// ��userIdд��session���Ա�������û���¼���ȡ�û�id
		session.setAttribute("uid", "");
		session.setAttribute("uname", "");
		return SUCCESS;
	}
}
