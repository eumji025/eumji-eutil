package com.eumji.date.mapper;

import java.util.List;
import java.util.Optional;

import com.eumji.date.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-2-5
 * @time: 下午5:14
 */
@Mapper
public interface DateMapper {

    int saveInfo(UserInfo userInfo);

    UserInfo getUser(int id);

    //UserInfo getUserByName(@Param("id") int id, @Param("name") String name);

    Optional<UserInfo> getUserByName(@Param("id") int id, @Param("name") String name);

    List<UserInfo> getAllUser();

}
