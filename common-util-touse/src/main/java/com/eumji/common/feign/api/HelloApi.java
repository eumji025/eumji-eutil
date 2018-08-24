package com.eumji.common.feign.api;

import feign.Param;
import feign.RequestLine;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 2018/8/23
 * @time: 23:04
 */
public interface HelloApi {

    @RequestLine("GET /hello/{name}")
    String hello(@Param("name") String name);
}
