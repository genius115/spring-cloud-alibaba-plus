package com.xiaomai;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 *  Map的有序和无序实现类，与Map的排序
 *
 * 1.HashMap、Hashtable不是有序的；
 * 2.TreeMap和LinkedHashMap是有序的（TreeMap默认 Key 升序，LinkedHashMap则记录了插入顺序）。
 *
 * @author Madison
 * @date 2021/5/10
 */
public class FastJsonTest {

    @Test
    public void TestMapJson(){
        Map<String,String> map = new TreeMap<String,String>();
        map.put("1","#1");
        map.put("2","#12");
        map.put("3","#123");
        map.put("4","#1234");
        map.put("31","#123-1");
        map.put("5","#12345");
        map.put("32","#123-2");
        String jsonMap = JSONObject.toJSONString(map);
        System.out.println(jsonMap);

        Map<String,String> map2 = new LinkedHashMap<String,String>();
        map2.put("1","#1");
        map2.put("2","#12");
        map2.put("3","#123");
        map2.put("4","#1234");
        map2.put("31","#123-1");
        map2.put("5","#12345");
        map2.put("32","#123-2");
        String jsonMap2 = JSONObject.toJSONString(map2);
        System.out.println(jsonMap2);

        Map<String,String> mapObject = (Map<String,String>) JSONObject.parse(jsonMap2);
        System.out.println(mapObject.get("2"));

    }
}
