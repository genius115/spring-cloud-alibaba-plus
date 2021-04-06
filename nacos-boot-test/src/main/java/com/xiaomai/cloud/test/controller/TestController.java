package com.xiaomai.cloud.test.controller;

import cn.hutool.core.date.DateUtil;
import com.xiaomai.cloud.test.annotation.Limit;
import com.xiaomai.cloud.test.annotation.RateLimit;
import com.xiaomai.cloud.test.bean.Demo;
import com.xiaomai.cloud.test.bean.R;
import com.xiaomai.cloud.test.bean.User;
import com.xiaomai.cloud.test.exception.LimitAccessException;
import com.xiaomai.cloud.test.service.GuavaRateLimiterService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Set;

/**
 * @author
 * @date 2021/3/2
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private Validator validator;

    @Autowired
    private GuavaRateLimiterService rateLimiterService;


    @GetMapping("/application")
    @RateLimit(limitNum = 100)
    public String testApplication() {
        if(rateLimiterService.tryAcquire()){
            log.info("成功获取许可");
        }
        log.info("未获取到许可");
        return DateUtil.now();
    }


    @GetMapping("/guavaLimit")
    @Limit(name = "测试限流每秒3个请求", perSecond = 3)
    public Object guavaLimit() {
        return new R.Builder<String>().setData("请求成功！").ok();
    }


    @ExceptionHandler(value = LimitAccessException.class)
    public Object LimitAccessExceptionHandler(LimitAccessException e) {
        //LOGGER.error("LimitAccessException...", e);
        return new R.Builder<String>().setData("请求频繁，请求限流").ok();
    }

    /**
     * 普通模式(会校验完所有的属性，然后返回所有的验证失败信息)
     *
     * @param user
     * @param result
     */
    @PostMapping("/user")
    public void user(@RequestBody @Valid User user, BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
        }

    }

    /**
     * Get请求验证参数
     *
     * @param grade
     * @param classroom
     */
    @Validated
    @RequestMapping(value = "/demo3", method = RequestMethod.GET)
    public void demo3(@Range(min = 1, max = 9, message = "年级只能从1-9")
                      @RequestParam(name = "grade", required = true)
                              int grade,
                      @Min(value = 1, message = "班级最小只能1")
                      @Max(value = 99, message = "班级最大只能99")
                      @RequestParam(name = "classroom", required = true)
                              int classroom) {
        System.out.println(grade + "," + classroom);

    }

    /**
     * 模型验证
     */
    @GetMapping("/demo2")
    public void demo2(){
        User userDemo = new User("zh","123456",10,"2000-01-03");
        //Bean验证
        Set<ConstraintViolation<User>> violationSet = validator.validate(userDemo);
        for (ConstraintViolation<User> model : violationSet) {
            System.out.println("Bean验证："+model.getMessage());
        }
    }

    /**
     * 自定义验证规则
     */
    @RequestMapping("/demo4")
    public void demo4(){
        Demo demo = new Demo();
        demo.setUserName("userName");
        Set<ConstraintViolation<Demo>> validate = validator.validate(demo);
        for (ConstraintViolation<Demo> dem : validate) {
            System.out.println(dem.getMessage());
        }
    }

}
