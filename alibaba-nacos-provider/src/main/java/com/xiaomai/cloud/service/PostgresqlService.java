package com.xiaomai.cloud.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author developer
 * @date 2020/12/31
 */
@Service
@DS("postgresdb")
public class PostgresqlService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> selectAllBook() {
        return  jdbcTemplate.queryForList("select * from book");
    }
}
