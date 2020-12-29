package com.xiaomai.cloud.controller.captcha;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 验证码：
 * 1、hu-tool
 * <dependency>
 *         <groupId>cn.hutool</groupId>
 *         <artifactId>hutool-all</artifactId>
 *         <version>5.5.1</version>
 *       </dependency>
 * 2、easy-captcha
 * <dependency>
 *             <groupId>com.github.whvcse</groupId>
 *             <artifactId>easy-captcha</artifactId>
 *             <version>1.6.2</version>
 *         </dependency>
 * 3、kaptcha
 * <dependency>
 *             <groupId>com.github.penggle</groupId>
 *             <artifactId>kaptcha</artifactId>
 *             <version>2.3.2</version>
 *         </dependency>
 * 4、Happy-Captcha
 * <dependency>
 * 		  <groupId>com.ramostear</groupId>
 * 		  <artifactId>Happy-Captcha</artifactId>
 * 		  <version>1.0.1</version>
 * 		</dependency>
 *
 * @author developer
 * @date 2020/12/29
 */
@RestController
@RequestMapping
public class CaptchaController {

    @GetMapping("/captcha")
    public String getCaptcha(){
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(120,40);

        // 验证码对应的字符串
        String code = captcha.getCode();
        System.out.println("验证码："+code);
        // 返回图片流
        return captcha.getImageBase64();

    }
}
