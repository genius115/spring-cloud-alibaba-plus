package com.xiaomai.cloud.api.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xiaomai.cloud.api.comm.CodeConstants;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class AuthManagerFilter extends OncePerRequestFilter{
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
		// 放置用户信息
        CurrentUser user = new CurrentUser();
        user.setUserId(RandomUtils.nextLong());
        user.setUsername("UserContext:[UserName:"+request.getHeader(CodeConstants.HEADER_INFO)+"]");
        user.setName("UserContext:[Name:"+request.getHeader(CodeConstants.HEADER_INFO)+"]");
        user.setAccountitemcode(JwtUtil.getitemcode(request.getHeader(CodeConstants.HEADER_INFO)));
        user.setAccountuserid(JwtUtil.getuserid(request.getHeader(CodeConstants.HEADER_INFO)));
        user.setAccountusername(JwtUtil.getusername(request.getHeader(CodeConstants.HEADER_INFO)));

        UserContext.set(user);
        chain.doFilter(request, response);		
	}
}