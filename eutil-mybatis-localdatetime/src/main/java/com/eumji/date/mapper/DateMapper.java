package com.eumji.date.mapper;

import com.eumji.date.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

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
}
