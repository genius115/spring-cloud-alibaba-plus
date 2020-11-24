package com.xiaomai.cloud.po.order;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author wangfeng
 * @date 2020/11/21
 */
@ApiModel(value = "支付模型")
@Data
@TableName(value="PAYMENT")
public class Payment {
    @ApiModelProperty(value="id" ,required= true,example = "123")
    @TableId
    private Integer id;
    @ApiModelProperty(value="serial" ,required= true,example = "默认模块")
    private String serial;
}
