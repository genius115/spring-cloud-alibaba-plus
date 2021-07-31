package com.cloud.boot.jbdc.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.InetAddress;

/**
 * @author Madison
 * @date 2021/7/13
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Demo {
    private String id;
    private String name;
    private Integer age;
    private String address;
}
