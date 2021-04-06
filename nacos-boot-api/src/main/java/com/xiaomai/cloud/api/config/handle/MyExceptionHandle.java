package com.xiaomai.cloud.api.config.handle;

import com.xiaomai.cloud.api.comm.R;
import com.xiaomai.cloud.api.comm.exception.LimitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Madison
 * @date 2021/4/6
 */
@RestControllerAdvice
public class MyExceptionHandle {
    private static final Logger LOG = LoggerFactory.getLogger(MyExceptionHandle.class);

    @ExceptionHandler(LimitException.class)
    public Object Handle(Exception e, HttpServletRequest request){
        LOG.error("msg:{},url:{}", ((LimitException)e).getMsg(), request.getRequestURL());

//        Map<String, Object> map = new HashMap<>();
//        map.put("code",((LimitException) e).getCode());
//        map.put("msg",((LimitException) e).getMsg());
//        map.put("url", request.getRequestURL());
//        return map;

        return new R.Builder<String>().setData(request.getRequestURL().toString())
                .consumerFailed(((LimitException) e).getCode(),((LimitException) e).getMsg());
    }
}
