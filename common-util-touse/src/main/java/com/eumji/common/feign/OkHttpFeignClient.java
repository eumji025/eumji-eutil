package com.eumji.common.feign;

import com.eumji.common.feign.api.HelloApi;
import feign.Feign;
import feign.okhttp.OkHttpClient;
import okhttp3.ConnectionPool;

import java.util.concurrent.TimeUnit;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 2018/8/25
 * @time: 7:18
 */
public class OkHttpFeignClient {

    public static HelloApi okhttpProxy(){
        HelloApi target = Feign.builder().client(initOkHttpClient()).target(HelloApi.class, "http://localhost:8081");

        return target;
    }

    public static OkHttpClient initOkHttpClient(){
        okhttp3.OkHttpClient okHttpClient = new okhttp3.OkHttpClient().newBuilder()
                .connectionPool(new ConnectionPool(10, 2, TimeUnit.MINUTES))
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .build();
        return new OkHttpClient(okHttpClient);
    }
}
