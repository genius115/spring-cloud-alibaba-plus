package com.xiaomai.boot.myjson.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 配置类（类名一般为模块名+properties）
 *   定义需要的配置信息及默认配置项，并指明关联配置文件的配置项前缀
 * 可以把相同前缀的配置信息通过配置项名称映射成实体类的属性中
 *
 * nobody.json为starter使用者通过yml配置文件动态修改属性值的变量名前缀
 * @author Madison
 * @date 2021/3/2
 */
@ConfigurationProperties(prefix = "nobody.json")
public class MyJsonProperties {

    //Starer使用者没有配置文件中配置prefixName属性的默认值
    public static final String DEFAULT_PREFIX_NAME = "@";

    //Starer使用者没有配置文件中配置prefixName属性的默认值
    public static final String DEFAULT_SUFFIX_NAME = "#";

    //前缀
    private String prefixName = DEFAULT_PREFIX_NAME;

    //后缀
    private String suffixName = DEFAULT_SUFFIX_NAME;

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getSuffixName() {
        return suffixName;
    }

    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }
}
