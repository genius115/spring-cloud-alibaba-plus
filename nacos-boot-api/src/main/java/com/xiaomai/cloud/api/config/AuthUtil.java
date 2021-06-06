package com.xiaomai.cloud.api.config;

import javax.servlet.http.HttpServletRequest;

import com.xiaomai.cloud.api.comm.CodeConstants;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class AuthUtil {
	static ThreadLocal<String> local = new ThreadLocal<String>();
	
	public static String getVal(){		
		return local.get();
	}
	
	public static void setVal(String name){
		Thread th = Thread.currentThread();
		local.set(th.getId()+"---"+th.getName()+"["+name+"]");
	}
	
	private static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        return servletRequestAttributes == null ? null : servletRequestAttributes.getRequest();
    }
	public static String getToken() {
	   return getToken(getRequest());
    }

    public static String getToken(HttpServletRequest request) {
        if (request == null) {
            return null;
        } else {
            String token = request.getHeader(CodeConstants.HEADER_INFO);
            return token;
        }
    }
    public static Long getUserId() {
       return RandomUtils.nextLong();
    }
    public static String getUserName() {
        return "UserName["+getToken()+"]";
    }
    public static String getRealName() {
        return "RealName["+getToken()+"]";
    }
    
    
    public static String getItemcode() {
        return "Itemcode["+JwtUtil.getitemname(getToken())+"]";
     }
    public static String getUserid() {
        return "Userid["+JwtUtil.getuserid(getToken())+"]";
     }
    public static String getUsername() {
        return "Username["+JwtUtil.getusername(getToken())+"]";
     }
}
