package com.eumji.date.service;

import com.eumji.date.mapper.DateMapper;
import com.eumji.date.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    //@Transactional
    public UserInfo getUserById(int id){
        //List<UserInfo> allUser = dateMapper.getAllUser();
        Optional<UserInfo> userInfo = dateMapper.getUserByName(id,"zhangsan");
        //UserInfo userInfo1 = dateMapper.getUser(id);
        //UserInfo userInfo2 = dateMapper.getUser(id);

        UserInfo userInfo1 = new UserInfo();
        userInfo1.setName("not match...........");
        return userInfo.orElse(userInfo1);
        //return userInfo;
    }

    public List<UserInfo> getAllUser(){
        List<UserInfo> users =  dateMapper.getAllUser();

        System.out.println(users);

        return users;
    }

}
