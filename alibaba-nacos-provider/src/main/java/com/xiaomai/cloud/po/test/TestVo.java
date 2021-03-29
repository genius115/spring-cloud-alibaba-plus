package com.xiaomai.cloud.po.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Madison
 * @date 2021/3/22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestVo {
    private String name;
    private Integer age;
    private String email;

}
