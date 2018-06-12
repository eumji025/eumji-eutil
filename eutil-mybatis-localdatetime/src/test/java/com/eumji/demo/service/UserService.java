package com.eumji.demo.service;

import com.eumji.date.model.UserInfo;
import com.eumji.demo.UserMapper;
import com.eumji.demo.model.dto.UserDto;
import com.eumji.eutil.pagehelper.base.Pagination;
import com.eumji.eutil.pagehelper.util.BaseServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-6-10
 * @time: 下午8:40
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;
    public Pagination<UserInfo> findUser(UserDto userDto){
        return BaseServiceUtil.searchByParam(userDto,userMapper::findByParam);
    }

}
