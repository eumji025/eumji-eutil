package com.eumji.demo.model.dto;

import com.eumji.eutil.pagehelper.base.PageInfo;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-6-10
 * @time: 下午8:37
 */
public class UserDto extends PageInfo<Long> {

    private String name;

    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}