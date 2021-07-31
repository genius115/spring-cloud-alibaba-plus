package com.cloud.boot.jbdc.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Madison
 * @date 2021/7/13
 */
@Mapper
public interface DemoMapper {
    Demo queryDemoById (Demo demo);
}
