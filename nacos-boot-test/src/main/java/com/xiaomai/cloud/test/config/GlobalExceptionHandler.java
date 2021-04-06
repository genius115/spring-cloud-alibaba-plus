package com.xiaomai.cloud.test.config;

import com.xiaomai.cloud.test.bean.R;
import com.xiaomai.cloud.test.exception.LimitAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * @author Madison
 * @date 2021/3/4
 */
@ControllerAdvice
@Component
@Order(-1)
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handle(ValidationException exception) {
        if(exception instanceof ConstraintViolationException){
            ConstraintViolationException exs = (ConstraintViolationException) exception;

            Set<ConstraintViolation<?>> violations = exs.getConstraintViolations();
            for (ConstraintViolation<?> item : violations) {
                /**打印验证不通过的信息*/
                System.out.println(item.getMessage());
            }
        }
        return "bad request,valid 验证失败！" ;
    }
}
