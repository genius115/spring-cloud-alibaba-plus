package com.xiaomai.cloud.rocket;

import lombok.*;

import java.io.Serializable;

/**
 * set/get方法
 * 有参、无参构造
 * toString方法
 *
 * // @Data
 *
 * @author Madison
 * @date 2021/3/1
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
// @Data
public class User implements Serializable {
    private String loginName;
    private String pwd;
    private String sign;
}
