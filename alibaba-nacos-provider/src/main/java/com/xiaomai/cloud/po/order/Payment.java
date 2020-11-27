package com.xiaomai.cloud.po.order;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author wangfeng
 * @date 2020/11/21
 */
@ApiModel(value = "支付模型")
@Data
@TableName(value="PAYMENT")
public class Payment {

    @ApiModelProperty(value="id",required= true, example = "123")
    // @TableId(value = "id",type=IdType.ASSIGN_ID)
    @TableId(type = IdType.AUTO)
    private Integer id;

    @NotEmpty(message = "serial不能为空")
    @ApiModelProperty(value="serial" ,required= true, example = "默认模块")
    private String serial;

    @ApiModelProperty(value="deleted" ,required= true, example = "0")
    @TableLogic
    private Integer deleted;
}
