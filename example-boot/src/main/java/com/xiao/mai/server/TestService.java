package com.xiao.mai.server;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.autoconfigure.PageHelperProperties;
import com.xiao.mai.bean.TbUserBean;
import com.xiao.mai.dao.TestMapper;

import cn.hutool.core.date.DateUtil;



@Service
public class TestService {
    @Autowired
    private TestMapper testMapper;
    
    public String  test1() {    	
    	return testMapper.test();
    }
    
    @Transactional
    public String  test2(String i) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("id", 5);
    	testMapper.deluser(map);
    	List<Map<String,String>> result = testMapper.seluser();
    	System.out.println("删除后*****");
    	for(Map<String,String> row: result){
    		System.out.println(row);
    	}
    	int a = 1/Integer.parseInt(i);
    	map.put("name", "zhangsan"+System.currentTimeMillis());
    	testMapper.insuser(map); 
    	result = testMapper.seluser();
    	System.out.println("新增后*****");
    	for(Map<String,String> row: result){
    		System.out.println(result);
    	}
    	return "SUCESS";
    }
    
    public String  test3(String i) {
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("id", 6);
    	testMapper.deluser(map);
    	List<Map<String,String>> result = testMapper.seluser();
    	System.out.println("删除后*****");
    	for(Map<String,String> row: result){
    		System.out.println(row);
    	}
    	int a = 1/Integer.parseInt(i);
    	map.put("name", "lisi"+System.currentTimeMillis());
    	testMapper.insuser(map);  
    	result = testMapper.seluser();
    	System.out.println("新增后*****");
    	for(Map<String,String> row: result){
    		System.out.println(row);
    	}
    	return "SUCESS";
    }
    
    public Object test4(int pageNum, int pageSize) {
    	PageHelper.startPage(pageNum,pageSize);//开启设置分页
    	List<TbUserBean> users = testMapper.seluserpage();
    	PageInfo<TbUserBean> pageInfo = new PageInfo<TbUserBean>(users);//类型转化为PageInfo
    	System.out.println(pageInfo);
    	return pageInfo;
    }
}
