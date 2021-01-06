package com.xiaomai.cloud.test;

/**
 * @author developer
 * @date 2021/1/6
 */
public class TestSystemArguments {
    public static void main(String[] args) {
//-D set a system property(设置系统属性) 可通过语句System.getProperties().list(System.out);查看有哪些参数可以设置。 可设置的参数:
//Java启动命令可选项(options)大致可分为标准(-D等)和非标准（-X、-XX）两种，
//非标准的可选项不保证在所有平台上都实现，并且未来的版本中可能会被修改且不告知,总之就是不稳定(Unstable)。不过有的非标准可选项还是非常有用的，后面我们会谈到。
        System.getProperties().list(System.out);
    }
}
