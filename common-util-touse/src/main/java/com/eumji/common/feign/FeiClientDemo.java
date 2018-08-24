package com.eumji.common.feign;

import com.eumji.common.feign.api.HelloApi;
import feign.Feign;
import feign.Retryer;
import feign.auth.BasicAuthRequestInterceptor;

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

    public void retry(){
        HelloApi helloApi = Feign.builder().retryer(new Retryer.Default()).target(HelloApi.class, "http://localhost:8081");
        String result = helloApi.hello("zhangsan");
        System.out.println("receive response:"+result);

    }

    public void interceptor(){
        HelloApi helloApi = Feign.builder().requestInterceptor(new BasicAuthRequestInterceptor("admin","pass")).target(HelloApi.class, "http://localhost:8081");
        String result = helloApi.hello("zhangsan");
        System.out.println("receive response:"+result);
    }

    public static void main(String[] args) {
        new FeiClientDemo().baseUse();
    }
}
