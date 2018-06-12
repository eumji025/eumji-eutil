package com.eumji.demo;

import com.eumji.date.model.UserInfo;
import com.eumji.demo.model.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-6-10
 * @time: 下午8:44
 */
@Mapper
public interface UserMapper {

    List<UserInfo> findByParam(UserDto userDto);
}
