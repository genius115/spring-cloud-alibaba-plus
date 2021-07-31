package com.xiaomai.sensitive;

import java.util.Arrays;
import java.util.List;

/**
 * @author Madison
 * @date 2021/7/21
 */
public class MyWordAllow implements IWordAllow {

    @Override
    public List<String> allow() {
        return Arrays.asList("五星红旗");
    }

}