package com.xiaomai.code;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Madison
 * @date 2021/4/21
 */
public class TestHuTool {
    public static void main(String[] args) {
        try {
            List<Entity> list = Db.use().findAll("NH_SAND_SCEN");
            System.out.println(list.size());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
