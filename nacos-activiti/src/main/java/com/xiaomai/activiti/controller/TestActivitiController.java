package com.xiaomai.activiti.controller;

import com.xiaomai.activiti.service.ActivitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Author tanghh
 * @Date 2020/3/27 10:21
 */
@RestController
public class TestActivitiController {

    @Autowired
    private ActivitiService activitiService;
    /**
     * 1.创建一个流程实例
     */
    @GetMapping(value = "/createLeaveFlow")
    public void createLeaveFlow() throws IOException {
        //1.举个例子，soup_tang 需要请假 ，我在页面上添加请假申请,任务申请人是soup_tang,
        // 我选择审核人为汤总,请汤总给我审核这个请假流程。
        String checkPeople = "汤总";
        activitiService.createLeaveWorkFlow(checkPeople);
    }

    /**
     * 2.执行完 第一个方法的创建流程以后，就要执行下面这个审核方法
     * @throws IOException
     */
    @GetMapping(value = "/assginLeaveFlow")
    public void assginLeaveFlow()  {
        //1.当汤总 登录系统的时候，他需要审核一些 审核人为他的 请假流程,当然了，我们在创建这个请假流程的时候，
        //我这个审核人就是可以动态赋值的
        //2.这个是流程实例id ,每次创建一个工作流，都会生成，在具体的生产环境中我们需要将这个流程实例id保存起来。
        String processInstanceId = "2507";
        //3.这个是审核人是否同意当前这个流程。（0代表同意 1代表不同意）
        int isAcceppt = 0;
        //4.这个是当前审核人
        String checkPeople = "汤总";
        activitiService.assginLeaveWorkFlow(processInstanceId,isAcceppt,checkPeople);
    }

    /**
     * 3.查询正在运行的实例
     */
    @GetMapping(value = "/listLeaveFlow")
    public void listLeaveFlow()  {
        activitiService.queryExcution();
    }

    /**
     * 3.查询正在运行的实例
     */
    @GetMapping(value = "/getLeaveFlowByProccessInstanceId")
    public void getLeaveFlowByProccessInstanceId()  {
        String proccessInstanceId = "2507";
        activitiService.queryProccessInstanceState(proccessInstanceId);
    }
}
