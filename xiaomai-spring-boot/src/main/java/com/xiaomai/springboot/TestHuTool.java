package com.xiaomai.springboot;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Madison
 * @date 2021/4/21
 */
@Slf4j
public class TestHuTool {
    public static void main(String[] args) throws InterruptedException {
        /*try {
            PageHelper.startPage(1,2);

            List<Entity> list = Db.use().findAll("NH_SAND_SCEN");
            System.out.println(list.size());

            PageInfo pageInfo = new PageInfo(list);

            PageInfo<Entity> pageInfo2 = new PageInfo(list);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }*/
        
        //阻塞队列
        LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<String>();
        lbq.add("1");
        lbq.add("2");
        lbq.add("3");
        lbq.add("4");
        
        log.info(lbq.take());
        log.info(lbq.take());
        log.info(lbq.take());
        log.info(lbq.take());

        
        
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }

        System.out.println("isEmpty:"+stack.isEmpty());
        System.out.println("capacity:"+stack.capacity());
        System.out.println("peek:"+stack.peek());

        System.out.println("*********遍历操做**********");

        // 集合遍历方式
        for (Integer x : stack) {
            System.out.println(x);
        }
        System.out.println("--------------------------");
        // 栈弹出遍历方式
        // while (s.peek()!=null) { //不健壮的判断方式，容易抛异常，正确写法是下面的
        while (!stack.empty()) {
            System.out.println(stack.pop());
        }
    }
}
