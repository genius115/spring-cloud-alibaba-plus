package com.xiaomai.cloud.po.order;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import okhttp3.Interceptor;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * MyBatis-plus 实体类
 * 注意：
 *     创建日期、修改日期、乐观锁、逻辑删除不提倡手动增加或者删除
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

    /**
     * 逻辑删除
     */
    @ApiModelProperty(value="deleted" ,required= true, example = "0")
    @TableLogic
    private Integer deleted;

    /**
     * 创建日期 fill标记为填充字段 默认不填充    DEFAULT, INSERT, UPDATE, INSERT_UPDATE;
     */
    @ApiModelProperty(value="gmtCreate" )
    @TableField(fill = FieldFill.INSERT)
    private Date gmtCreate;

    /**
     * 修改日期 fill标记为填充字段
     */
    @ApiModelProperty(value="gmtModified")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;

}
