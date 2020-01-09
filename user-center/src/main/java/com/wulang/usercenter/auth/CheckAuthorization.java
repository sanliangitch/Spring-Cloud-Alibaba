package com.wulang.usercenter.auth;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 元注解：元注解是指注解的注解。包括  @Retention @Target @Document @Inherited四种。
 *
 * @Retention: 定义注解的保留策略
 * @Target：定义注解的作用目标
 * @Document：说明该注解将被包含在javadoc中
 * @Inherited：说明子类可以继承父类中的该注解
 *
 * 可以参考：https://www.cnblogs.com/CatsBlog/p/9329785.html
 * @author wulang
 * @create 2020/1/8/20:48
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckAuthorization {
    String value();
}
