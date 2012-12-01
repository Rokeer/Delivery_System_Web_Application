package interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

@SuppressWarnings("serial")
public class LoginCheck  extends AbstractInterceptor{
	public String intercept (ActionInvocation ai) throws Exception {
		Map session = ai.getInvocationContext().getSession();
		String ref = ActionContext.getContext().getName();
		if(session.get("uid") != null && !session.get("uid").equals("")) {
			return ai.invoke();
		} else {
			return "login";
		}
	}
	

}
