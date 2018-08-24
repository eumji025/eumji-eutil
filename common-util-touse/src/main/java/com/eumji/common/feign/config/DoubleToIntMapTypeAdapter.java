package com.eumji.common.feign.config;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.Map;

/**
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 2018/8/24
 * @time: 23:30
 */
public class DoubleToIntMapTypeAdapter extends TypeAdapter<Map<String, Object>> {
    private final TypeAdapter<Map<String, Object>> delegate =
            new Gson().getAdapter(new TypeToken<Map<String, Object>>() {});

    @Override
    public void write(JsonWriter out, Map<String, Object> value) throws IOException {
        delegate.write(out, value);
    }

    @Override
    public Map<String, Object> read(JsonReader in) throws IOException {
        Map<String, Object> map = delegate.read(in);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            if (entry.getValue() instanceof Double) {
                entry.setValue(Double.class.cast(entry.getValue()).intValue());
            }
        }
        return map;
    }
}
