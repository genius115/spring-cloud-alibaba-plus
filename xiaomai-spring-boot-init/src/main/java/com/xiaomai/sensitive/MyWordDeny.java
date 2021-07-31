package com.xiaomai.sensitive;

import java.util.Arrays;
import java.util.List;

/**
 * @author Madison
 * @date 2021/7/21
 */
public class MyWordDeny implements IWordDeny {

    @Override
    public List<String> deny() {
        return Arrays.asList("我的自定义敏感词");
    }

}
