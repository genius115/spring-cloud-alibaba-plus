package com.cloud.boot.jbdc.service;

import com.cloud.boot.jbdc.dao.Demo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Madison
 * @date 2021/7/13
 */
@Service
public class JdbcService {
    @Autowired
    //@Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate;
    
    public String save(Demo demo,JdbcTemplate jdbcTemplate){
        int result = jdbcTemplate.update("INSER INTO DEMO (id,name,age) VALUES (? ,?,?)",
                demo.getId(),demo.getName(),demo.getId());
        return  ""; 
    }
    
    public List<Map<String,Object>> get(){
        List<Map<String,Object>> list =  this.jdbcTemplate.queryForList("SELECT * FROM DEMO");
        return list;
    }
    
    public List<Map<String,Object>> get(JdbcTemplate jdbcTemplate){
        List<Map<String,Object>> list =  jdbcTemplate.queryForList("SELECT * FROM DEMO");
        return list;
    }
}
