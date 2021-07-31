package com.xiaomai.sensitive;

import com.github.houbb.sensitive.word.bs.SensitiveWordBs;
import com.github.houbb.sensitive.word.support.allow.WordAllows;
import com.github.houbb.sensitive.word.support.deny.WordDenys;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Madison
 * @date 2021/7/21
 */
public class TestSensitiveWord {
    
    @Test
    public void Test01() {
        final String text = "五星红旗迎风飘扬，画像屹立在天安门前。";
        Assert.assertTrue(SensitiveWordBs.newInstance().contains(text));


        IWordAllow myDdWordAllow = new MyWordAllow();
        SensitiveWordBs sensitiveWordBs = SensitiveWordBs.newInstance()
                .wordAllow(WordAllows.chains(WordAllows.system()))
                .wordDeny(WordDenys.chains(WordDenys.system()))
                // 各种其他配置
                .init();
        
    }
}
