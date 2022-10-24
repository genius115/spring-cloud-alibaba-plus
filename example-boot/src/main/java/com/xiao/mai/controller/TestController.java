package com.xiao.mai.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xiao.mai.server.TestService;

import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.Api;

@Api(tags = "测试管理")
@RestController
@RequestMapping(value = "/test") 
public class TestController {
	
	@Autowired
	TestService analysisService;
	
	@GetMapping("/index")
	public Map<String,String> index(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("code", "200");
		map.put("msg", "V1.0-V2.0");
		map.put("data", new Date().toString());
		map.put("timestamp", DateUtil.formatDateTime(new Date()));
		return map;		
	}
	@GetMapping("/1")
	public String test1(){		
		return analysisService.test1();
	}
	
	@GetMapping("/2")
	public String test2(HttpServletRequest req){		
		return analysisService.test2(req.getParameter("i"));
	}
	
	@GetMapping("/3")
	public String test3(HttpServletRequest req){		
		return analysisService.test3(req.getParameter("i"));
	}
	
	@GetMapping("/4")
	public Object test4(@RequestParam int pageNum, @RequestParam int pageSize){
		/*
		int pageNum = Integer.valueOf(req.getParameter("pageNum"));
		int pageSize = Integer.valueOf(req.getParameter("pageSize"));
		*/
		return analysisService.test4(pageNum,pageSize);
	}

}
