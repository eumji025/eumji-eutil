package com.eumji.jackson.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import sun.security.pkcs.ParsingException;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-1-13
 * @time: 上午11:14
 */
public class DateJsonDeserialer extends JsonDeserializer<Date> {
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String text = jsonParser.getText();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sf.parse(text);

        } catch (ParseException e) {
            //e.printStackTrace();
            throw new ParsingException("日期格式错误。请输入正确的格式！！！");
        }
    }
}
