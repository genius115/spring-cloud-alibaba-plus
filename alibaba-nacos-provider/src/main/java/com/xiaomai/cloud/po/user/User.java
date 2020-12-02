package com.xiaomai.cloud.po.user;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangfeng
 * @date 2020/11/21
 */
@ApiModel(value = "用户模型")
@Data
@TableName(value="USER")
public class User {

    @ApiModelProperty(value="主键", required= true, example = "123456789")
    @TableId(value = "id",type=IdType.ASSIGN_ID)
    private String id;

    @ApiModelProperty(value="姓名" ,required= true, example = "张三")
    private String name;

    @ApiModelProperty(value="年龄" ,required= true, example = "18")
    private Integer age;

    @ApiModelProperty(value="删除标记" ,required= true, example = "0")
    @TableLogic
    private Integer deleted;
}