package com.xiaomai.cloud.nacoswebflux.index.service;

import com.xiaomai.cloud.nacoswebflux.index.controller.CityCacheController;
import com.xiaomai.cloud.nacoswebflux.index.domain.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 *
 * spring cache 的默认缓存
 * @author developer
 * @date 2021/1/12
 */
// @Service
public class CityCacheService {

    private ConcurrentMap<Long, City> cityDB = new ConcurrentHashMap<>();

    public static final Logger log = LoggerFactory.getLogger(CityCacheController.class);

    /**
     *
     * @param id
     * @return
     */
    @Cacheable(value = {"city"}, /*keyGenerator = "myKeyGenerator",*/key = "#id",condition = "#a0>=1",unless = "#a0!=2")
    public City getCity(Integer id) {
        City city = cityDB.get(id);
        log.info("查询主键为{}的城市数据",id);
        return city;
    }

    /**
     *  @CachePut：既调用方法，又更新缓存数据；同步更新缓存
     *  修改了数据，同时更新缓存
     */
    @CachePut(value = {"city"}, key = "#result.id")
    public City updateCity(City city){
        cityDB.put(city.getId(),city);
        log.info("更新主键为{}的城市数据",city.getId());
        return city;
    }

    @CacheEvict(value = {"city"}, beforeInvocation = true, key="#id")
    public void deleteCity(Integer id){
        // employeeMapper.deleteEmpById(id);
        //int i = 10/0;
        log.info("删除主键为{}的城市数据",id);
        cityDB.remove(Long.valueOf(id));
    }

    @Caching(
            cacheable = {
                    @Cacheable(/*value={"city"},*/key = "#provinceId")
            },
            put = {
                    @CachePut(/*value={"city"},*/key = "#result.id"),
                    @CachePut(/*value={"city"},*/key = "#result.cityName")
            }
    )
    public City getCityByProvinceId(Integer provinceId){
        return cityDB.get(provinceId);
    }
}
