package com.eumji.common.feign;

import com.eumji.common.feign.api.HelloApi;
import com.eumji.common.feign.config.Base64Util;
import com.eumji.common.feign.config.DefaultRetryerPlus;
import feign.Feign;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 2018/8/23
 * @time: 23:01
 */
public class FeiClientDemo {


    /**
     * feignclient最基本的用法
     */
    public void baseUse(){
        HelloApi helloApi = Feign.builder().target(HelloApi.class, "http://localhost:8081");
        String result = helloApi.hello("zhangsan");
        System.out.println("receive response:"+result);
    }

    /**
     * retry的简单示例
     * 关闭服务器即可模拟
     */
    public void retry(){
        HelloApi helloApi = Feign.builder().retryer(new DefaultRetryerPlus()).target(HelloApi.class, "http://localhost:8081");
        String result = helloApi.hello("zhangsan");
        System.out.println("receive response:"+result);

    }

    public void interceptor(){
        HelloApi helloApi = Feign.builder().requestInterceptor(new BasicAuthRequestInterceptor("admin","pass", Charset.forName("UTF-8"))).target(HelloApi.class, "http://localhost:8081");
        String result = helloApi.hello("zhangsan");
        System.out.println("receive response:"+result);
    }

    public static void main(String[] args) {
        new FeiClientDemo().interceptor();
        //BasicAuthRequestInterceptor basicAuthRequestInterceptor = new BasicAuthRequestInterceptor("admin", "pass", Charset.forName("UTF-8"));
        //basicAuthRequestInterceptor.apply(null);
        //String s = new String(Base64Util.decode("YWRtaW46cGFzcw==".getBytes(Charset.forName("utf-8"))));
        //System.out.println(s);
    }
}
