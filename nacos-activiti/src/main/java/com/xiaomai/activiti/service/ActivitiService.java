package com.xiaomai.activiti.service;

import java.io.IOException;

/**
 * @Author tanghh
 * @Date 2020/3/27 10:11
 */
public interface ActivitiService {

    /**
     * 创建请假的工作流
     * @param checkPeople
     * @throws IOException
     */
    void createLeaveWorkFlow(String checkPeople) throws IOException;



    /**
     * 分配请假的工作流
     * @param processInstanceId
     * @param isAccept
     * @param checkPeople
     */
    void assginLeaveWorkFlow(String processInstanceId, int isAccept, String checkPeople);

    /**
     * 查询正在运行的实例
     */
    void queryExcution();


    /**
     * 查询流程实例状态
     * @param proccessInstanceId
     */
    void queryProccessInstanceState(String proccessInstanceId);
}
