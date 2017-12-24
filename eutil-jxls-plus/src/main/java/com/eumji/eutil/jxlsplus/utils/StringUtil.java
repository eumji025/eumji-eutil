package com.eumji.eutil.jxlsplus.utils;

/**
 * string 工具类
 * @email eumji025@gmail.com
 * @author:EumJi
 * @date: 17-12-23
 * @time: 下午9:40
 */
public class StringUtil {

    /**
     * 验证是否为空
     * @param param
     * @return
     */
    public static boolean isEmpty(String param){
        if (param == null || "".equals(param.trim())){
            return true;
        }
        return false;
    }
}
