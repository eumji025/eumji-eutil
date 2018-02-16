package com.eumji.jackson.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * 将double数字格式化为两位小数
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-1-13
 * @time: 上午11:22
 */
public class MoneyJsonSerializer extends JsonSerializer<Double> {
    private static ThreadLocal<DecimalFormat> threadLocal = new ThreadLocal<>();

    public static DecimalFormat getDecimalFormat(){
        DecimalFormat decimalFormat = threadLocal.get();
        if (decimalFormat == null ){
            threadLocal.set(decimalFormat = new DecimalFormat("0.00"));
        }
        return decimalFormat;
    }

    @Override
    public void serialize(Double value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(getDecimalFormat().format(value));
    }
}
