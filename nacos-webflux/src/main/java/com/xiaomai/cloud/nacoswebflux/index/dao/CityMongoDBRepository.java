package com.xiaomai.cloud.nacoswebflux.index.dao;

import com.xiaomai.cloud.nacoswebflux.index.domain.City;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

/**
 * @author developer
 * @date 2021/1/12
 */
public interface CityMongoDBRepository extends ReactiveMongoRepository<City,Long> {

}
