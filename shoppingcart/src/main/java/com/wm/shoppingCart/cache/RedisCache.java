package com.wm.shoppingCart.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ClassName RedisCache
 * @Description TODO
 * @Author 李鉴
 * @Date 2020/4/15 17:11
 * @Version 1.0
 **/
@Target({ElementType.METHOD, ElementType.TYPE}) //定义注解修饰的目标,方法/类
@Retention(RetentionPolicy.RUNTIME) //定义注解的生命周期(SOURCE源码级别,CLASS编译期级别,RUNTIME运行期级别)
public @interface RedisCache{
    String key() default "";
}
