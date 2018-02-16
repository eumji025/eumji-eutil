package com.eumji.date.controller;

import com.eumji.date.model.UserInfo;
import com.eumji.date.service.DateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-2-5
 * @time: 下午5:12
 */
@RestController
public class DateController {

    @Autowired
    DateService dateService;

    @RequestMapping(value = "user",method = RequestMethod.GET)
    public UserInfo getUser(){
        return dateService.getUserById(1);
    }
}
