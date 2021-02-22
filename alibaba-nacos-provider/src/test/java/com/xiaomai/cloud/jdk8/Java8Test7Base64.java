package com.xiaomai.cloud.jdk8;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.UUID;

/**
 * Java 8 内置了 Base64 编码的编码器和解码器。
 *
 * Base64工具类提供了一套静态方法获取下面三种BASE64编解码器：
 *
 *     基本：输出被映射到一组字符A-Za-z0-9+/，编码不添加任何行标，输出的解码仅支持A-Za-z0-9+/。
 *     URL：输出映射到一组字符A-Za-z0-9+_，输出是URL和文件。
 *     MIME：输出隐射到MIME友好格式。输出每行不超过76字符，并且使用'\r'并跟随'\n'作为分割。编码输出最后没有行分割。
 *
 * 内嵌类
 * 序号 	内嵌类 & 描述
 * 1 	static class Base64.Decoder *
 * 该类实现一个解码器用于，使用 Base64 编码来解码字节数据。
 *
 * 2 	static class Base64.Encoder *
 * 该类实现一个编码器，使用 Base64 编码来编码字节数据。
 *
 *
 * @author Madison
 * @date 2021/1/17
 */
public class Java8Test7Base64 {

    public static void main(String args[]){
        try {

            // 使用基本编码
            String base64encodedString = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);

            // 解码
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);
            System.out.println("原始字符串: " + new String(base64decodedBytes, "utf-8"));


            base64encodedString = Base64.getUrlEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (URL) :" + base64encodedString);

            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < 10; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }
            System.out.println("stringBuilder:"+stringBuilder);
            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);

        }catch(UnsupportedEncodingException e){
            System.out.println("Error :" + e.getMessage());
        }
    }
}
