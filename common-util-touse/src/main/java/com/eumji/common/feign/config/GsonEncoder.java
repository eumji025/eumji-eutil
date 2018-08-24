package com.eumji.common.feign.config;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import feign.RequestTemplate;
import feign.codec.Encoder;

import java.lang.reflect.Type;
import java.util.Collections;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 2018/8/24
 * @time: 23:30
 */
public class GsonEncoder implements Encoder {

    private final Gson gson;

    public GsonEncoder(Iterable<TypeAdapter<?>> adapters) {
        this(GsonFactory.create(adapters));
    }

    public GsonEncoder() {
        this(Collections.emptyList());
    }

    public GsonEncoder(Gson gson) {
        this.gson = gson;
    }

    @Override
    public void encode(Object object, Type bodyType, RequestTemplate template) {
        template.body(gson.toJson(object, bodyType));
    }
}
