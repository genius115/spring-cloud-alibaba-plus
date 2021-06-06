package com.xiaomai.fastjson;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Optional;
import java.util.Random;
import java.util.StringJoiner;

/**
 * @author Madison
 * @date 2021/5/13
 */
public class TestJson {
    /**
     * Json对象转换用的是get方法
     */
    @Test
    public void userDemoToJson(){
        UserDemo userDemo = new UserDemo();
        userDemo.setId("1");
        userDemo.setName("张三");
        String userJson = JSON.toJSONString(userDemo);
        System.out.println(userJson);
    }

    @Test
    public void forEachDemo(){
        for(int i=0;i<5;i++){
            if(i==3){
                System.out.println("continue:"+i);
                continue;
            }
            if(i==4){
                System.out.println("break:"+i);
                break;
            }
            System.out.println("print:"+i);
        }
    }

    @Test
    public void switchDemo(){
        int i= (int) (Math.random()*10);
        Random random = new SecureRandom();
        i = new Random().nextInt(5);
        switch(i){
            case 0 :
                //语句
                System.out.println("case0:" +i);
                break; //可选
            case 1 :
                //语句
                System.out.println("case1:" +i);
                break; //可选
            case 2 :
                //语句
                System.out.println("case2:" +i);
                break; //可选
            //你可以有任意数量的case语句
            default : //可选
                //语句
                System.out.println("default:" +i);
        }

    }

    @Test
    public void randomDemo1(){
        Random r = new Random(12345);
        for (int i = 0; i < 10; i++) {
            System.out.print(r.nextInt(100));
        }

        SecureRandom sr = null;
        try {
            sr = SecureRandom.getInstanceStrong(); // 获取高强度安全随机数生成器
        } catch (NoSuchAlgorithmException e) {
            sr = new SecureRandom(); // 获取普通的安全随机数生成器
        }
        byte[] buffer = new byte[16];
        sr.nextBytes(buffer); // 用安全随机数填充buffer
        System.out.println(Arrays.toString(buffer));
    }

    @Test
    public void stringtojdk8Demo(){
        StringJoiner sj = new StringJoiner(",");
        sj.add("1");
        sj.add("2");
        sj.add("3");
        System.out.println(sj.toString());
    }

    @Test
    public void optionDemo(){
        UserDemo userDemo = new UserDemo();
        userDemo.setId("1");
        userDemo.setName("张三");
        String id = Optional.ofNullable(userDemo).map(user->user.getId()).orElse("UNKNOWN");
        System.out.println(id);

        userDemo = null;
        id = Optional.ofNullable(userDemo).map(user->user.getId()).orElse("UNKNOWN");
        System.out.println(id);

        boolean is = StrUtil.isNotBlank("unfinfinrd");
        System.out.println(is);

    }

}
