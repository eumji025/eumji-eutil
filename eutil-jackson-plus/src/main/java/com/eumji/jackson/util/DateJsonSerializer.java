package com.eumji.jackson.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-1-13
 * @time: 上午11:20
 */
public class DateJsonSerializer extends JsonSerializer<Date> {
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        jsonGenerator.writeString(sf.format(date));
    }
}
