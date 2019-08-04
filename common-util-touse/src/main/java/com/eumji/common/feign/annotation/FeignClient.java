package com.eumji.common.feign.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义feign的注解 用于spring加载机制
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 2018/9/8
 * @time: 8:22
 */
@Target({ElementType.PACKAGE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FeignClient {

    String serverUrl() default "";
}
