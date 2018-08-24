package com.eumji.common.feign.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Map;
import static feign.Util.resolveLastTypeParameter;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 2018/8/24
 * @time: 23:29
 */
public final class GsonFactory {

    private GsonFactory() {}

    /**
     * Registers type adapters by implicit type. Adds one to read numbers in a {@code Map<String,
     * Object>} as Integers.
     */
    static Gson create(Iterable<TypeAdapter<?>> adapters) {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        builder.registerTypeAdapter(new TypeToken<Map<String, Object>>() {}.getType(),
                new DoubleToIntMapTypeAdapter());
        for (TypeAdapter<?> adapter : adapters) {
            Type type = resolveLastTypeParameter(adapter.getClass(), TypeAdapter.class);
            builder.registerTypeAdapter(type, adapter);
        }
        return builder.create();
    }
}
