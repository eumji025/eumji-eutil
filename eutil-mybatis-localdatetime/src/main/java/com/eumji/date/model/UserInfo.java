package com.eumji.date.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-2-5
 * @time: 下午5:23
 */
@Alias("userinfo")
public class UserInfo implements Serializable {

	private int id;

	private String name;

    /**
     * 如果只是指明DateTimeFormat注解，返回前端的适合不能按照标准的pattern输出的
     *
     * 需要使用JsonFormat注解
	 *
	 * 因为对于json类型的响应，通常会使用{@link RequestResponseBodyMethodProcessor}进行处理，如果使用了这个就会脱离
	 * spring的Jsr310DateTimeFormatAnnotationFormatterFactory就会失效（还需要分析作用的时机）
     */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = DateTimeFormat.ISO.DATE_TIME)
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime birthday;

	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDateTime birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"id=" + id +
				", name='" + name + '\'' +
				", birthday=" + birthday +
				", age=" + age +
				'}';
	}
}
