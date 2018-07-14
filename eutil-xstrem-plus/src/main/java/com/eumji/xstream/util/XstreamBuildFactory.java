package com.eumji.xstream.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.thoughtworks.xstream.converters.basic.DoubleConverter;
import com.thoughtworks.xstream.converters.extended.SqlTimestampConverter;

import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: xstream builder factory util
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 2018-07-01
 */
public class XstreamBuildFactory {

    private static final String DEFAULT_TIME_FORMT="yyyy-MM-dd HH:mm:ss";
    //todo if have more format patterns ， just add in this array
    private static final String[] FORMAT_COLLECTION = {"yyyy-MM-dd HH:mm:ss.S z","yyyy-MM-dd G HH:mm:ss.S ",
    "yyyy-MM-dd HH:mm:ss.S 'UTC'","yyyy-MM-dd G HH:mm:ss.S 'UTC'","yyyy-MM-dd HH:mm:ssz","yyyy-MM-dd'T'HH:mm:ss.SX","yyyy-MM-dd'T'HH:mm:ss.X",
            "yyyy-MM-dd'T'HH:mmX","yyyy-MM-dd","yyyy/MM/dd","yyyy/MM/dd HH:mm:ss"};

    private static ConcurrentHashMap<String,XStream> xstreamMap = new ConcurrentHashMap<>();
    private XstreamBuildFactory(){

    }

    /**
     * xstream反序列化默认0时区，这会造成 时间+8小时，所以要切换到东8区
     * @return
     */
    private static XStream buildDefaultXstream(){
        XStream xStream = new XStream();
        xStream.ignoreUnknownElements();
        xStream.registerConverter(new SqlTimestampConverter(TimeZone.getTimeZone("GMT+8")));
        xStream.registerConverter(new DateConverter(DEFAULT_TIME_FORMT,FORMAT_COLLECTION,TimeZone.getTimeZone("GMT+8")));
        xStream.registerConverter(new DoubleConverter(){
            @Override
            public Object fromString(String str) {
                if (str == null || str.length() <= 0){
                    return null;
                }
                return super.fromString(str);
            }
        });
        xStream.autodetectAnnotations(true);
        return xStream;
    }

    /**
     * 通过key构建一个xstream，为什么构建多个，可能发生别名冲突
     * 如果不存在别名冲突，也许一个就够了 扩展
     * @param key
     * @return
     */
    public static XStream buildXstreamByKey(String key){
        return xstreamMap.computeIfAbsent(key, key2-> buildDefaultXstream());
    }

    public static XStream createDefaultXStream(){
        return buildXstreamByKey("xstream-default-builder-key");
    }

    /**
     * 提供扩展的接口
     * @param key 获取xstream的key
     * @param xstreamExpander 扩展接口，可以为空
     * @return
     */
    public static XStream createXstreamAndExpand(String key, XStreamExpander xstreamExpander){
        XStream stream = buildXstreamByKey(key);
        if (xstreamExpander != null){
            return xstreamExpander.expandMoreAttr(stream);
        }
        return stream;
    }


}
