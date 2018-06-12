package com.eumji.date.mapper;

import com.eumji.date.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    UserInfo getUserByName(int id,String name);

    List<UserInfo> getAllUser();

}
