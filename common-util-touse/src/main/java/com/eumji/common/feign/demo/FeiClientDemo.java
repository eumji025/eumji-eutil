package com.eumji.common.feign.demo;

import com.eumji.common.feign.api.HelloApi;
import com.eumji.common.feign.config.DefaultRetryerPlus;
import com.eumji.common.feign.config.GsonDecoder;
import com.eumji.common.feign.config.GsonEncoder;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;

import java.nio.charset.Charset;

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

    /**
     * interceptor的演示
     * server {@link com/eumji/common/feign/api/interceptor.go}
     */
    public void interceptor(){
        HelloApi helloApi = Feign.builder().requestInterceptor(new BasicAuthRequestInterceptor("admin","pass", Charset.forName("UTF-8"))).target(HelloApi.class, "http://localhost:8081");
        String result = helloApi.hello("zhangsan");
        System.out.println("receive response:"+result);
    }

    public void encodeAndDecode(){
        HelloApi helloApi = Feign.builder().encoder(new GsonEncoder())
                .decoder(new GsonDecoder()).target(HelloApi.class, "http://localhost:8081");

    }

    public static void main(String[] args) {
        new FeiClientDemo().interceptor();
        //BasicAuthRequestInterceptor basicAuthRequestInterceptor = new BasicAuthRequestInterceptor("admin", "pass", Charset.forName("UTF-8"));
        //basicAuthRequestInterceptor.apply(null);
        //String s = new String(Base64Util.decode("YWRtaW46cGFzcw==".getBytes(Charset.forName("utf-8"))));
        //System.out.println(s);
    }
}
