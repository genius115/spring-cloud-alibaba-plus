package com.xiaomai.cloud.mapper.serialnumber;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaomai.cloud.po.serialnumber.SerialNumber;

/**
 * @author Madison
 * @date 2021/2/1
 */
@DS("master")
public interface GroupMapper extends BaseMapper<SerialNumber> {
    int getMaxGroupNum();
}
