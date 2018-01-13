package com.eumji.jackson.model;

import com.eumji.jackson.util.DateJsonDeserialer;
import com.eumji.jackson.util.DateJsonSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.util.Date;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-1-13
 * @time: 上午11:34
 */
public class UserInfo {
    @JsonProperty(value = "firstName")
    private String first_name;

    private String lastName;

    private int age;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone ="GMT+8" )
    private Date birthday;

    @JsonDeserialize(using = DateJsonDeserialer.class)
    @JsonSerialize(using = DateJsonSerializer.class)
    private Date otherDay;

    private String address;
    @JsonIgnore
    private String ignoreStr;

    private Enum sex;

    @Override
    public String toString() {
        return "UserInfo{" +
                "first_name='" + first_name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                ", otherDay=" + otherDay +
                ", address='" + address + '\'' +
                ", ignoreStr='" + ignoreStr + '\'' +
                ", sex=" + sex +
                '}';
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @JsonGetter("xxxAdress")
    public String getAddress() {
        return address;
    }
    @JsonSetter("myAddress")
    public void setAddress(String address) {
        this.address = address;
    }

    public String getIgnoreStr() {
        return ignoreStr;
    }

    public void setIgnoreStr(String ignoreStr) {
        this.ignoreStr = ignoreStr;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Enum getSex() {
        return sex;
    }

    public void setSex(Enum sex) {
        this.sex = sex;
    }

    public Date getOtherDay() {
        return otherDay;
    }

    public void setOtherDay(Date otherDay) {
        this.otherDay = otherDay;
    }
}

