package com.xiaomai.cloud.api.config;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.apache.tomcat.util.http.MimeHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.opslab.util.DateUtil;

/**
 * 
 * @ClassName: AuthFilter
 * @Description:(设置请求头Authorization值)
 * @author: wangfeng
 * @date: 2021年4月13日 下午7:06:47
 * 
 * @Copyright: 2021 Inc. All rights reserved.
 */
@Component
public class AuthFilter implements Filter {
	Logger logger = LoggerFactory.getLogger(AuthFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		logger.info("AuthFilter init ......"+DateUtil.currentDateTime());
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//吉林敦化; JLDH; jldh_chenxy; 陈晓艳
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcmduYW1lIjoi5ZCJ5p6X5pWm5YyW5oq95rC06JOE6IO95pyJ6ZmQ5YWs5Y-4IiwiaXRlbW5hbWUiOiLlkInmnpfmlabljJYiLCJpdGVtY29kZSI6IkpMREgiLCJvcmdjb2RlIjoiSkxESCIsImlzcyI6Ik1JTkciLCJkZXB0Y29kZSI6IkpMREhfQkdTIiwiaWF0IjoxNjE4MzA1ODc2LCJ1c2VyaWQiOiJqbGRoX2NoZW54eSIsIm9yZ2lkIjoiODY0YmJmMTJjYjQ5NGM0MTljOTc4NTRhNzk2NjllZGQiLCJ1c2VybmFtZSI6IumZiOaZk-iJsyIsImRlcHRuYW1lIjoi5Yqe5YWs5a6kIn0.7_dOmd0VgcbnQTNeSFNG9MCFZXhcUslanmLYinX5-Hg";
		//陕西镇安; SXZA; sxza_caij; 蔡军
		//token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcmduYW1lIjoi6ZmV6KW_6ZWH5a6J5oq95rC06JOE6IO95pyJ6ZmQ5YWs5Y-4IiwiaXRlbW5hbWUiOiLpmZXopb_plYflrokiLCJpdGVtY29kZSI6IlNYWkEiLCJvcmdjb2RlIjoiU1haQSIsImlzcyI6Ik1JTkciLCJkZXB0Y29kZSI6IlNYWkFfR1NMRCIsImlhdCI6MTYxODMwNzkyNSwidXNlcmlkIjoic3h6YV9jYWlqIiwib3JnaWQiOiJjZDY0ZWRkZTRjOTc0MmY5YTcxY2Y2NDhlODM0YmQ0NCIsInVzZXJuYW1lIjoi6JSh5YabIiwiZGVwdG5hbWUiOiLlhazlj7jpooblr7wifQ.bDWkrnsnJKD5sY59T_K65L8RhmiVgCGGsyFJ_kuCipE";	
		Class<? extends HttpServletRequest> requestClass = (Class<? extends HttpServletRequest>) request.getClass();
		try {
			Field request1 = requestClass.getDeclaredField("request");
			request1.setAccessible(true);
			Object o = request1.get(request);
			
			Field coyoteRequest = o.getClass().getDeclaredField("coyoteRequest");
			coyoteRequest.setAccessible(true);
			
			Object o1 = coyoteRequest.get(o);
			Field headers = o1.getClass().getDeclaredField("headers");
			headers.setAccessible(true);
			MimeHeaders o2 = (MimeHeaders) headers.get(o1);
			o2.addValue("Authorization-dev").setString(token);
			logger.info("----- AuthFilter START -----");
			//logger.warn(o2.toString());
			logger.info("----- AuthFilter END -----");
		} catch (Exception e) {
			e.printStackTrace();

		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		logger.info("AuthFilter destroy ......"+DateUtil.currentDateTime());
	}
}