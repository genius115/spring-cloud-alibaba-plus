package com.xiaomai.cloud.po.postgres;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Value;

/**
 * @author developer
 * @date 2020/12/31
 */
@ApiModel(value = "书籍模型")
@Data
@TableName(value="book")
public class Book {

    @ApiModelProperty(value = "主键",position = 1)
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "编号",position = 2)
    private String code;

    @ApiModelProperty(value = "名称",position = 3)
    private String name;
}
