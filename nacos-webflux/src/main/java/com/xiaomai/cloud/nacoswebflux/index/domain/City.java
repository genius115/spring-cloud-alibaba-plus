package com.xiaomai.cloud.nacoswebflux.index.domain;

/**
 * @author developer
 * @date 2021/1/12
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.io.Serializable;

/**
 * 城市实体类
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class City implements Serializable {

    /**
     * 城市编号
     * 该字段会映射到MongoDB的默认主键字段_id
     */
    @Id
    private Long id;

    /**
     * 省份编号
     */
    private Long provinceId;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 描述
     */
    private String description;
}

