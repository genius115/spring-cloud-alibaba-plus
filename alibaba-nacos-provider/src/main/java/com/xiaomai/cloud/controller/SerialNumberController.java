package com.xiaomai.cloud.controller;

import com.xiaomai.cloud.service.serialnumber.IGroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Madison
 * @date 2021/2/1
 */
@Api(value = "流水号生成")
@RestController
@RequestMapping("/serial")
public class SerialNumberController {

    @Autowired
    private IGroupService groupServiceImpl;

    @ApiOperation(value = "获取新流水号", nickname = "测试SerialNumberController的getNewNum接口")
    @GetMapping("/getNewNum")
    public String getNewNum() {
        return groupServiceImpl.getNewAutoNum();
    }
}
