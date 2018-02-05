package com.eumji.date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * 其实不算工具类，这是个人测试在java8中mybatis整合localdatetime
 * 详情请看github： https://github.com/mybatis/typehandlers-jsr310
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-2-5
 * @time: 下午5:15
 */
@SpringBootApplication
public class DateApplication {

    public static void main(String[] args) {
        SpringApplication.run(DateApplication.class,args);
    }
}
