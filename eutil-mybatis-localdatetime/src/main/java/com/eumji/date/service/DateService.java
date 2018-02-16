package com.eumji.date.service;

import com.eumji.date.mapper.DateMapper;
import com.eumji.date.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-2-5
 * @time: 下午5:14
 */
@Service
public class DateService {

    @Autowired
    private DateMapper dateMapper;

    public int saveUser(UserInfo userInfo){
        int count = dateMapper.saveInfo(userInfo);
        return count;
    }

    public UserInfo getUserById(int id){
        UserInfo userInfo = dateMapper.getUser(id);
        UserInfo userInfo2 = dateMapper.getUser(id);
        return userInfo;
    }
}
