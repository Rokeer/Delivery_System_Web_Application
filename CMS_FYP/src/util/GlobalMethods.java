package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

public class GlobalMethods {
	public GlobalMethods() {

	}

	// ����MM-dd-yyyy��ʽ��ȡʱ��
	public String getDate() {
		Date date = new Date();
		Format fmt = new SimpleDateFormat("MM-dd-yyyy");
		return fmt.format(date);
	}

	// ��ȡ�ַ��MD5
	public String getMD5(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			while (hashtext.length() < 32) {
				hashtext = "0" + hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	// ����ʽ��ȡCache��UCID
	public String setCacheUCId(int uiCacheId) {
		String zeros = "00000";
		String str = Integer.toString(uiCacheId, 36).toUpperCase();
		str = zeros.substring(0, 5 - str.length()) + str;
		str = "UC" + str;
		return str;
	}

	// ���Cookie
	public void addCookie(String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        ServletActionContext.getResponse().addCookie(cookie);
	}

	// ����CookieʧЧ
	public void clearCookie(String name) {
		Cookie cookie = new Cookie(name, "0");
        cookie.setMaxAge(0);
        ServletActionContext.getResponse().addCookie(cookie);
	}
	
	// ����Cookie
	public void updateCookie (String name, String value) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(31536000);
        ServletActionContext.getResponse().addCookie(cookie);
	}

	// ��ȡCookie
	public String getCookie(String name) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Cookie[] cookies = request.getCookies();
		try {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	public String getParameterValue(String name) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Map parameters = request.getParameterMap();
		if (parameters.containsKey(name)) {
			return request.getParameter(name);
		}
		return null;
	}
	
	
	
	
}
