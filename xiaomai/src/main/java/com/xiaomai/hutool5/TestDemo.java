package com.xiaomai.hutool5;

import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author Madison
 * @date 2021/7/5
 */
@Slf4j
public class TestDemo {
    
    @Test
    public void test(){
        // 5***************1X
        log.info("数据脱敏{}",DesensitizedUtil.idCardNum("51343620000320711X", 1, 2));

        // 180****1999
        log.info("数据脱敏{}",DesensitizedUtil.mobilePhone("18049531999"));

        String num = "123456789101112";
        log.info("数据脱敏{}", StrUtil.hide(num, 3, num.length() - 4));
    }
}
