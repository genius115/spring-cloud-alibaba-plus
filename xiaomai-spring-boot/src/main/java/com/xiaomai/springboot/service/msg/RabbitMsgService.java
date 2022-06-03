package com.xiaomai.springboot.service.msg;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author Madison
 * 
 * <p>
 *  @ConditionalOnProperty
 *  通过其两个属性name以及havingValue来实现的，其中name用来从application.properties中读取某个属性值。
 *  如果该值为空，则返回false;
 *  如果值不为空，则将该值与havingValue指定的值进行比较，如果一样则返回true;否则返回false。
 *  如果返回值为false，则该configuration不生效；为true则生效。
 *  </p>
 *
 *  @ConditionalOnProperty注解类源码如下：
 *  @Retention(RetentionPolicy.RUNTIME)
 *  @Target({ ElementType.TYPE, ElementType.METHOD })
 *  @Documented
 *  @Conditional(OnPropertyCondition.class)
 *  public @interface ConditionalOnProperty {
 *
 *  // 数组，获取对应property名称的值，与name不可同时使用
 *  String[] value() default {};
 *
 *  // 配置属性名称的前缀，比如spring.http.encoding
 *  String prefix() default "";
 *
 *  // 数组，配置属性完整名称或部分名称
 *  // 可与prefix组合使用，组成完整的配置属性名称，与value不可同时使用
 *  String[] name() default {};
 *
 *  // 可与name组合使用，比较获取到的属性值与havingValue给定的值是否相同，相同才加载配置
 *  String havingValue() default "";
 *
 *  // 缺少该配置属性时是否可以加载。如果为true，没有该配置属性时也会正常加载；反之则不会生效
 *  boolean matchIfMissing() default false;
 *  }
 * 
 * 
 * @date 2022/6/2
 */
@Configuration
@ConditionalOnProperty(name = "msg.type",havingValue = "rabbit")
public class RabbitMsgService implements MsgService{
    @Override
    public String send(String msg) {
        return "RabbitMsgService---"+msg;
    }
}
