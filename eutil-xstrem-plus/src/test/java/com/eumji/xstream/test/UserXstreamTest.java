package com.eumji.xstream.test;

import com.eumji.xstream.model.User;
import com.eumji.xstream.util.XstreamBuildFactory;
import com.thoughtworks.xstream.XStream;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: write some thing of this file
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 2018-07-01
 */
public class UserXstreamTest {
    private static Logger logger = LoggerFactory.getLogger(UserXstreamTest.class);
    /**
     * <com.eumji.xstream.model.User>
     *   <name>张三</name>
     *   <age>18</age>
     *   <address>海天一路</address>
     * </com.eumji.xstream.model.User>
     * 默认的class是全路径名
     */
    @Test
    public void commonXstreamTest(){
        XStream xStream = XstreamBuildFactory.buildDefaultXstream();
        User user = new User();
        user.setAddress("海天一路");
        user.setName("张三");
        user.setAge(18);
        String value = xStream.toXML(user);
        logger.info("{}.{} method the xstream format value is :{}",UserXstreamTest.class.getCanonicalName(),"commonXstreamTest",value);

    }

}