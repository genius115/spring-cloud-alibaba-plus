package com.xiaomai.cloud.test.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

/**
 * @author Madison
 * @date 2021/3/4
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @NotBlank(message="姓名不能是空空空")
    @Length(min=3,max=8,message = "姓名长度为3~8")
    private String name;

    @NotEmpty(message = "密码不能为空")
    private String password;

    @Max(value=100,message = "最大值不能超过100")
    @Min(value=0,message = "最小值不能小于0")
    private Integer age;

    /**
     * 如果是空，则不校验，如果不为空，则校验
     */
    @Pattern(regexp="^[0-9]{4}-[0-9]{2}-[0-9]{2}$",message="出生日期格式不正确")
    private String birthday;
}
