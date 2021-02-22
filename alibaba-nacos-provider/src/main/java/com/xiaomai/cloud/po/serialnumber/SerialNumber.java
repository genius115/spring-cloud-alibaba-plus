package com.xiaomai.cloud.po.serialnumber;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Madison
 * @date 2021/2/1
 */
@ApiModel(value = "序列号")
@Data
@TableName(value="sys_serial_number")
public class SerialNumber {

    @ApiModelProperty(value = "主键",position = 1)
    @TableId(value = "id",type = IdType.ASSIGN_UUID)
    private String id;

    @ApiModelProperty(value = "名称",position = 2)
    private String module_name;

    @ApiModelProperty(value = "编号",position = 3)
    private String module_code;

}
