package com.eumji.eutil.pagehelper.util;

import java.io.*;

/**
 * @email eumji025@gmail.com
 * @author: EumJi
 * @date: 18-6-11
 * @time: 上午8:10
 */
public class ExceptionUtil {

    public static String printException(Throwable throwable) {
        if (throwable == null) {
            return null;
        }
        try (StringWriter stringWriter = new StringWriter();
             PrintWriter printWriter = new PrintWriter(stringWriter)) {
            throwable.printStackTrace(printWriter);
            return stringWriter.toString();
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return "";
    }
}
