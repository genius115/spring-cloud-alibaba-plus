package com.xiaomai.cloud.beans.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author wangfeng
 * @date 2020/11/20
 */
@ApiModel(value = "用户模型")
public class UserEntity {
    @ApiModelProperty(value="id" ,required= true,example = "123")
    private Integer id;
    @ApiModelProperty(value="用户姓名" ,required=true,example = "郑钦锋")
    private String name;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DemoDoctor [id=" + id + ", name=" + name + "]";
    }

}
