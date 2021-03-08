package com.xiaomai.cloud.test.handler;

import com.xiaomai.cloud.test.bean.JsonResult;
import com.xiaomai.cloud.test.exception.UserNotExistException;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * 被要求单独处理的异常会先被处理，而后其他异常会被Exception（默认形式）的处理方法捕获。
 * @author Madison
 * @date 2021/3/5
 */
@ControllerAdvice
public class ControllerHandler {

    /**
     * 处理全部异常
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult handleException(Exception ex) {
        System.out.println("程序异常：" + ex.toString());
        return new JsonResult(false, "请求失败");
    }

    /**
     * 处理UserNotExistException异常
     */
    @ExceptionHandler({UserNotExistException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public JsonResult handleUserNotExistException(UserNotExistException ex) {
        System.out.println("请求用户数据异常：" + ex.toString());
        return new JsonResult(false, "请求用户数据失败");
    }

}