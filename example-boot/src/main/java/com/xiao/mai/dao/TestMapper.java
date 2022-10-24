package com.xiao.mai.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.xiao.mai.bean.TbUserBean;

@Mapper
public interface TestMapper {
	String test();
	List<Map<String,String>> seluser();
	List<TbUserBean> seluserpage();
	int insuser(Map<String, Object> map);
	int upduser(Map<String, Object> map);
	int deluser(Map<String, Object> map);
}
