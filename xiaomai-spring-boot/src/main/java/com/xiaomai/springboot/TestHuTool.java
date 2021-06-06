package com.xiaomai.springboot;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Madison
 * @date 2021/4/21
 */
public class TestHuTool {
    public static void main(String[] args) {
        try {
            PageHelper.startPage(1,2);

            List<Entity> list = Db.use().findAll("NH_SAND_SCEN");
            System.out.println(list.size());

            PageInfo pageInfo = new PageInfo(list);

            PageInfo<Entity> pageInfo2 = new PageInfo(list);

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
